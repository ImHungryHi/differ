package org.hungry.differ.logic;

import org.hungry.differ.constants.Text;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DualComparatorTest {

    @Test
    void sanityCheck() {
        assertTrue(Boolean.TRUE);
    }

    @Test
    void appendDiffToOriginalFileName() {
        // Given
        String given = "0001.csv";
        String expected = "0001_diff.csv";
        DualComparator dualComparator = new DualComparator(given, Text.EMPTY);

        // When
        String actual = dualComparator.appendDiffToOriginFileName(given);

        // Then
        assertEquals(expected, actual);
    }
}