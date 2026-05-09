package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StateMachineTest {

    @ParameterizedTest(name = "Input: {0} -> Expected: {1}")
    @CsvSource({
            "'abcTESTabc', F",
            "'TEST', F",
            "'T', ONE",
            "'TE', TWO",
            "'TES', THREE",
            "'TESTX', F",
            "'', S",
            "'SOMETHING', S",
            "'TESTTEST', F",
            "'TTTT', ONE"
    })
    void testFiniteStateMachine(String input, String expectedState) {
        StateMachine fsm = new StateMachine();

        input.chars().mapToObj(c -> (char) c).forEach(fsm::process);

        assertEquals(StateMachine.State.valueOf(expectedState), fsm.getCurrentState());
    }

    @ParameterizedTest(name = "Reset test: {0} -> {1} -> reset -> {2} -> {3}")
    @CsvSource({
            "TEST, F, A, S",
            "TE, TWO, TEST, F",
            "TESTX, F, TEST, F"
    })
    void testResetFunctionality(String input1, String expected1, String input2, String expected2) {
        StateMachine fsm = new StateMachine();

        input1.chars().mapToObj(c -> (char) c).forEach(fsm::process);
        assertEquals(StateMachine.State.valueOf(expected1), fsm.getCurrentState());

        fsm.reset();
        assertEquals(StateMachine.State.S, fsm.getCurrentState());

        input2.chars().mapToObj(c -> (char) c).forEach(fsm::process);
        assertEquals(StateMachine.State.valueOf(expected2), fsm.getCurrentState());
    }
}
