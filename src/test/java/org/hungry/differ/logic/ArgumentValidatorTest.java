package org.hungry.differ.logic;

import org.hungry.differ.constants.DifferError;
import org.hungry.differ.errors.DifferException;
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
        DifferException actual = assertThrows(DifferException.class,
                () -> ArgumentValidator.validate(new String[0]));

        // Then
        assertEquals(DifferError.ARG_001.getCategory(), actual.getCategory());
        assertEquals(DifferError.ARG_001.getIdentifier(), actual.getIdentifier());
        assertEquals(DifferError.ARG_001.getMessage(), actual.getMessage());
    }

    @Test
    void should_ThrowARG002_WhenArgumentCount_IsOne_AndParameterCount_IsNone() {
        // When
        DifferException actual = assertThrows(DifferException.class,
                () -> ArgumentValidator.validate(new String[] { "insufficient" }));

        // Then
        assertEquals(DifferError.ARG_002.getCategory(), actual.getCategory());
        assertEquals(DifferError.ARG_002.getIdentifier(), actual.getIdentifier());
        assertEquals(DifferError.ARG_002.getMessage(), actual.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = { "-d", "-dual-compare" })
    void should_ThrowARG003_WhenArgumentCount_IsOne_AndArgument_IsDual(String argument) {
        // When
        DifferException actual = assertThrows(DifferException.class,
                () -> ArgumentValidator.validate(new String[] { argument }));

        // Then
        assertEquals(DifferError.ARG_003.getCategory(), actual.getCategory());
        assertEquals(DifferError.ARG_003.getIdentifier(), actual.getIdentifier());
        assertEquals(DifferError.ARG_003.getMessage(), actual.getMessage());
    }

    @ParameterizedTest
    @CsvSource({ "-d,-t", "-t,-dual-compare", "-tree-compare,-d", "-tree-compare,-dual-compare" })
    void should_ThrowARG004_WhenIncompatibleParameters_WereGiven(String firstArgument, String secondArgument) {
        // When
        DifferException actual = assertThrows(DifferException.class,
                () -> ArgumentValidator.validate(new String[] { firstArgument, secondArgument }));

        // Then
        assertEquals(DifferError.ARG_004.getCategory(), actual.getCategory());
        assertEquals(DifferError.ARG_004.getIdentifier(), actual.getIdentifier());
        assertEquals(DifferError.ARG_004.getMessage(), actual.getMessage());
    }

    @ParameterizedTest
    @CsvSource({ "-ac", "-c", "--csv" })
    void should_ThrowARG005_WhenCsvGiven_WithoutDual(String argument) {
        // When
        DifferException actual = assertThrows(DifferException.class,
                () -> ArgumentValidator.validate(new String[] { argument }));

        // Then
        assertEquals(DifferError.ARG_005.getCategory(), actual.getCategory());
        assertEquals(DifferError.ARG_005.getIdentifier(), actual.getIdentifier());
        assertEquals(DifferError.ARG_005.getMessage(), actual.getMessage());
    }

    @ParameterizedTest
    @CsvSource({ "-a", "--all" })
    void should_ThrowARG005_WhenAllGiven_WithoutDual(String argument) {
        // When
        DifferException actual = assertThrows(DifferException.class,
                () -> ArgumentValidator.validate(new String[] { argument }));

        // Then
        assertEquals(DifferError.ARG_006.getCategory(), actual.getCategory());
        assertEquals(DifferError.ARG_006.getIdentifier(), actual.getIdentifier());
        assertEquals(DifferError.ARG_006.getMessage(), actual.getMessage());
    }
}