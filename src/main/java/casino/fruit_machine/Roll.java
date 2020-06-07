package fruit_machine;

public class Roll {
    public enum Sign {
 		Seven(1),
        Strawberry(2),
        Pineapple(3),
        Cherries(4),
        Grapes(5),
        Lemon(6),
        Watermelon(7);

        private int sign;

        public int getSign() {
            return this.sign;
        }

        public static Sign of(int value) {
            int items = Sign.values().length+1;
            if (value > items || value < 1) {
                return Sign.Seven;
            }
            return Sign.values()[value];
        }
    
        private Sign(int sign) {
            this.sign = sign;
        }

        public static int getLastValueIndex() {
            return Sign.values().length;
        }
 	}

    Sign sign;

    private Roll(Sign initialSign) {
        this.sign = initialSign;
    }

    public static Roll standard() {
        return new Roll(Sign.Seven);
    }

    public void turn(int number) {
        if (number < 1 || number > Roll.Sign.getLastValueIndex() + 1) {
            return;
        }

        Sign sign = Sign.of(number);
        this.setSign(sign);
    }

    public void setSign(Sign sign) {
        this.sign = sign;
    }

    public boolean isSame(Roll roll) {
        return this.sign == roll.getSign();
    }

    public Sign getSign() {
        return this.sign;
    }

    public int getValue() {
        return this.sign.getSign();
    }
}