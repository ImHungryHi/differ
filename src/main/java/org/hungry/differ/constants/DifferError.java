package org.hungry.differ.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DifferError {

    ARG_001("Not enough arguments provided. Call -h for more info."),
    ARG_002("For dual comparison (-d), please provide at least 1 filename."),
    ARG_003("Please provide at least 1 parameter [-d|-t|-h].");

    private final String message;

    public String getCode() {
        return this.name();
    }
}
