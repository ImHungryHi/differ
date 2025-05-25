package org.hungry.differ.logic;

import org.hungry.differ.constants.Number;
import org.hungry.differ.constants.Parameter;
import org.hungry.differ.interfaces.DifferProcessor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ArgumentParserTest {

    @Test
    void sanityCheck() {
        assertTrue(Boolean.TRUE);
    }

    @ParameterizedTest
    @ValueSource(strings = { "-h", "-help" })
    void should_ParseToHelpPrinter(String parameter) {
        // Given
        String[] arguments = new String[] { parameter };

        // When
        DifferProcessor actual = ArgumentParser.parseToComparator(arguments);

        // Then
        assertInstanceOf(HelpPrinter.class, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = { "-d", "-dual-compare" })
    void should_ParseToDualComparator_WithMinimumOneFileName(String parameter) {
        // Given
        String[] arguments = new String[] { parameter, "C:/Users/UTlity/0001.csv" };

        // When
        DifferProcessor actual = ArgumentParser.parseToComparator(arguments);

        // Then
        assertInstanceOf(DualComparator.class, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = { "-t", "-tree-compare" })
    void should_ParseToTreeComparator_WithMinimalInput(String parameter) {
        // Given
        String[] arguments = new String[] { parameter };

        // When
        DifferProcessor actual = ArgumentParser.parseToComparator(arguments);

        // Then
        assertInstanceOf(TreeComparator.class, actual);
    }

    @ParameterizedTest
    @CsvSource({ "-t,-d,-help", "-dual-compare,-tree-compare,-h" })
    void should_GetParameters(String firstParameter, String secondParameter, String thirdParameter) {
        // Given
        String[] arguments = new String[] { firstParameter, secondParameter, thirdParameter };

        // When
        Set<Parameter> actual = ArgumentParser.getParameters(arguments);

        // Then
        assertEquals(Number.THREE, actual.size());
        assertTrue(actual.contains(Parameter.DUAL));
        assertTrue(actual.contains(Parameter.TREE));
        assertTrue(actual.contains(Parameter.HELP));
    }

    @ParameterizedTest
    @ValueSource(strings = { "-d", "-dual-compare" })
    void should_GetParameters_OnlyWithHyphenAtStart(String firstParameter) {
        // Given
        String[] arguments = new String[] { firstParameter, "tree-compare", "filename.csv" };

        // When
        Set<Parameter> actual = ArgumentParser.getParameters(arguments);

        // Then
        assertEquals(Number.ONE, actual.size());
        assertTrue(actual.contains(Parameter.DUAL));
        assertFalse(actual.contains(Parameter.TREE));
    }

    @ParameterizedTest
    @CsvSource({ "-t,-d", "-dual-compare,-tree-compare" })
    void getFileNames(String firstParameter, String secondParameter) {
        // Given
        String[] arguments = new String[] { firstParameter, secondParameter, "tree-compare", "filename.csv" };

        // When
        Set<String> actual = ArgumentParser.getFileNames(arguments);

        // Then
        assertEquals(Number.TWO, actual.size());
        assertTrue(actual.contains("tree-compare"));
        assertTrue(actual.contains("filename.csv"));
    }
}