package util;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

public class DateUtilTest {

    @Test
    public void isValidYearTest() {
        var year = 2017;

        var validYear = DateUtil.isValidYear(year);

        assertTrue(validYear);
    }

    @Test
    public void isInvalidYearTest() {
        var year = 201;

        var validYear = DateUtil.isValidYear(year);

        assertFalse(validYear);
    }

    @Test
    public void testLocalDateIntervalInOneDays() {
        var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        var begin = "01/01/2019";
        var end = "02/01/2019";

        final var firstDate = LocalDate.parse(begin, formatter);
        final var secondDate = LocalDate.parse(end, formatter);

        var interval = DateUtil.getIntervalInDays(firstDate, secondDate);
        assertEquals(interval.longValue(), 1);
    }

    @Test
    public void testLocalDateIntervalInThreeDays() {
        var formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        var begin = "01/01/2019";
        var end = "04/01/2019";

        var firstDate = LocalDate.parse(begin, formatter);
        var secondDate = LocalDate.parse(end, formatter);

        var interval = DateUtil.getIntervalInDays(firstDate, secondDate);
        assertEquals(interval.longValue(), 3);
    }

    @Test
    public void testDateIntervalInOneDays() throws ParseException {
        var formatter = new SimpleDateFormat("dd/MM/yyyy");
        var begin = "01/01/2019";
        var end = "02/01/2019";
        var interval = DateUtil.getIntervalInDays(formatter.parse(begin), formatter.parse(end));
        assertEquals(interval.longValue(), 1);
    }

    @Test
    public void testDateIntervalInThreeDays() throws ParseException {
        var formatter = new SimpleDateFormat("dd/MM/yyyy");
        var begin = "01/01/2019";
        var end = "04/01/2019";
        var interval = DateUtil.getIntervalInDays(formatter.parse(begin), formatter.parse(end));
        assertEquals(interval.longValue(), 3);
    }

}
