package org.hungry.differ.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Text {

    public static final String EMPTY = "";
    public static final String SPACE = " ";
    public static final String HYPHEN = "-";
    public static final String UNDERSCORE = "_";
    public static final String NOT_APPLICABLE = "NA";

    public static final String ORIGIN = "ORIGIN";
    public static final String TARGET = "TARGET";

    public static final String DEFAULT_FILE_SUFFIX = "_diff";
    public static final String RESULT_FILE_SUFFIX = "_result.txt";

    public static final char CHAR_PERIOD = '.';
}
