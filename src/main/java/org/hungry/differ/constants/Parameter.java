package org.hungry.differ.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
@AllArgsConstructor
public enum Parameter {

    HELP("h", "help", """
            Display the message that you're currently looking at.
            \tGo ahead, see if anything changes."""),
    TREE("t", "tree-compare", """
            Execute to check a folder for duplicate files.
            \tWill look at file size, first + last bytes, checksum, filename."""),
    DUAL("d", "dual-compare", """
            Execute to compare 2 files and check for diffs.
            \tUsage is 'differ [-d|-dual-compare] file1.extension [file1_diff.extension]'.
            \tSuffix the target file with '_diff' like above to run without a second filename.""");

    private final String letter;
    private final String name;
    private final String description;

    private static final Map<String, Parameter> haystack = new HashMap<>();

    static {
        for (Parameter parameter : values()) {
            haystack.put(parameter.letter, parameter);
            haystack.put(parameter.name, parameter);
        }
    }

    public static Parameter getByLetterOrName(String needle) {
        return haystack.get(needle);
    }

    public boolean isHelp() {
        return this.equals(HELP);
    }

    public boolean isDualCompare() {
        return this.equals(DUAL);
    }

    public boolean isTreeCompare() {
        return this.equals(TREE);
    }
}
