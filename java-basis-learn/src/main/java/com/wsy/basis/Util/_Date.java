package com.wsy.basis.Util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

/**
 * LocalDateTime <- Calender
 * Instant <- Date
 * DateTimeFormatter <- SimpleDateFormat
 */
public class _Date {
    public void testClock(){
        Clock clock = Clock.systemDefaultZone();
        long millis = clock.millis();
        System.out.println(millis);
        Instant instant = clock.instant();
        System.out.println(instant);
        Date from = Date.from(instant);
        System.out.println(from);
    }
    public void testZones(){
        System.out.println(ZoneId.getAvailableZoneIds());
        ZoneId zone1 = ZoneId.of("Europe/Berlin");
        ZoneId zone2 = ZoneId.of("Brazil/East");
        System.out.println(zone1.getRules());
        System.out.println(zone2.getRules());

    }
    public void testLocalTime(){
        ZoneId zone0 = ZoneId.of("Asia/Shanghai");
        ZoneId zone1 = ZoneId.of("Europe/Berlin");
        ZoneId zone2 = ZoneId.of("Brazil/East");
        LocalTime now0 = LocalTime.now(zone0);
        LocalTime now1 = LocalTime.now(zone1);
        LocalTime now2 = LocalTime.now(zone2);
        System.out.println(now0);
        System.out.println(now1);
        System.out.println(now2);
        System.out.println(now1.isBefore(now2));
        long hourBetween = ChronoUnit.HOURS.between(now1, now2);
        long minutesBetween = ChronoUnit.MINUTES.between(now1, now2);
        System.out.println(hourBetween);
        System.out.println(minutesBetween);

        LocalTime late = LocalTime.of(23, 59, 59);
        System.out.println(late);
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).withLocale(Locale.GERMAN);
        LocalTime parse = LocalTime.parse("13:37", formatter);
        System.out.println(parse);
    }
    public void testLocalDate(){
        LocalDate now = LocalDate.now();
        System.out.println(now);
        LocalDate tomorrow = now.plus(1, ChronoUnit.DAYS);
        System.out.println(tomorrow);
        LocalDate yesterday = tomorrow.minusDays(2);
        System.out.println(yesterday);
        LocalDate year = LocalDate.of(2019, 12, 12);
        DayOfWeek dayOfWeek = year.getDayOfWeek();
        int value = dayOfWeek.getValue();
        System.out.println(value);
        System.out.println(dayOfWeek);

    }
    public void testFormat(){
        String str1 = "2014==04==12 01时06分09秒";
        DateTimeFormatter formatter = DateTimeFormatter
                .ofPattern("yyyy==MM==dd HH时mm分ss秒");
        LocalDateTime parse = LocalDateTime.parse(str1, formatter);
        System.out.println(parse);

        String str2 = "2014$$$四月$$$13 20小时";
        DateTimeFormatter fomatter2 = DateTimeFormatter
                .ofPattern("yyy$$$MMM$$$dd HH小时");
        LocalDateTime dt2 = LocalDateTime.parse(str2, fomatter2);
        System.out.println(dt2); // 输出 2014-04-13T20:00
    }
    public void testFormat1(){
        LocalDateTime now = LocalDateTime.now();
        String date = DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(now);
        System.out.println(date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(formatter.format(now));


    }
    public void testFormat2(){
        LocalDateTime rightNow = LocalDateTime.of(2020, 12, 31, 12, 0, 0);
        String date= DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(rightNow);
// 2020-12-31T12:00:00
        System.out.println(date);
        DateTimeFormatter formatterOfYYYY = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");
// 2021-12-31 12:00:00
        System.out.println(formatterOfYYYY.format(rightNow));

        DateTimeFormatter formatterOfYyyy = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
// 2020-12-31 12:00:00
        System.out.println(formatterOfYyyy.format(rightNow));
    }
    public static void main(String[] args) {
        _Date date = new _Date();
        date.testFormat2();
    }
}
