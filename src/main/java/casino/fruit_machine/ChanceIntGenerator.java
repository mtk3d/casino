package fruit_machine;

import java.util.Random;

public class ChanceIntGenerator {
    Random generator;
    int first;
    int second;
    int third;
    int chance;
    int currentChance;
    int limit;
    int randomInt;

    ChanceIntGenerator(int limit, int chance) {
        this.generator = new Random();
        this.chance = chance;
        this.limit = limit;
        this.regenerate();
    }

    public void regenerate() {
        this.currentChance = this.generator.nextInt(this.chance);
        this.randomInt = this.generator.nextInt(this.limit);
    }

    public int nextInt() {
        if (1 == this.currentChance) {
            return this.randomInt;
        }
        return this.generator.nextInt(this.limit);
    }
}