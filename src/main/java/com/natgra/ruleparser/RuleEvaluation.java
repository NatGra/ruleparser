package com.natgra.ruleparser;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.Set;

/**
 * Rule Evaluation Parser.
 * @author  natgra
 * @version 0.0.1
 */
public class RuleEvaluation extends RuleEvaluationBaseVisitor<Boolean> {

    private Set<String> vehicleSaCodes;

    private RuleEvaluation(Set<String> vehicleSaCodes) {
        this.vehicleSaCodes = vehicleSaCodes;
    }

    /**
     * Method to evaluate an expression against a set of vehicle codes, that returns a boolean value.
     *
     * @param expression    an expression, e.g. "235 & (411 | 425)"
     * @param vehicleCodes  a set of vehicle codes, e.g. "[48, 118, 122]"
     * @return result of evaluation as boolean value
     */
    public static Boolean evaluate(String expression, Set<String> vehicleCodes) {
        RuleEvaluationParser parser = createParser(expression);
        ParseTree tree = parser.expression();
        return new RuleEvaluation(vehicleCodes).visit(tree);
    }

    /**
     * Constructor for RuleEvaluationParser.
     * The RuleEvaluationParser is generated from main/antlr4/com/natgra/ruleparser/RuleEvaluation.g4 by ANTLR 4.7.
     *
     * @param expression    an expression, e.g. "235 & (411 | 425)"
     * @return a new RuleEvaluationParser
     */
    static RuleEvaluationParser createParser(String expression) {
        CharStream stream = CharStreams.fromString(expression);
        RuleEvaluationLexer lexer = new RuleEvaluationLexer(stream);
        TokenStream tokenStream = new CommonTokenStream(lexer);
        return new RuleEvaluationParser(tokenStream);
    }

    @Override
    public Boolean visitID(RuleEvaluationParser.IDContext ctx) {
        for (String saCode : vehicleSaCodes) {
            if (saCode.equalsIgnoreCase(ctx.IDENTIFIER().getText())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean visitAndOr(RuleEvaluationParser.AndOrContext ctx) {
        if (ctx.AND() != null) {
            return visit(ctx.left) && visit(ctx.right);
        } else {
            return visit(ctx.left) || visit(ctx.right);
        }
    }

    @Override
    public Boolean visitNot(RuleEvaluationParser.NotContext ctx) {
        return !visit(ctx.expression());
    }

    @Override
    public Boolean visitParenthesis(RuleEvaluationParser.ParenthesisContext ctx) {
        return visit(ctx.expression());
    }
}
