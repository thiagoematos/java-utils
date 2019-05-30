package util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
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
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        if (strDate != null) {
            try {
                date = dateFormat.parse(strDate);
            } catch (ParseException e) {
                logger.error("Error on date parser: " + strDate, e);
            }
        }

        return date;
    }

    public static Date strToDate(final String strDate, final String pattern) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = dateFormat.parse(strDate);
        } catch (ParseException e) {
            logger.error("Error on date parser: " + strDate, e);
        }

        return date;
    }

    public static String dateToStr(final Date date) {
        String strDate = null;
        if (date != null) {

            final DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            strDate = df.format(date);
        }

        return strDate;
    }

    public static String dateToStr(final Date date, final String pattern) {
        String strDate = null;
        if (date != null) {
            final DateFormat df = new SimpleDateFormat(pattern);
            strDate = df.format(date);
        }

        return strDate;
    }

    public static String getDatePlustDay(final String dateStr, final Integer days) {
        if (days == null) return "0";
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern(OUTPUT_SIMPLE_PATTERN);
        final LocalDate localDate = LocalDate.parse(dateStr, pattern).plusDays(days);
        return localDate.format(pattern);
    }

    public static String getDatePlustDay(final Date date, final Integer days) {
        if (days == null) return "0";
        final Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);

        return dateToStr(calendar.getTime());
    }

    public static String extractCalendarValue(final Date date, final Integer item) {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return Integer.toString(calendar.get(item));
    }

    public static boolean isValidYear(final Integer year) {
        boolean isValid = false;
        try {
            final DateTimeFormatter pattern = DateTimeFormatter.ofPattern(OUTPUT_SIMPLE_PATTERN);
            LocalDate.parse("01/01/" + year, pattern);
            isValid = true;
        } catch (final DateTimeParseException e) {
            logger.error("Date Parser Error", e);
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
        Calendar calendar = Calendar.getInstance();
        if(Objects.nonNull(begin) && Objects.nonNull(end)) {
            calendar.setTime(end);
            LocalDate localBegin = Instant.ofEpochMilli(begin.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate localEnd = Instant.ofEpochMilli(calendar.getTime().getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
            return ChronoUnit.DAYS.between(localBegin, localEnd);
        }
        return 0L;
    }

}
