package restaurant.misc;

public class Money {

    public static String convertToString(long money) {
        String moneyString = Long.toString(money);
        if (moneyString.length() <= 1) {
            return "0." + moneyString + "0 zł";
        } else {
            return moneyString.substring(0, moneyString.length() - 2) + "." +
                    moneyString.substring(moneyString.length() - 2, moneyString.length()) + " zł";
        }
    }
}
