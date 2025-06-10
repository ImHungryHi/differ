package org.hungry.differ.logic;

import org.hungry.differ.constants.Number;
import org.hungry.differ.constants.Parameter;
import org.hungry.differ.interfaces.DifferProcessor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ArgumentParserTest {

    @Test
    void sanityCheck() {
        assertTrue(Boolean.TRUE);
    }

    @ParameterizedTest
    @ValueSource(strings = { "-h", "--help" })
    void should_ParseToHelpPrinter(String parameter) {
        // Given
        String[] arguments = new String[] { parameter };

        // When
        DifferProcessor actual = ArgumentParser.parseToProcessor(arguments);

        // Then
        assertInstanceOf(HelpPrinter.class, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = { "-d", "--dual-compare" })
    void should_ParseToDualComparator_WithMinimumOneFileName(String parameter) {
        // Given
        String[] arguments = new String[] { parameter, "C:/Users/UTlity/0001.csv" };

        // When
        DifferProcessor actual = ArgumentParser.parseToProcessor(arguments);

        // Then
        assertInstanceOf(DualComparator.class, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "-dc C:/Users/UTlity/0001.csv",
            "-d -c C:/Users/UTlity/0001.csv",
            "--dual-compare -c C:/Users/UTlity/0001.csv",
            "-d --csv C:/Users/UTlity/0001.csv",
            "--dual-compare --csv C:/Users/UTlity/0001.csv" })
    void should_ParseToCsvComparator_WithMinimumOneFileName(String parameter) {
        // Given
        String[] arguments = parameter.split(" ");

        // When
        DifferProcessor actual = ArgumentParser.parseToProcessor(arguments);

        // Then
        assertInstanceOf(CsvComparator.class, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "-dca C:/Users/UTlity/0001.csv",
            "-d -c -a C:/Users/UTlity/0001.csv",
            "--dual-compare -c --all C:/Users/UTlity/0001.csv",
            "-d --csv -a C:/Users/UTlity/0001.csv",
            "--dual-compare --csv --all C:/Users/UTlity/0001.csv" })
    void should_ParseToAllCsvComparator_WithMinimumOneFileName(String parameter) {
        // Given
        String[] arguments = parameter.split(" ");

        // When
        DifferProcessor actual = ArgumentParser.parseToProcessor(arguments);

        // Then
        assertInstanceOf(AllCsvComparator.class, actual);
    }

    @ParameterizedTest
    @ValueSource(strings = { "-t", "--tree-compare" })
    void should_ParseToTreeComparator_WithMinimalInput(String parameter) {
        // Given
        String[] arguments = new String[] { parameter };

        // When
        DifferProcessor actual = ArgumentParser.parseToProcessor(arguments);

        // Then
        assertInstanceOf(TreeComparator.class, actual);
    }

    @ParameterizedTest
    @CsvSource({ "-t,-d,--help,-a,--csv", "--dual-compare,--tree-compare,-h,--all,-c" })
    void should_GetParameters(String first, String second, String third, String fourth, String fifth) {
        // Given
        String[] arguments = new String[] { first, second, third, fourth, fifth };

        // When
        Set<Parameter> actual = ArgumentParser.getParameters(arguments);

        // Then
        assertEquals(Number.FIVE, actual.size());
        assertTrue(actual.contains(Parameter.DUAL));
        assertTrue(actual.contains(Parameter.TREE));
        assertTrue(actual.contains(Parameter.HELP));
        assertTrue(actual.contains(Parameter.ALL));
        assertTrue(actual.contains(Parameter.CSV));
    }

    @ParameterizedTest
    @ValueSource(strings = { "-d", "--dual-compare" })
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
    @CsvSource({ "-t,-d", "--dual-compare,--tree-compare" })
    void getFileNames(String firstParameter, String secondParameter) {
        // Given
        String[] arguments = new String[] { firstParameter, secondParameter, "tree-compare", "filename.csv" };

        // When
        List<String> actual = ArgumentParser.getFileNames(arguments);

        // Then
        assertEquals(Number.TWO, actual.size());
        assertEquals("tree-compare", actual.get(Number.ZERO));
        assertEquals("filename.csv", actual.get(Number.ONE));
    }
}
