package org.hungry.differ.logic;

import org.hungry.differ.constants.DifferError;
import org.hungry.differ.constants.Number;
import org.hungry.differ.constants.Text;
import org.hungry.differ.errors.DifferException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DualComparatorTest {

    private static final String ORIGIN_PATH = "src/test/resources/dual/origin.csv";
    private static final String TARGET_PATH = "src/test/resources/dual/target.csv";

    @Test
    void sanityCheck() {
        assertTrue(Boolean.TRUE);
    }

    @ParameterizedTest
    @ValueSource(ints = { -4,-1,0,1,3 })
    void should_ParseFile_AndGetLineOccurrence_CountInIncrements(int directionalIncrement) {
        // Given
        DualComparator dualComparator = new DualComparator(ORIGIN_PATH, TARGET_PATH);
        dualComparator.parseFile(ORIGIN_PATH, directionalIncrement, DifferError.IO_001);

        // When
        int actual = dualComparator.getLineOccurrence("Twas bryllig and the slithy toves");

        // Then
        assertEquals((Number.TWO * directionalIncrement), actual);
    }

    @ParameterizedTest
    @EnumSource(DifferError.class)
    void should_ParseFile_ThrowGivenError_WhenFileNotExists(DifferError differError) {
        // Given
        DualComparator dualComparator = new DualComparator(ORIGIN_PATH, TARGET_PATH);

        // When
        DifferException actual = assertThrows(DifferException.class, () -> dualComparator.parseFile("404.nonexist", Number.ONE, differError));

        // Then
        assertEquals(differError.getIdentifier(), actual.getIdentifier());
    }

    @ParameterizedTest
    @CsvSource({
            "'\u001B[30m','\u001B[40m',TARGET,2,May the force be with you,'\u001B[30m\u001B[40mTARGET [+2]: [May the force be with you]\u001B[0m\n'",
            "'\u001B[36m','\u001B[42m',ORIGIN,43,Tha i fliuch an-drasta,'\u001B[36m\u001B[42mORIGIN [+43]: [Tha i fliuch an-drasta]\u001B[0m\n'"
    })
    void should_GetColourFormattedLine(String foregroundColour, String backgroundColour,
                                       String direction, int occurrences, String line, String expected) {
        // When
        String actual = DualComparator.getColourFormattedLine(foregroundColour, backgroundColour, direction, occurrences, line);

        // Then
        assertEquals(expected, actual);
    }

    @Test
    void should_Process_WithSingleInput() {
        // Given
        DualComparator dualComparator = new DualComparator(ORIGIN_PATH, Text.EMPTY);

        // When
        dualComparator.process();
        List<String> actual = getResultOutput();

        // Then
        assertTrue(actual.contains("TARGET [+1]: [Mostly, this poem has just this line added plus the first and last paragraphs concatenated]"));
        assertTrue(actual.contains("TARGET [+2]: [Twas bryllig and the slithy toves did gyre and gimble in the wabe.]"));
        assertTrue(actual.contains("ORIGIN [+2]: [Twas bryllig and the slithy toves]"));
        assertTrue(actual.contains("ORIGIN [+2]: [did gyre and gimble in the wabe.]"));
    }

    @Test
    void should_Process_WithDualInput() {
        // Given
        DualComparator dualComparator = new DualComparator(ORIGIN_PATH, TARGET_PATH);

        // When
        dualComparator.process();
        List<String> actual = getResultOutput();

        // Then
        assertTrue(actual.contains("TARGET [+1]: [And, oh! The latter block of poetry is missing!]"));
        assertTrue(actual.contains("ORIGIN [+1]: [did gyre and gimble in the wabe.]"));
    }

    private List<String> getResultOutput() {
        List<String> allLines = new LinkedList<>();

        try {
            allLines = Files.readAllLines(Paths.get("src/test/resources/dual/origin_result.txt"));
        } catch (IOException ignored) {
            // Nope
        }

        return allLines;
    }
}