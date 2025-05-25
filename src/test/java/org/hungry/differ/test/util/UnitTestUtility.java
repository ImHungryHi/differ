package org.hungry.differ.test.util;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.slf4j.LoggerFactory;

public class UnitTestUtility {

    public static ListAppender<ILoggingEvent> createLogWatcher(Class<?> clazz) {
        ListAppender<ILoggingEvent> logWatcher= new ListAppender<>();
        logWatcher.start();
        ((Logger) LoggerFactory.getLogger(clazz)).addAppender(logWatcher);
        return logWatcher;
    }
}