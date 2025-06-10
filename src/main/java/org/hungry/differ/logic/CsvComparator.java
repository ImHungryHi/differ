package org.hungry.differ.logic;

import org.hungry.differ.constants.Text;
import org.hungry.differ.interfaces.DifferProcessor;
import org.hungry.differ.utility.TextUtility;

public class CsvComparator implements DifferProcessor {

    private final String originFilePath;
    private final String targetFilePath;

    public CsvComparator(String originFilePath, String targetFilePath) {
        if (targetFilePath.isBlank())
            targetFilePath = TextUtility.appendSuffixToOriginFileName(originFilePath, Text.DEFAULT_FILE_SUFFIX, true);

        this.originFilePath = originFilePath;
        this.targetFilePath = targetFilePath;
    }

    public void process() {
        System.out.println("Not implemented yet");
    }
}