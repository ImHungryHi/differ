package org.hungry.differ.utility;

import org.hungry.differ.constants.Text;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class TextUtilityTest {

    @Test
    void sanityCheck() {
        assertTrue(Boolean.TRUE);
    }

    @ParameterizedTest
    @CsvSource({ Text.DEFAULT_FILE_SUFFIX + ",true,0001_diff.csv", Text.RESULT_FILE_SUFFIX + ",false,0001_result.txt" })
    void should_AppendSuffixToOriginalFileName(String suffix, boolean shouldAppendExtension, String expected) {
        // Given
        String given = "0001.csv";

        // When
        String actual = TextUtility.appendSuffixToOriginFileName(given, suffix, shouldAppendExtension);

        // Then
        assertEquals(expected, actual);
    }
}