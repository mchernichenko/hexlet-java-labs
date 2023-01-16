package exercise;

class Sentence {
    public static void printSentence(String sentence) {
        // BEGIN
        System.out.println(sentence.endsWith("!") ? sentence.toUpperCase() : sentence.toLowerCase());
        // END
    }
}
