package org.hungry.differ.interfaces;

import org.hungry.differ.constants.Parameter;

import java.util.HashSet;
import java.util.Set;

public interface DifferProcessor {

    Set<Parameter> parameterSet = new HashSet<>();

    void process();
}