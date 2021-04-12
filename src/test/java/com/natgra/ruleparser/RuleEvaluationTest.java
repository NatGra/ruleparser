package com.natgra.ruleparser;

import org.junit.Test;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class RuleEvaluationTest {

    private static final String RULE_1 = "!(48 & 50 & 56 & 110 & 111 & 117 & 118 & 122)";
    private static final String RULE_2 = "235 & (421 | 426 | 427 | 429)";

    @Test
    public void evaluation_of_RULE_1_successful() {
        Set<String> saCodes = Stream.of("112", "48", "50", "56", "110", "111", "117", "118", "122").collect(Collectors.toSet());
        assertThat(RuleEvaluation.evaluate(RULE_1, saCodes)).isEqualTo(false);
    }

    @Test
    public void evaluation_of_RULE_1_failing() {
        Set<String> saCodes = Stream.of("112", "50", "56", "110", "111", "117", "118", "122").collect(Collectors.toSet());
        assertThat(RuleEvaluation.evaluate(RULE_1, saCodes)).isEqualTo(true);
    }

    @Test
    public void evaluation_of_RULE_2_successful() {
        Set<String> saCodes = Stream.of("235", "429").collect(Collectors.toSet());
        assertThat(RuleEvaluation.evaluate(RULE_2, saCodes)).isEqualTo(true);
    }

    @Test
    public void evaluation_of_RULE_2_failing() {
        Set<String> saCodes = Stream.of("235", "221").collect(Collectors.toSet());
        assertThat(RuleEvaluation.evaluate(RULE_2, saCodes)).isEqualTo(false);
    }

}