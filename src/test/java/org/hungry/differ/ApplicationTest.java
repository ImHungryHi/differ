package org.hungry.differ;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.hungry.differ.constants.DifferError;
import org.hungry.differ.constants.Parameter;
import org.hungry.differ.logic.HelpPrinter;
import org.hungry.differ.test.util.UnitTestUtility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApplicationTest {

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
        assertTrue(logWatcher.list.stream().anyMatch(logItem -> logItem.getMessage().contains(Parameter.HELP.getDescription())));
    }
}