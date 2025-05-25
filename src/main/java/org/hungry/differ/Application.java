package org.hungry.differ;

import org.hungry.differ.interfaces.DifferProcessor;
import org.hungry.differ.logic.ArgumentParser;
import org.hungry.differ.logic.ArgumentValidator;

public class Application {
    public static void main(String[] arguments) {
        ArgumentValidator.validate(arguments);
        DifferProcessor processor = ArgumentParser.parseToComparator(arguments);
        processor.process();
    }
}