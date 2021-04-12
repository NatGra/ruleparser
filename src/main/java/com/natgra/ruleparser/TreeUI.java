package com.natgra.ruleparser;

import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.tree.ParseTree;

import javax.swing.JFrame;
import java.util.Arrays;

/**
 * Graphical User Interface for Rule Evaluation Parser.
 */
public class TreeUI {

    // suppressed public constructor
    private TreeUI() {
        throw new AssertionError();
    }

    /**
     * Main function for Graphical User Interface.
     *
     * @param args expression to be represented
     */
    public static void main(String[] args) {
        String expression = args[0];
        RuleEvaluationParser parser = RuleEvaluation.createParser(expression);
        ParseTree tree = parser.expression();
        JFrame frame = new JFrame("AST for expression: " + expression);
        TreeViewer treeViewer = new TreeViewer(Arrays.asList(parser.getRuleNames()), tree);
        treeViewer.setScale(1.5);
        frame.add(treeViewer);
        frame.setSize(640, 480);
        frame.setVisible(true);
    }
}
