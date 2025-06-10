package org.hungry.differ.utility;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.hungry.differ.constants.Number;
import org.hungry.differ.constants.Text;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TextUtility {

    public static String appendSuffixToOriginFileName(String originFileName, String suffix, boolean shouldAppendExtension) {

        StringBuilder appendedFileName = new StringBuilder();
        int startIndexOfExtension= originFileName.lastIndexOf(Text.CHAR_PERIOD);

        appendedFileName.append(originFileName, Number.ZERO, startIndexOfExtension);
        appendedFileName.append(suffix);

        if (shouldAppendExtension)
            appendedFileName.append(originFileName.substring(startIndexOfExtension));

        return appendedFileName.toString();
    }
}