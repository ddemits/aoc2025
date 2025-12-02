
void main() throws IOException {
    long[][] ranges = Arrays.stream(Utilities.getInput("day2").split(","))
            .map(rangeString ->  Arrays.stream(rangeString.split("-")).mapToLong(Long::parseLong).toArray())
            .toArray(long[][]::new);
    long start = System.currentTimeMillis();
    solve1(ranges);
    System.out.println("Duration: " + (System.currentTimeMillis() - start) + "ms");
    start = System.currentTimeMillis();
    solve2(ranges);
    System.out.println("Duration: " + (System.currentTimeMillis() - start) + "ms");
}

private void solve1(long[][] ranges) {
    long result = 0L;
    for (long[] range : ranges) {
        for (long codeLong = range[0]; codeLong <= range[1]; codeLong++) {
            String code = Long.toString(codeLong);
            int codeLength = code.length();
            if (codeLength % 2 == 0 && isRepetitionInCode(code, code.substring(0, codeLength / 2))) {
                result += codeLong;
            }
        }
    }
    System.out.printf("Puzzle 1 solution: [%d]\n", result);
}

private void solve2(long[][] ranges) {
    long result = 0L;
    for (long[] range : ranges) {
        for (long codeLong = range[0]; codeLong <= range[1]; codeLong++) {
            String code = Long.toString(codeLong);
            for (int repetitionLength = 1; repetitionLength <= code.length() / 2; repetitionLength++) {
                if (code.length() % repetitionLength == 0) {
                    String repetition = code.substring(0, repetitionLength);
                    if (isRepetitionInCode(code, repetition)) {
                        result += codeLong;
                        break;
                    }
                }
            }
        }
    }
    System.out.printf("Puzzle 2 solution: [%d]\n", result);
}

private boolean isRepetitionInCode(String code, String repetition) {
    if (repetition.isEmpty() || code.length() % repetition.length() != 0) {
        return false;
    }
    boolean allMatching = true;
    int repetitionLength = repetition.length();
    for (int repetitionStart = 0; repetitionStart <= code.length() - repetitionLength; repetitionStart += repetitionLength) {
        allMatching &= repetition.equals(code.substring(repetitionStart, repetitionStart + repetitionLength));
    }
    return allMatching;
}