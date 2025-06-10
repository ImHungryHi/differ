package org.hungry.differ.logic;

import org.hungry.differ.constants.Parameter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class HelpPrinterTest {

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream logWatcher = new ByteArrayOutputStream();

    @BeforeEach
    void setup() {
        System.setOut(new PrintStream(logWatcher));
    }

    @AfterEach
    void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    void sanityCheck() {
        assertTrue(Boolean.TRUE);
    }

    @Test
    void should_PrintHelpPrinterHelp() {
        // Given
        HelpPrinter helpPrinter = new HelpPrinter();

        // When
        helpPrinter.process();

        // Then
        assertTrue(logWatcher.toString().contains(Parameter.HELP.getDescription()));
    }

    @Test
    void should_PrintDualComparatorHelp() {
        // Given
        HelpPrinter helpPrinter = new HelpPrinter();

        // When
        helpPrinter.process();

        // Then
        assertTrue(logWatcher.toString().contains(Parameter.DUAL.getDescription()));
    }

    @Test
    void should_PrintCsvParameterHelp() {
        // Given
        HelpPrinter helpPrinter = new HelpPrinter();

        // When
        helpPrinter.process();

        // Then
        assertTrue(logWatcher.toString().contains(Parameter.CSV.getDescription()));
    }

    @Test
    void should_PrintAllParameterHelp() {
        // Given
        HelpPrinter helpPrinter = new HelpPrinter();

        // When
        helpPrinter.process();

        // Then
        assertTrue(logWatcher.toString().contains(Parameter.ALL.getDescription()));
    }

    @Test
    void should_PrintTreeComparatorHelp() {
        // Given
        HelpPrinter helpPrinter = new HelpPrinter();

        // When
        helpPrinter.process();

        // Then
        assertTrue(logWatcher.toString().contains(Parameter.TREE.getDescription()));
    }
}