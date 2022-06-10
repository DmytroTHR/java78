package hiberpractice;

public class Application {

    public static void main(String[] args) throws Exception{

        TransactionHistory vitold = new TransactionHistory(12345, "Vitold", "Credit", 1320);
        TransactionHistory nestor = new TransactionHistory(12332, "Nestor", "Credit", 2030);
        TransactionHistory boris = new TransactionHistory(12323, "Boris", "Debit", 2000);
        TransactionHistory hendalf = new TransactionHistory(12354, "Hendalf", "Debit", 4400);

        Hibernate<TransactionHistory> hibernate = Hibernate.getConnection();
        hibernate.write(vitold);
        hibernate.write(nestor);
        hibernate.write(boris);
        hibernate.write(hendalf);

        TransactionHistory obj2 = hibernate.read(TransactionHistory.class, 2L);
        System.out.println(obj2);
    }
}
