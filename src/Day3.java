import java.io.IOException;
import java.util.Arrays;

void main() throws IOException {
    String[] banks = Arrays.stream(Utilities.getInput("day3").split("\r\n")).toArray(String[]::new);
    long start = System.currentTimeMillis();
    System.out.printf("Puzzle 1 solution: [%d]\n", solve(banks, 2));
    System.out.println("Duration: " + (System.currentTimeMillis() - start) + "ms");
    start = System.currentTimeMillis();
    System.out.printf("Puzzle 2 solution: [%d]\n", solve(banks, 12));
    System.out.println("Duration: " + (System.currentTimeMillis() - start) + "ms");
}

private long solve(String[] banks, int combinationSize) {
    long result = 0;
    for (String bank : banks) {
        result += findLargestBatteryCombination(bank, combinationSize);
    }
    return result;
}

private long findLargestBatteryCombination(String bank, int combinationSize) {
    int[] combination = new int[combinationSize];
    for (int bankIndex = 0; bankIndex < bank.length(); bankIndex++) {
        int battery = Integer.parseInt(String.valueOf(bank.charAt(bankIndex)));
        boolean assigned = false;
        for (int combinationIndex = 0; combinationIndex < combination.length; combinationIndex++) {
            if (!assigned && combination[combinationIndex] < battery
                    && combinationSize - combinationIndex <= bank.length() - bankIndex) {
                combination[combinationIndex] = battery;
                assigned = true;
            } else if (assigned) {
                combination[combinationIndex] = 0;
            }
        }
    }
    long result = 0;
    for (int i = 0; i < combination.length; i++) {
        result += (long) Math.pow(10, combinationSize - i - 1) * combination[i];
    }
    return result;
}