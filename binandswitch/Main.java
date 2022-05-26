package binandswitch;

public class Main {
    public static void main(String[] args) {
        int bin = 0b10101;
        short oct = 0123;
        long hex = 0xF0F0F0F;
        int underscored = 123_456_789;

        System.out.println("Binary 0b10101:" + bin);
        System.out.println("Octal 0123:" + oct);
        System.out.println("Hex 0xF0F0F0F:" + hex);
        System.out.println("Underscored 123_456_789:" + underscored);

        String today = "Thursday";
        switch (today) {
            case "Monday": case "Tuesday": case "Wednesday": case "Thursday": case "Friday":   
                System.out.println("Today is a weekday");
                break;
            case "Saturday": case "Sunday":
                System.out.println("Today is a weekend");
                break;
        }
    }
}
