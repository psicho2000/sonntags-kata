package de.psicho.sonntagskata;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class KataTest {

    private Kata kata = new Kata();

    @Test
    void first_bit_is_set_in_1() {
        boolean result = kata.isBitSet(1, 1);
        assertTrue(result);
    }

    @Test
    void second_bit_is_set_in_2() {
        boolean result = kata.isBitSet(2, 2);
        assertTrue(result);
    }

    @Test
    void third_bit_is_set_in_4() {
        boolean result = kata.isBitSet(3, 4);
        assertTrue(result);
    }

    @Test
    void eigth_bit_is_set_in_128() {
        boolean result = kata.isBitSet(8, 128);
        assertTrue(result);
    }

    @Test
    void fourth_bit_is_not_set_in_7() {
        boolean result = kata.isBitSet(4, 7);
        assertFalse(result);
    }

    @Test
    void sixth_bit_is_not_set_in_223() {
        boolean result = kata.isBitSet(6, 223);
        assertFalse(result);
    }

    @Test
    void all_bits_are_set_in_255() {
        assertTrue(kata.isBitSet(1, 255));
        assertTrue(kata.isBitSet(2, 255));
        assertTrue(kata.isBitSet(3, 255));
        assertTrue(kata.isBitSet(4, 255));
        assertTrue(kata.isBitSet(5, 255));
        assertTrue(kata.isBitSet(6, 255));
        assertTrue(kata.isBitSet(7, 255));
        assertTrue(kata.isBitSet(8, 255));
    }

    @Test
    void no_bit_is_set_in_0() {
        assertFalse(kata.isBitSet(1, 0));
        assertFalse(kata.isBitSet(2, 0));
        assertFalse(kata.isBitSet(3, 0));
        assertFalse(kata.isBitSet(4, 0));
        assertFalse(kata.isBitSet(5, 0));
        assertFalse(kata.isBitSet(6, 0));
        assertFalse(kata.isBitSet(7, 0));
        assertFalse(kata.isBitSet(8, 0));
    }

    @Test
    void create_summand_fails_if_left_equals_right() {
        assertThrows(AssertionError.class, () -> kata.createSummand(5, 5));
    }

    @Test
    void create_summand_fails_if_left_greaten_than_right() {
        assertThrows(AssertionError.class, () -> kata.createSummand(6, 5));
    }

    @Test
    void create_single_summand() {
        int summand = kata.createSummand(2, 3);
        assertEquals(3, summand);
    }

    @Test
    void create_multi_summand() {
        int summand = kata.createSummand(4, 8);
        assertEquals(5678, summand);
    }

    @Test
    void create_maximal_summand() {
        int summand = kata.createSummand(0, 9);
        assertEquals(123456789, summand);
    }

    @Test
    void create_first_summand() {
        int summand = kata.createSummand(0, 1);
        assertEquals(1, summand);
    }

    @Test
    void create_last_summand() {
        int summand = kata.createSummand(8, 9);
        assertEquals(9, summand);
    }

    @Test
    void provide_complete_instance() {
        int instance = kata.provideInstance(0);
        assertEquals(123456789, instance);
    }

    @Test
    void provide_simple_instance_at_bit_one() {
        int instance = kata.provideInstance(createDual(1));
        assertEquals(1 + 23456789, instance);
    }

    @Test
    void provide_simple_instance_at_bit_eight() {
        int instance = kata.provideInstance(createDual(8));
        assertEquals(12345678 + 9, instance);
    }

    @Test
    void provide_complex_instance_at_bit_four_and_five() {
        int instance = kata.provideInstance(createDual(4) + createDual(5));
        assertEquals(1234 + 5 + 6789, instance);
    }

    private int createDual(int bit) {
        return (1 << (bit - 1));
    }

}