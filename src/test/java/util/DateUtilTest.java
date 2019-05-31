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
        int year = 2017;

        boolean validYear = DateUtil.isValidYear(year);

        assertTrue(validYear);
    }

    @Test
    public void isInvalidYearTest() {
        int year = 201;

        boolean validYear = DateUtil.isValidYear(year);

        assertFalse(validYear);
    }

    @Test
    public void testLocalDateIntervalInOneDays() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String begin = "01/01/2019";
        String end = "02/01/2019";

        final LocalDate firstDate = LocalDate.parse(begin, formatter);
        final LocalDate secondDate = LocalDate.parse(end, formatter);

        long interval = DateUtil.getIntervalInDays(firstDate, secondDate);
        assertEquals(1, interval);
    }

    @Test
    public void testLocalDateIntervalInThreeDays() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String begin = "01/01/2019";
        String end = "04/01/2019";

        LocalDate firstDate = LocalDate.parse(begin, formatter);
        LocalDate secondDate = LocalDate.parse(end, formatter);

        long interval = DateUtil.getIntervalInDays(firstDate, secondDate);
        assertEquals(3, interval);
    }

    @Test
    public void testDateIntervalInOneDays() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String begin = "01/01/2019";
        String end = "02/01/2019";
        long interval = DateUtil.getIntervalInDays(formatter.parse(begin), formatter.parse(end));
        assertEquals(1, interval);
    }

    @Test
    public void testDateIntervalInThreeDays() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String begin = "01/01/2019";
        String end = "04/01/2019";
        long interval = DateUtil.getIntervalInDays(formatter.parse(begin), formatter.parse(end));
        assertEquals(3, interval);
    }

}
