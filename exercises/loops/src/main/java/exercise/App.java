package exercise;

class App {
    // BEGIN
    public static String getAbbreviation(String str) {
        String[] words = str.split(" ");
        StringBuilder abbr = new StringBuilder();

        for (String word : words) {
            abbr.append(word.charAt(0));
        }
        return abbr.toString().toUpperCase();
    }
    // END
}
