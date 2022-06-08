package datelocal;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.temporal.ChronoField;

public class Main {
    public static void main(String[] args) {
        LocalDate dateBirth = LocalDate.of(2000, 9, 19);
        System.out.println("Birth date: " + dateBirth);

        int nowYear = LocalDate.now().getYear();
        Month nowMonth = LocalDate.now().getMonth();
        DayOfWeek nowDay = LocalDate.now().getDayOfWeek();
        int nowDate = LocalDate.now().getDayOfMonth();
        System.out.println("Current date: " + nowYear + "-" + nowMonth + "-" + nowDate);
        System.out.println("Today is: " + nowDay);

        boolean leapYear = dateBirth.isLeapYear();
        System.out.println("Is leap year: " + leapYear);

        int dayBirth = dateBirth.get(ChronoField.DAY_OF_MONTH);
        int monthBirth = dateBirth.get(ChronoField.MONTH_OF_YEAR);
        int yearBirth = dateBirth.get(ChronoField.YEAR_OF_ERA);
        System.out.println("Birth date: " + dayBirth + "." + monthBirth + "." + yearBirth);

        LocalDate parsedDate = LocalDate.parse("2002-02-02");
        System.out.println("Parsed date: " + parsedDate);

        LocalTime timeBirth = LocalTime.of(12, 30, 44);
        System.out.println("Birth time: " + timeBirth);
        LocalTime timeNow = LocalTime.now();
        System.out.println("Current time: " + timeNow);
        System.out.println("Hours: " + timeNow.getHour());
        System.out.println("Minutes: " + timeNow.getMinute());
        System.out.println("Seconds: " + timeNow.getSecond());
        System.out.println("Nano seconds: " + timeNow.getNano());

        LocalDateTime dateTimeBirth = LocalDateTime.of(2000, 9, 19, 12, 30, 44);
        System.out.println("Year before birth: " + dateTimeBirth.minusYears(1).getYear());
        System.out.println("Month after birth: " + dateTimeBirth.plusMonths(1).getMonth());
        LocalDate dateB = dateTimeBirth.toLocalDate();
        LocalTime timeB = dateTimeBirth.toLocalTime();
        System.out.println("Birth date: " + dateB);
        System.out.println("Birth time: " + timeB);
        System.out.println("Seconds passed in the day of birth: "+ timeB.toSecondOfDay());
    }
}
