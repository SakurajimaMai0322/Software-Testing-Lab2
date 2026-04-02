package com.github.davidmoten.geo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Base32Test {

    @Test
    public void testEncodeWithBirthday() {
        String result = Base32.encodeBase32(20030322L, 5);
        assertNotNull(result);
        assertEquals(5, result.length());
    }

    @Test
    public void testPadWithPhoneNumber() {
        String phone = "0910101010";
        String result = Base32.padLeftWithZerosToLength(phone, 12);
        assertEquals("00" + phone, result);
    }

    @Test
    public void testGetCharIndexForAndy() {
        int index = Base32.getCharIndex('b');
        assertEquals(10, index);
    }

    // --- MUT 1: encodeBase32(long i, int length) ---
    @Test
    public void testEncodeBase32Positive() {
        assertEquals("0000b", Base32.encodeBase32(10, 5));
    }

    @Test
    public void testEncodeBase32Zero() {
        assertEquals("000", Base32.encodeBase32(0, 3));
    }

    @Test
    public void testEncodeBase32Negative() {
        assertEquals("-00000b", Base32.encodeBase32(-10, 6));
    }

    // --- MUT 2: decodeBase32(String hash) ---
    @Test
    public void testDecodeBase32Normal() {
        assertEquals(10, Base32.decodeBase32("b"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDecodeBase32Invalid() {
        Base32.decodeBase32("a#!");
    }

    // --- MUT 3: getCharIndex(char ch) ---
    @Test
    public void testGetCharIndexValid() {
        assertEquals(10, Base32.getCharIndex('b'));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetCharIndexInvalid() {
        Base32.getCharIndex('i');
    }

    // --- MUT 4: padLeftWithZerosToLength(String s, int length) ---
    @Test
    public void testPadLeftNecessary() {
        assertEquals("00123", Base32.padLeftWithZerosToLength("123", 5));
    }

    @Test
    public void testPadLeftNotNecessary() {
        assertEquals("12345", Base32.padLeftWithZerosToLength("12345", 3));
    }
}