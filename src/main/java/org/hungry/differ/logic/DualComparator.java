package org.hungry.differ.logic;

import org.hungry.differ.constants.Number;
import org.hungry.differ.constants.Text;
import org.hungry.differ.interfaces.DifferProcessor;

import java.util.HashMap;
import java.util.Map;

public class DualComparator implements DifferProcessor {

    private Map<String, Integer> lineWithOccurrence = new HashMap<>();
    private String originFileName;
    private String targetFileName;

    public DualComparator(String originFileName, String targetFileName) {
        if (targetFileName.isBlank())
            targetFileName = appendDiffToOriginFileName(originFileName);

        this.originFileName = originFileName;
        this.targetFileName = targetFileName;
    }

    public void process() {
        System.out.println("Not implemented yet");
    }

    String appendDiffToOriginFileName(String originFileName) {

        StringBuilder appendedFileName = new StringBuilder();
        int startIndexOfExtension= originFileName.lastIndexOf(Text.CHAR_PERIOD);

        appendedFileName.append(originFileName, Number.ZERO, startIndexOfExtension);
        appendedFileName.append(Text.DEFAULT_FILE_SUFFIX);
        appendedFileName.append(originFileName.substring(startIndexOfExtension));

        return appendedFileName.toString();
    }
}