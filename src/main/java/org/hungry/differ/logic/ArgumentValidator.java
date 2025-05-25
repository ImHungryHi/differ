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
            throw new IllegalArgumentException(DifferError.ARG_001.getMessage());

        Set<Parameter> parameterSet = ArgumentParser.getParameters(arguments);

        if (parameterSet.contains(Parameter.DUAL) && arguments.length == Number.ONE)
            throw new IllegalArgumentException(DifferError.ARG_002.getMessage());

        if (parameterSet.size() != Number.ONE)
            throw new IllegalArgumentException(DifferError.ARG_003.getMessage());
    }
}