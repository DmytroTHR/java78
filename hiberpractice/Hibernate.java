package hiberpractice;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import hiberpractice.annotation.Column;
import hiberpractice.annotation.PrimaryKey;

public class Hibernate<T> {

    private Connection conn;
    private AtomicLong id = new AtomicLong(0);

    public static <T> Hibernate<T> getConnection() throws SQLException {
        return new Hibernate<>();
    }

    private Hibernate() throws SQLException {
        this.conn = DriverManager.getConnection("jdbc:h2:./hiberpractice/data/practice", "sa", "");
    }

    public void write(T t) throws Exception {
        Class<?> clss = t.getClass();
        Field[] fields = clss.getDeclaredFields();

        Field pkey = null;
        List<Field> columns = new ArrayList<>();
        StringJoiner joiner = new StringJoiner(",");

        for (Field field : fields) {
            if (field.isAnnotationPresent(PrimaryKey.class)) {
                pkey = field;
                // System.out.println("The primary key is: " + field.getName() + " = " +
                // field.get(t));
            } else if (field.isAnnotationPresent(Column.class)) {
                columns.add(field);
                joiner.add(field.getName());
                // System.out.println("The column is: " + field.getName() + " = " +
                // field.get(t));
            }
        }

        int numFlds = columns.size() + 1;
        String vals = IntStream.range(0, numFlds)
                .mapToObj(e -> "?")
                .collect(Collectors.joining(","));

        String sql = "insert into " + clss.getSimpleName() + " ("
                + Objects.requireNonNull(pkey, "No primary key found").getName() + ","
                + joiner.toString() + ") values (" + vals + ")";
        PreparedStatement stmt = conn.prepareStatement(sql);

        if (pkey.getType() == long.class) {
            stmt.setLong(1, id.incrementAndGet());
        }

        int idx = 2;
        for (Field col : columns) {
            col.setAccessible(true);
            if (col.getType() == int.class) {
                stmt.setInt(idx++, (int) col.get(t));
            } else if (col.getType() == String.class) {
                stmt.setString(idx++, (String) col.get(t));
            } else if (col.getType() == double.class) {
                stmt.setDouble(idx++, (double) col.get(t));
            }
        }

        stmt.executeUpdate();
    }

    public T read(Class<T> clss, long l) throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
        Field[] declFields = clss.getDeclaredFields();
        Field pkey = null;
        for (Field fld : declFields) {
            if (fld.isAnnotationPresent(PrimaryKey.class)) {
                pkey = fld;
                break;
            }
        }
        String sql = "select * from " + clss.getSimpleName() + " where "
                + Objects.requireNonNull(pkey, "No primary key found").getName()
                + " = " + l;
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        rs.next();

        T genObj = clss.getConstructor().newInstance();
        long transactionId = rs.getInt(pkey.getName());
        pkey.setAccessible(true);
        pkey.set(genObj, transactionId);
        for (Field fld : declFields){
            if (fld.isAnnotationPresent(Column.class)){
                fld.setAccessible(true);
                if (fld.getType() == int.class){
                    fld.set(genObj, rs.getInt(fld.getName()));
                } else if (fld.getType() == String.class){
                    fld.set(genObj, rs.getString(fld.getName()));
                } else if (fld.getType() == double.class){
                    fld.set(genObj, rs.getDouble(fld.getName()));
                }
            }
        }

        return genObj;
    }
}
