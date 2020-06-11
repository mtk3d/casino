package casino.roulette;

import casino.Clickable;
import casino.Drawable;
import casino.Money;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board implements Drawable, Clickable {
    List<Field> fields;
    List<Bet> bets = new ArrayList<>();

    Board(List<Field> fields) {
        this.fields = fields;
    }

    public static Board generate() {
        List<Field> fields = new ArrayList<>();
        int offsetX = 300;
        int offsetY = 20;
        fields.add(new Field(offsetX, offsetY, 90, 20, "0", new Rule(new Integer[]{0}, 35)));

        int y = 20;
        int x = 0;
        List<Integer> redFields = Arrays.asList(1,3,5,7,9,12,14,16,18,19,21,23,25,27,30,32,34,36);
        List<Integer> blackFields = Arrays.asList(2,4,6,8,10,11,13,15,17,20,22,24,26,28,29,31,33,35);
        for (int i = 1; i <= 36; i++) {
            Rule rule = new Rule(new Integer[]{1}, 35);

            if (redFields.contains(i)) {
                fields.add(Field.standard(offsetX + x, offsetY + y, String.valueOf(i), rule, Color.RED));
            } else {
                fields.add(Field.standard(offsetX + x, offsetY + y, String.valueOf(i), rule));
            }

            x += 30;
            if (i % 3 == 0) {
                x = 0;
                y += 20;
            }
        }

        Rule firstColumn = new Rule(new Integer[]{1,4,7,10,13,16,19,22,25,28,31,34}, 2);
        fields.add(Field.standard(offsetX, offsetY + 13 * 20, "2to1", firstColumn));
        Rule secondColumn = new Rule(new Integer[]{2,5,8,11,14,17,20,23,26,29,32,35}, 2);
        fields.add(Field.standard(offsetX + 30, offsetY + 13 * 20, "2to1", secondColumn));
        Rule thirdColumn = new Rule(new Integer[]{3,6,9,12,15,18,21,24,27,30,33,36}, 2);
        fields.add(Field.standard(offsetX + 60, offsetY + 13 * 20, "2to1", thirdColumn));

        offsetX -= 30;
        List<Integer> first12 = IntStream.range(1,12).boxed().collect(Collectors.toList());
        Rule firstTw = new Rule(first12, 2);
        fields.add(new Field(offsetX, offsetY + 20, 30, 80, "1st", firstTw));
        List<Integer> second12 = IntStream.range(13,24).boxed().collect(Collectors.toList());
        Rule secondTw = new Rule(second12, 2);
        fields.add(new Field(offsetX, offsetY + 100, 30, 80, "2nd", secondTw));
        List<Integer> third12 = IntStream.range(25,36).boxed().collect(Collectors.toList());
        Rule thirdTw = new Rule(third12, 2);
        fields.add(new Field(offsetX, offsetY + 180, 30, 80, "3rd", thirdTw));

        offsetX -= 40;
        Rule oneTo18 = new Rule(IntStream.range(1,18).boxed().collect(Collectors.toList()), 1);
        fields.add(new Field(offsetX, offsetY + 20, 40, 40, "1-18", oneTo18));
        Rule odd = new Rule(Arrays.asList(1,3,5,7,9,11,13,15,17,19,21,23,25,27,29,31,33,35), 1);
        fields.add(new Field(offsetX, offsetY + 60, 40, 40, "odd", odd));

        Rule black = new Rule(blackFields, 1);
        fields.add(new Field(offsetX, offsetY + 100, 40, 40, "black", black));
        Rule red = new Rule(redFields, 1);
        fields.add(new Field(offsetX, offsetY + 140, 40, 40, "red", red, Color.RED));

        Rule even = new Rule(Arrays.asList(2,4,6,8,10,12,14,16,18,20,22,24,26,28,30,32,34,36), 1);
        fields.add(new Field(offsetX, offsetY + 180, 40, 40, "even", even));
        Rule nineteenTo36 = new Rule(IntStream.range(19,36).boxed().collect(Collectors.toList()), 1);
        fields.add(new Field(offsetX, offsetY + 220, 40, 40, "19-36", nineteenTo36));

        return new Board(fields);
    }


    @Override
    public void draw(Graphics2D g2D) {
        for (Field field: fields) {
            field.draw(g2D);
        }

        for (Bet bet: bets) {
            bet.draw(g2D);
        }
    }

    @Override
    public boolean hasBeenClicked(int x, int y) {
        boolean betExists = false;
        boolean clicked = false;
        for (Bet bet: bets) {
            if (bet.hasBeenClicked(x, y)) {
                bet.increase();
                betExists = true;
                clicked = true;
            }
        }

        if (!betExists) {
            for (Field field: fields) {
                if (field.hasBeenClicked(x, y)) {
                    bets.add(field.createBet());
                    clicked = true;
                }
            }
        }

        return clicked;
    }

    public Money calculate(int value) {
        Money sum = Money.of(0);

        bets.removeIf(bet -> !bet.inRange(value));

        for (Bet bet: bets) {
            sum = sum.sum(bet.getWin());
        }

        return sum;
    }

    public void clear() {
        bets = new ArrayList<>();
    }
}
