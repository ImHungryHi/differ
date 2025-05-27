package org.hungry.differ.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hungry.differ.errors.DifferException;

@Getter
@AllArgsConstructor
public enum DifferError {

    ARG_001("Not enough arguments provided. Call -h for more info."),
    ARG_002("For dual comparison (-d), please provide at least 1 filename."),
    ARG_003("Please provide at least 1 parameter [-d|-t|-h]."),
    IO_004("IO error occurred during origin file read. Please check if your first file input is readable."),
    IO_005("IO error occurred during target file read. Please check if your second file input is readable."),
    IO_006("IO error occurred during result file write.");

    private final String category = this.name().substring(Number.ZERO, name().lastIndexOf(Text.UNDERSCORE));
    private final String identifier = this.name().substring(name().lastIndexOf(Text.UNDERSCORE) + Number.ONE);
    private final String message;

    public void throwDifferException() {
        throw new DifferException(getCategory(), getIdentifier(), getMessage());
    }

    public DifferException getDifferException() {
        return new DifferException(getCategory(), getIdentifier(), getMessage());
    }
}