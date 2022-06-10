package hiberpractice;

import java.sql.SQLException;

import org.h2.tools.Server;

public class Launcher {
    public static void main(String[] args) {
        try {
            Server.main();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
