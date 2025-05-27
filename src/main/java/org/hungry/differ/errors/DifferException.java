package org.hungry.differ.errors;

import lombok.Getter;
import org.hungry.differ.constants.Text;

@Getter
public class DifferException extends RuntimeException {

    private final String category;
    private final String identifier;

    public DifferException(String message) {
        super(message);
        this.category = Text.NOT_APPLICABLE;
        this.identifier = Text.NOT_APPLICABLE;
    }

    public DifferException(String category, String identifier, String message) {
        super(message);
        this.category = category;
        this.identifier = identifier;
    }
}