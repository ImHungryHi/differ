package org.hungry.differ.logic;

import lombok.extern.slf4j.Slf4j;
import org.hungry.differ.constants.Parameter;
import org.hungry.differ.interfaces.DifferProcessor;

@Slf4j
public class HelpPrinter implements DifferProcessor {

    public void process() {

        StringBuilder messageBuilder = new StringBuilder("\n");
        messageBuilder.append("Here's this command in a nutshell:");
        messageBuilder.append("\n- Usage: differ [-h|-help|-d|-dual-compare|-t|-tree-compare] [filename|directory] [second filename]");
        messageBuilder.append("\n- Help page takes no other arguments. " +
                "EXCEPT if you combine with any other parameter [-t|-d] to display specific help.");
        messageBuilder.append("\n- Dual compare takes 1 or 2 additional arguments. " +
                "At least 1 filename should be given, a secondary is optional - cref further.");
        messageBuilder.append("\n- Tree compare takes at most 1 additional argument. " +
                "Giving no other arguments will start a tree comparison for the current working directory. " +
                "If you want specify a directory to tree-compare against, simply provide the path.");

        messageBuilder.append("\n")
                .append("\n----------------------------------------")
                .append("\n");

        for (Parameter parameter : Parameter.values()) {
            messageBuilder.append(String.format("%n-%s | -%s -> %s", parameter.getName(), parameter.getLetter(), parameter.getDescription()));
        }

        log.info(messageBuilder.toString());
        System.out.println(messageBuilder);
    }
}