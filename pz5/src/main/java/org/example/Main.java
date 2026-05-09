package org.example;

public class Main {
    public static void main(String[] args) {
        String[] testCases = {
                "abcTESTabc",
                "TEST",
                "T",
                "TE",
                "TES",
                "TESTX",
                "",
                "SOMETHING",
                "TESTTEST",
                "TTTT",
                "tEsT",
                "TeStAbC",
                "XTESTabc",
                "TTTESTT",
                "AAATESTWWW",
                "TETEST",
                "TESTESTTEST",
                "TTTESTTTT",
                "TEEEEST",
                "TEEEST",
                "TESST",
                "TESTT",
                "TESTE",
                "TTTEST",
                "TETETS",
                "TTEST",
                "TESTYTESTYTESTYTEST"
        };

        System.out.println("Результати тестування:\n");
        System.out.printf("%-30s | %-10s%n", "Вхідний рядок", "Стан");
        System.out.println("-".repeat(45));

        for (String testCase : testCases) {
            StateMachine fsm = new StateMachine();
            testCase.chars().mapToObj(c -> (char) c).forEach(fsm::process);

            String displayStr = testCase.isEmpty() ? "(порожній)" : testCase;
            String status = fsm.getCurrentState() == StateMachine.State.F ? "✓ F" : fsm.getCurrentState().toString();

            System.out.printf("%-30s | %-10s%n", displayStr, status);
        }

        System.out.println("\n" + "=".repeat(45));
        System.out.println("Легенда:");
        System.out.println("  S     - Початковий стан");
        System.out.println("  ONE   - Прочитано 'T'");
        System.out.println("  TWO   - Прочитано 'TE'");
        System.out.println("  THREE - Прочитано 'TES'");
        System.out.println("  F     - Прочитано 'TEST' ✓");
    }
}

