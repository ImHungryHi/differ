package org.hungry.differ.logic;

import org.hungry.differ.constants.*;
import org.hungry.differ.constants.Number;
import org.hungry.differ.interfaces.DifferProcessor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class DualComparator implements DifferProcessor {

    private final SortedMap<String, Integer> lineWithOccurrence = new TreeMap<>();
    private final String originFilePath;
    private final String targetFilePath;

    private static final String LINE_FORMAT = "%s [+%s]: [%s]";

    public DualComparator(String originFilePath, String targetFilePath) {
        if (targetFilePath.isBlank())
            targetFilePath = appendSuffixToOriginFileName(originFilePath, Text.DEFAULT_FILE_SUFFIX, true);

        this.originFilePath = originFilePath;
        this.targetFilePath = targetFilePath;
    }

    public void process() {
        parseFile(originFilePath, Number.ONE, DifferError.IO_004);
        parseFile(targetFilePath, Number.MINUS_ONE, DifferError.IO_005);

        printResult();
    }

    void parseFile(String filePath, int modifier, DifferError differError) {
        try {
            List<String> allLines = Files.readAllLines(Paths.get(filePath));

            for (String line : allLines) {
                lineWithOccurrence.put(line, getLineOccurrence(line) + modifier);
            }
        } catch (IOException ioException) {
            differError.throwDifferException();
        }
    }

    int getLineOccurrence(String line) {
        return lineWithOccurrence.getOrDefault(line, Number.ZERO);
    }

    static String appendSuffixToOriginFileName(String originFileName, String suffix, boolean shouldAppendExtension) {

        StringBuilder appendedFileName = new StringBuilder();
        int startIndexOfExtension= originFileName.lastIndexOf(Text.CHAR_PERIOD);

        appendedFileName.append(originFileName, Number.ZERO, startIndexOfExtension);
        appendedFileName.append(suffix);

        if (shouldAppendExtension)
            appendedFileName.append(originFileName.substring(startIndexOfExtension));

        return appendedFileName.toString();
    }

    private void printResult() {
        StringBuilder screenOutputBuilder = new StringBuilder();
        StringBuilder fileOutputBuilder = new StringBuilder();

        lineWithOccurrence.forEach((line, occurrences) -> {
            if (occurrences < Number.ZERO) {

                screenOutputBuilder.append(getColourFormattedLine(ANSIColour.RED, ANSIBackgroundColour.CYAN,
                        Text.TARGET, occurrences, line));
                fileOutputBuilder.append(String.format(LINE_FORMAT, Text.TARGET, -occurrences, line)).append("\n");
            } else if (occurrences > Number.ZERO) {

                screenOutputBuilder.append(getColourFormattedLine(ANSIColour.BLACK, ANSIBackgroundColour.YELLOW,
                        Text.ORIGIN, occurrences, line));
                fileOutputBuilder.append(String.format(LINE_FORMAT, Text.ORIGIN, occurrences, line)).append("\n");
            }
        });

        System.out.println(screenOutputBuilder);

        try {
            String resultFilePath = appendSuffixToOriginFileName(originFilePath, Text.RESULT_FILE_SUFFIX, false);
            Files.writeString(Paths.get(resultFilePath), fileOutputBuilder.toString());
        } catch (IOException ioException) {
            DifferError.IO_006.throwDifferException();
        }
    }

    static String getColourFormattedLine(String foregroundColour, String backGroundColour,
                                  String direction, int occurrences, String line) {

        return new StringBuilder(String.format("%s%s", foregroundColour, backGroundColour))
                .append(String.format(LINE_FORMAT, direction, occurrences, line))
                .append(ANSIColour.DEFAULT)
                .append("\n").toString();
    }
}
