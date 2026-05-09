package org.example;

public class StateMachine {

    public enum State {
        S, ONE, TWO, THREE, F
    }

    private State currentState;

    public StateMachine() {
        this.currentState = State.S;
    }

    public void process(char ch) {
        char upperCh = Character.toUpperCase(ch);

        switch (currentState) {
            case S:
                if (upperCh == 'T') {
                    currentState = State.ONE;
                }
                break;

            case ONE:
                if (upperCh == 'E') {
                    currentState = State.TWO;
                } else if (upperCh == 'T') {
                    currentState = State.ONE;
                } else {
                    currentState = State.S;
                }
                break;

            case TWO:
                if (upperCh == 'S') {
                    currentState = State.THREE;
                } else if (upperCh == 'T') {
                    currentState = State.ONE;
                } else {
                    currentState = State.S;
                }
                break;

            case THREE:
                if (upperCh == 'T') {
                    currentState = State.F;
                } else {
                    currentState = State.S;
                }
                break;

            case F:
                break;
        }
    }

    public State getCurrentState() {
        return currentState;
    }

    public void reset() {
        currentState = State.S;
    }
}
