package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class DateUtil {

    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);
    public static final String OUTPUT_SIMPLE_PATTERN = "dd/MM/yyyy";

    private DateUtil() {
        throw new IllegalAccessError("Utility class");
    }

    public static Date strToDate(final String strDate) {
        return strToDate(strDate, OUTPUT_SIMPLE_PATTERN);
    }

    public static Date strToDate(final String strDate, final String pattern) {
        try {
            final var dateFormat = new SimpleDateFormat(pattern);
            return dateFormat.parse(strDate);
        } catch (ParseException e) {
            logger.error("Error on date parser: " + strDate, e);
        }

        return null;
    }

    public static String dateToStr(final Date date) {
        return dateToStr(date, OUTPUT_SIMPLE_PATTERN);
    }

    public static String dateToStr(final Date date, final String pattern) {
        if (date != null) {
            final var df = new SimpleDateFormat(pattern);
            return df.format(date);
        }

        return null;
    }

    public static String getDatePlustDay(final String dateStr, final Integer days) {
        if (days == null) return "0";
        var pattern = DateTimeFormatter.ofPattern(OUTPUT_SIMPLE_PATTERN);
        var localDate = LocalDate.parse(dateStr, pattern).plusDays(days);
        return localDate.format(pattern);
    }

    public static String getDatePlustDay(final Date date, final Integer days) {
        if (days == null) return "0";
        final var calendar = Calendar.getInstance();

        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);

        return dateToStr(calendar.getTime());
    }

    public static String extractCalendarValue(final Date date, final Integer item) {
        final var calendar = Calendar.getInstance();
        calendar.setTime(date);
        return Integer.toString(calendar.get(item));
    }

    public static boolean isValidYear(final Integer year) {
        var isValid = false;
        try {
            final var pattern = DateTimeFormatter.ofPattern(OUTPUT_SIMPLE_PATTERN);
            LocalDate.parse("01/01/" + year, pattern);
            isValid = true;
        } catch (final DateTimeParseException e) {
            logger.info("Date Parser Error", e);
        }

        return isValid;
    }

    public static Long getIntervalInDays(final LocalDate begin, final LocalDate end) {
        if(Objects.nonNull(begin) && Objects.nonNull(end)) {
            return ChronoUnit.DAYS.between(begin, end);
        }
        return 0L;
    }

    public static Long getIntervalInDays(final Date begin, final Date end) {
        var calendar = Calendar.getInstance();
        if(Objects.nonNull(begin) && Objects.nonNull(end)) {
            calendar.setTime(end);
            var localBegin = Instant.ofEpochMilli(begin.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
            var localEnd = Instant.ofEpochMilli(calendar.getTime().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
            return ChronoUnit.DAYS.between(localBegin, localEnd);
        }
        return 0L;
    }

}
