package casino.roulette;

import java.util.Arrays;
import java.util.List;

public class Rule {
    private final List<Integer> numbers;
    private final int multiplier;

    Rule(List<Integer> numbers, int multiplier) {
        this.numbers = numbers;
        this.multiplier = multiplier;
    }

    Rule(Integer[] numbers, int multiplier) {
        this.numbers = Arrays.asList(numbers);
        this.multiplier = multiplier;
    }

    public boolean hit(int number) {
        return numbers.contains(number);
    }

    public int getMultiplier() {
        return multiplier;
    }
}
