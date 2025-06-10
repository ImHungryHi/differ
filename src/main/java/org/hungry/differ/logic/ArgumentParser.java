package org.hungry.differ.logic;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hungry.differ.constants.Number;
import org.hungry.differ.constants.Parameter;
import org.hungry.differ.constants.Text;
import org.hungry.differ.interfaces.DifferProcessor;

import java.util.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ArgumentParser {

    public static DifferProcessor parseToProcessor(String[] arguments) {

        DifferProcessor processor = null;
        Set<Parameter> parameterSet = getParameters(arguments);
        List<String> fileNameSet = getFileNames(arguments);
        Iterator<String> fileNameIterator = fileNameSet.iterator();

        if (parameterSet.stream().allMatch(Parameter::isHelp)) {

            processor = new HelpPrinter();
        } else if (Parameter.isAllCsvCompare(parameterSet)) {

            processor = new AllCsvComparator(fileNameIterator.next(), getSecondFileName(fileNameIterator));
        } else if (Parameter.isCsvCompare(parameterSet)) {

            processor = new CsvComparator(fileNameIterator.next(), getSecondFileName(fileNameIterator));
        } else if (parameterSet.stream().allMatch(Parameter::isDualCompare)) {

            processor = new DualComparator(fileNameIterator.next(), getSecondFileName(fileNameIterator));
        } else if (parameterSet.stream().allMatch(Parameter::isTreeCompare)) {

            if (fileNameSet.isEmpty())
                fileNameSet.add(System.getProperty("user.dir"));

            processor = new TreeComparator(fileNameSet.getFirst());
        }

        return processor;
    }

    static Set<Parameter> getParameters(String[] arguments) {

        Set<Parameter> parameterSet = new HashSet<>();

        for (String argument : arguments) {

            if (argument.startsWith(Text.DOUBLE_HYPHEN))
                parameterSet.add(Parameter.getByLetterOrName(argument.substring(Number.TWO)));
            else if (argument.startsWith(Text.HYPHEN))
                parameterSet.addAll(convertSingleParameters(argument.substring(Number.ONE)));
        }

        return parameterSet;
    }

    private static Set<Parameter> convertSingleParameters(String argument) {
        Set<Parameter> parameterSet = new HashSet<>();

        for (char character : argument.toCharArray()) {
            parameterSet.add(Parameter.getByLetterOrName(String.valueOf(character)));
        }

        return parameterSet;
    }

    static List<String> getFileNames(String[] arguments) {

        List<String> fileNameSet = new LinkedList<>();

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
