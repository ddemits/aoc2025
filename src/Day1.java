
void main() throws IOException {
    int[] commands = Arrays.stream(Utilities.getInput("day1").split("\r\n"))
            .mapToInt(line -> (line.charAt(0) == 'L' ? -1 : 1) * Integer.parseInt(line.substring(1)))
            .toArray();
    solve1(commands);
    solve2(commands);
}

private void solve1(int[] commands) {
    int result = 0;
    int position = 50;
    for (int command : commands) {
        position = position + command;
        position = ((position % 100) + 100) % 100;
        if (position == 0) {
            result++;
        }
    }
    System.out.printf("Puzzle 1 solution: [%d]\n", result);
}

private void solve2(int[] commands) {
    int result = 0;
    int position = 50;
    for (int command : commands) {
        result += Math.abs(command / 100);
        int newPosition = position + (command % 100);
        // Crossed or landed at 0
        if (newPosition == 0 || newPosition * position < 0) {
            result++;
        }
        // Crossed or landed at 100 or -100
        if (newPosition >= 100 || newPosition <= -100) {
            result++;
        }
        position = newPosition % 100;
    }
    System.out.printf("Puzzle 2 solution: [%d]\n", result);
}