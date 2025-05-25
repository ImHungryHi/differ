package org.hungry.differ;

import org.hungry.differ.constants.DifferError;
import org.hungry.differ.constants.Parameter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream logWatcher = new ByteArrayOutputStream();

    @BeforeEach
    void setup() {
        System.setOut(new PrintStream(logWatcher));
    }

    @AfterEach
    void teardown() {
        System.setOut(standardOut);
    }

    @Test
    void sanityCheck() {
        assertTrue(Boolean.TRUE);
    }

    @Test
    void should_Validate() {
        // When
        IllegalArgumentException actual = assertThrows(IllegalArgumentException.class,
                () -> Application.main(new String[0]));

        // Then
        assertEquals(DifferError.ARG_001.getMessage(), actual.getMessage());
    }

    @Test
    void should_Process() {
        // When
        Application.main(new String[]{ "-h" });

        // Then
        assertTrue(logWatcher.toString().contains(Parameter.HELP.getDescription()));
    }
}