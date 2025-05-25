package org.hungry.differ.logic;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.hungry.differ.constants.Parameter;
import org.hungry.differ.test.util.UnitTestUtility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelpPrinterTest {

    ListAppender<ILoggingEvent> logWatcher = null;

    @BeforeEach
    void setup() {
        logWatcher = UnitTestUtility.createLogWatcher(HelpPrinter.class);
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
        assertTrue(logWatcher.list.stream().anyMatch(logItem -> logItem.getMessage().contains(Parameter.HELP.getDescription())));
    }

    @Test
    void should_PrintDualComparatorHelp() {
        // Given
        HelpPrinter helpPrinter = new HelpPrinter();

        // When
        helpPrinter.process();

        // Then
        assertTrue(logWatcher.list.stream().anyMatch(logItem -> logItem.getMessage().contains(Parameter.DUAL.getDescription())));
    }

    @Test
    void should_PrintTreeComparatorHelp() {
        // Given
        HelpPrinter helpPrinter = new HelpPrinter();

        // When
        helpPrinter.process();

        // Then
        assertTrue(logWatcher.list.stream().anyMatch(logItem -> logItem.getMessage().contains(Parameter.TREE.getDescription())));
    }
}