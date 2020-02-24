package main;

import java.util.Random;

public class MonteCarloMethod {

    private Random random;
    private int numbersOfIteration;

    public MonteCarloMethod(int numbersOfIteration) {
        this.numbersOfIteration = numbersOfIteration;
        random = new Random();
    }

    private boolean decidingPassFirstYear() {
        while (true) {
            double randomValue = random.nextDouble();
            if (randomValue <= 0.6) return true;
            if (randomValue >= 0.7) continue;
            return false;
        }
    }
    private boolean decidingPassSecondYear() {
        while (true) {
            double randomValue = random.nextDouble();
            if (randomValue <= 0.6) return true;
            if (randomValue >= 0.7) continue;
            return false;
        }
    }
    private boolean decidingPassThirdYear() {
        while (true) {
            double randomValue = random.nextDouble();
            if (randomValue <= 0.7) return true;
            if (randomValue >= 0.8) continue;
            return false;
        }
    }
    private boolean decidingPassFourthYear() {
        while (true) {
            double randomValue = random.nextDouble();
            if (randomValue <= 0.85) return true;
            if (randomValue >= 0.9) continue;
            return false;
        }
    }
    private boolean decidingPassFiveYear() {
        while (true) {
            double randomValue = random.nextDouble();
            if (randomValue >= 0.9)return true;
        }
    }
    public String monteCarloCalculate() {
        int thirdYears = 0;
        int secondYears = 0;
        int firstYears = 0;
        int fourthYears = 0;
        int fiveYears = 0;
        for (int i = 0; i < numbersOfIteration; i++) {
            if (decidingPassFirstYear() && decidingPassSecondYear() && decidingPassThirdYear() && decidingPassFourthYear() && decidingPassFiveYear()) {
                firstYears++;
            }
            if (decidingPassSecondYear() && decidingPassThirdYear() && decidingPassFourthYear() && decidingPassFiveYear()) {
                secondYears++;
            }
            if (decidingPassThirdYear() && decidingPassFourthYear() && decidingPassFiveYear()) {
                thirdYears++;
            }
            if (decidingPassFourthYear() && decidingPassFiveYear()) {
                fourthYears++;
            }
            if (decidingPassFiveYear()) {
                fiveYears++;
            }
        }
        return "First years had done are: " + firstYears + "probility is " + ((double) firstYears * 100 / numbersOfIteration) + "%\n"
                + "Second years had done are: " + secondYears + " with probabability is " + ((double) secondYears * 100 / numbersOfIteration + "%") + "\n"
                + "Third years had done are: " + thirdYears + " with probability is " + ((double) thirdYears * 100 / numbersOfIteration) + "%\n"
                + "Fourth years had done are: " + fourthYears + " with probabilite is " + ((double) fourthYears * 100 / numbersOfIteration) + "%\n"
                + "Five years had done are: " + fiveYears + " with probabilite is " + ((double) fiveYears * 100 / numbersOfIteration) + "%\n"
                + "from " + numbersOfIteration;
    }
    public static void main(String[] args) {
        MonteCarloMethod monteCarloMethod = new MonteCarloMethod(10000);
        System.out.println(monteCarloMethod.monteCarloCalculate());
    }
}
