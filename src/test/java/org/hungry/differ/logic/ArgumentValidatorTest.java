package org.hungry.differ.logic;

import org.hungry.differ.constants.DifferError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class ArgumentValidatorTest {

    @Test
    void sanityCheck() {
        assertTrue(Boolean.TRUE);
    }

    @Test
    void should_ThrowARG001_WhenArgumentCount_IsZero() {
        // When
        IllegalArgumentException actual = assertThrows(IllegalArgumentException.class,
                () -> ArgumentValidator.validate(new String[0]));

        // Then
        assertEquals(DifferError.ARG_001.getMessage(), actual.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = { "-d", "-dual-compare" })
    void should_ThrowARG002_WhenArgumentCount_IsOne_AndArgument_IsDual(String argument) {
        // When
        IllegalArgumentException actual = assertThrows(IllegalArgumentException.class,
                () -> ArgumentValidator.validate(new String[] { argument }));

        // Then
        assertEquals(DifferError.ARG_002.getMessage(), actual.getMessage());
    }

    @ParameterizedTest
    @CsvSource({ "-d,-t", "-t,-dual-compare", "-tree-compare,-d", "-tree-compare,-dual-compare" })
    void should_ThrowARG003_WhenIncompatibleParameters_WereGiven(String firstArgument, String secondArgument) {
        // When
        IllegalArgumentException actual = assertThrows(IllegalArgumentException.class,
                () -> ArgumentValidator.validate(new String[] { firstArgument, secondArgument }));

        // Then
        assertEquals(DifferError.ARG_003.getMessage(), actual.getMessage());
    }
}