package org.hungry.differ.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hungry.differ.errors.DifferException;

@Getter
@AllArgsConstructor
public enum DifferError {

    ARG_001("Not enough arguments provided. Call -h for more info."),
    ARG_002("Please provide at least 1 parameter [-d|-t|-h]."),
    ARG_003("For dual comparison (-d), please provide at least 1 filename."),
    ARG_004("DualCompare and TreeCompare cannot be used simultaneously."),
    ARG_005("CSV parameter cannot be used without DualCompare."),
    ARG_006("All parameter cannot be used by itself."),
    IO_001("IO error occurred during origin file read. Please check if your first file input is readable."),
    IO_002("IO error occurred during target file read. Please check if your second file input is readable."),
    IO_003("IO error occurred during result file write."),
    IO_OO4("Result file wasn't opened.");

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