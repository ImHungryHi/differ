package org.hungry.differ.logic;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hungry.differ.constants.DifferError;
import org.hungry.differ.constants.Number;
import org.hungry.differ.constants.Parameter;

import java.util.Set;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ArgumentValidator {

    public static void validate(String[] arguments) {

        if (arguments.length < Number.ONE)
            DifferError.ARG_001.throwDifferException();

        Set<Parameter> parameterSet = ArgumentParser.getParameters(arguments);

        if (parameterSet.isEmpty())
            DifferError.ARG_002.throwDifferException();

        if (parameterSet.contains(Parameter.DUAL) && arguments.length == Number.ONE)
            DifferError.ARG_003.throwDifferException();

        if (parameterSet.contains(Parameter.DUAL) && parameterSet.contains(Parameter.TREE))
            DifferError.ARG_004.throwDifferException();

        if (parameterSet.stream().anyMatch(Parameter::isCsv)
                && parameterSet.stream().noneMatch(Parameter::isDualCompare))
            DifferError.ARG_005.throwDifferException();

        if (parameterSet.stream().anyMatch(Parameter::isAll)
                && parameterSet.stream().noneMatch(Parameter::isDualCompare))
            DifferError.ARG_006.throwDifferException();
    }
}