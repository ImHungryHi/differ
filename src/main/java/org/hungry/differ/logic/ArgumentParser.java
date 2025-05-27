package org.hungry.differ.logic;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hungry.differ.constants.Number;
import org.hungry.differ.constants.Parameter;
import org.hungry.differ.constants.Text;
import org.hungry.differ.interfaces.DifferProcessor;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ArgumentParser {

    public static DifferProcessor parseToProcessor(String[] arguments) {

        DifferProcessor processor = null;
        Parameter parameter = getParameters(arguments).iterator().next();
        Set<String> fileNameSet = getFileNames(arguments);

        if (parameter.isHelp()) {

            processor = new HelpPrinter();
        } else if (parameter.isDualCompare()) {

            Iterator<String> fileNameIterator = fileNameSet.iterator();
            processor = new DualComparator(fileNameIterator.next(), getSecondFileName(fileNameIterator));
        } else if (parameter.isTreeCompare()) {

            if (fileNameSet.isEmpty())
                fileNameSet.add(System.getProperty("user.dir"));

            processor = new TreeComparator(fileNameSet.iterator().next());
        }

        return processor;
    }

    static Set<Parameter> getParameters(String[] arguments) {

        Set<Parameter> parameterSet = new HashSet<>();

        for (String argument : arguments) {

            if (argument.startsWith(Text.HYPHEN))
                parameterSet.add(Parameter.getByLetterOrName(argument.substring(Number.ONE)));
        }

        return parameterSet;
    }

    static Set<String> getFileNames(String[] arguments) {

        Set<String> fileNameSet = new HashSet<>();

        for (String argument : arguments) {

            if (!argument.startsWith(Text.HYPHEN))
                fileNameSet.add(argument);
        }

        return fileNameSet;
    }

    private static String getSecondFileName(Iterator<String> fileNameIterator) {

        return fileNameIterator.hasNext() ? fileNameIterator.next() : Text.EMPTY;
    }
}