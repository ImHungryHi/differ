package org.hungry.differ.logic;

import org.hungry.differ.constants.Parameter;
import org.hungry.differ.interfaces.DifferProcessor;

public class HelpPrinter implements DifferProcessor {

    public void process() {

        StringBuilder messageBuilder = new StringBuilder("\n");
        messageBuilder.append("Here's this command in a nutshell:");
        messageBuilder.append("\n- Usage: differ [-h|--help|-d|--dual-compare|-t|--tree-compare] [-c|--csv|-a|--all] [filename|directory] [second filename]");
        messageBuilder.append("\n- Help page takes no other arguments.");
        messageBuilder.append("\n\tEXCEPT if you combine with any other parameter [-t|d|c|a] to display specific help.");
        messageBuilder.append("\n- Dual compare takes 1 or 2 additional arguments.");
        messageBuilder.append("\n\tAt least 1 filename should be given, a secondary is optional - cref further.");
        messageBuilder.append("\n- CSV compare is an additional parameter for dual compare.");
        messageBuilder.append("\n\tThis will show the records with the most similar matches between the 2 files.");
        messageBuilder.append("\n- All is an additional parameter for dual compare.");
        messageBuilder.append("\n\tThis will show all records that have at least some similarities.");
        messageBuilder.append("\n- Tree compare takes at most 1 additional argument.");
        messageBuilder.append("\n\tGiving no other arguments will start a tree comparison for the current working directory.");
        messageBuilder.append("\n\tIf you want specify a directory to tree-compare against, simply provide the path.");

        messageBuilder.append("\n")
                .append("\n----------------------------------------")
                .append("\n");

        for (Parameter parameter : Parameter.values()) {
            messageBuilder.append(String.format("%n-%s|-%s -> %s", parameter.getLetter(), parameter.getName(), parameter.getDescription()));
        }

        System.out.println(messageBuilder);
    }
}