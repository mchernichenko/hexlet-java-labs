package exercise;

class Card {
    public static void printHiddenCard(String cardNumber, int starsCount) {
        // BEGIN
        int len = cardNumber.length();
        System.out.println("*".repeat(starsCount) + cardNumber.substring(len - 4, len));
        // END
    }
}
