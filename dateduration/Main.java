package dateduration;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.AbstractMap.SimpleEntry;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Instant insNow = Instant.now();
        Instant insBirth = Instant.ofEpochSecond(
                LocalDateTime.parse("1983-03-04T12:30:00").toEpochSecond(ZoneOffset.UTC));
        System.out.println("Now: " + insNow);
        System.out.println("Birth: " + insBirth);
        Duration between = Duration.between(insBirth, insNow);
        System.out.println("Duration (days): " + between.toDays());
        System.out.println("Duration (sec.): " + between.toSeconds());
        System.out.println("Duration:" + between);

        LocalDate ldtNow = LocalDate.now();
        LocalDate ldtBirth = LocalDate.ofInstant(insBirth, ZoneId.systemDefault());
        Period ldtBetween = Period.between(ldtBirth, ldtNow);
        System.out.println("Period: " + ldtBetween);

        System.out.println("\n========================\n");
        printAllTimeZones();
        System.out.println("\n========================\n");
        ZoneId kyiv = ZoneId.of("Europe/Kiev");
        ZonedDateTime zdtNow = ZonedDateTime.now(kyiv);
        DateTimeFormatter frFormat = DateTimeFormatter.ofPattern("dd.MMM.yyyy HH:mm:ss", Locale.FRANCE);
        System.out.println("Now: " + zdtNow.format(frFormat));
        ZonedDateTime usCentral = zdtNow.withZoneSameInstant(ZoneId.of("US/Central"));
        System.out.println("US Central: " + usCentral.format(DateTimeFormatter.RFC_1123_DATE_TIME));

        ZoneOffset zoneOffset = ZoneOffset.of("+10:00");
        OffsetDateTime offsetDateTime = OffsetDateTime.now(zoneOffset);
        Locale lUa = new Locale("uk", "UA");
        System.out.println("Offset: " + offsetDateTime.format(DateTimeFormatter.ofPattern("dd MMMM yyy", lUa)));

        System.out.println("\n========================\n");
        
        List<Locale> lstLoc = List.of(Locale.getAvailableLocales());
        lstLoc.stream()
                .map(n -> n.getDisplayName() + " -> " + n.getCountry())
                .sorted()
                .forEach(System.out::println);

    }

    private static void printAllTimeZones() {
        LocalDateTime ldt = LocalDateTime.now();

        Map<String, String> result = ZoneId.getAvailableZoneIds().stream()
                .map(ZoneId::of)
                .map(zoneId -> new SimpleEntry<>(zoneId.toString(),
                        ldt.atZone(zoneId).getOffset().getId().replaceAll("Z", "00:00")))
                .sorted(Map.Entry.<String, String>comparingByKey())
                .collect(Collectors.toMap(SimpleEntry::getKey, SimpleEntry::getValue, (v1, v2) -> v1,
                        LinkedHashMap::new));

        result.forEach((k, v) -> System.out.printf("%s (%s)\n", k, v));
        System.out.println("\nTotal Zone IDs: " + result.size());

    }
}
