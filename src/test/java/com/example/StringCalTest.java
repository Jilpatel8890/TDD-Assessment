package com.example;

import org.junit.Test;
import static org.junit.Assert.*;

public class StringCalTest {

    @Test
    public void testEmptyStringReturnsZero() {
        assertEquals(0, StringCal.add(""));
    }

    @Test
    public void testSingleNumberReturnsItsValue() {
        assertEquals(1, StringCal.add("1"));
    }

    @Test
    public void testTwoNumbersCommaSeparatedReturnsSum() {
        assertEquals(3, StringCal.add("1,2"));
    }

    @Test
    public void testNewLinesBetweenNumbers() {
        assertEquals(6, StringCal.add("1\n2,3"));
    }

    @Test
    public void testDifferentDelimiters() {
        assertEquals(3, StringCal.add("//;\n1;2"));
    }

    @Test(expected = RuntimeException.class)
    public void testNegativeNumbersThrowException() {
        StringCal.add("-1,2");
    }

    @Test
    public void testNegativeNumberExceptionMessage() {
        try {
            StringCal.add("-1,-2");
            fail("Expected exception");
        } catch (RuntimeException e) {
            assertEquals("negative numbers not allowed -1,-2", e.getMessage());
        }
    }
}