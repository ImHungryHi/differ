package org.hungry.differ.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Getter
@AllArgsConstructor
public enum Parameter {

    HELP("h", "help", """
            Display the message that you're currently looking at.
            \tGo ahead, see if anything changes."""),
    TREE("t", "tree-compare", """
            Execute to check a folder for duplicate files.
            \tWill look at file size, first + last bytes, checksum, filename.
            \tUsage is 'differ [-t|--tree-compare] [path]."""),
    DUAL("d", "dual-compare", """
            Execute to compare 2 files and check for diffs.'.
            \tSuffix the target file with '_diff' like below to run without a second filename.
            \tUsage is 'differ [-d|--dual-compare] file1.extension [file1_diff.extension]"""),
    CSV("c", "csv", """
            Include to treat the 2 files as csv and find the most matching elements.
            \tUsage is 'differ [-dc|-d --csv] file1.extension [file1_diff.extension]'."""),
    ALL("a", "all", """
            Include in command to list all possible matches between 2 files.
            \tUsage is 'differ [-dca|--dual-compare --csv --all] file1.extension [file1_diff.extension]'.""");

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

    public static boolean isCsvCompare(Set<Parameter> parameterSet) {
        return parameterSet.contains(DUAL) && parameterSet.contains(CSV);
    }

    public static boolean isAllCsvCompare(Set<Parameter> parameterSet) {
        return parameterSet.contains(DUAL) && parameterSet.contains(CSV) && parameterSet.contains(ALL);
    }

    public boolean isCsv() {
        return this.equals(CSV);
    }

    public boolean isAll() {
        return this.equals(ALL);
    }

    public boolean isDualCompare() {
        return this.equals(DUAL);
    }

    public boolean isTreeCompare() {
        return this.equals(TREE);
    }
}