package exercise;

import java.util.Arrays;

// BEGIN
public class Kennel {
    private static final String[][] PUPPIES = new String[100][];
    public static void addPuppy(String[] puppy) {
        PUPPIES[getPuppyCount()] = puppy;
    }
    public static void addSomePuppies(String[][] puppyArr) {
        for (String[] strings : puppyArr) {
            addPuppy(strings);
        }
    }
    public static int getPuppyCount() {
        int i = 0;
        while (PUPPIES[i] != null) {
            i++;
        }
        return i;
    }
    public static boolean isContainPuppy(String name) {
        int cnt = getPuppyCount();
        for (int i = 0; i < cnt; i++) {
            if (PUPPIES[i][0].equals(name)) {
                return true;
            }
        }
        return false;
    }
    public static String[][] getAllPuppies() {
        return Arrays.copyOf(PUPPIES, getPuppyCount());
    }
    public static String[] getNamesByBreed(String breed) {
        StringBuilder sb = new StringBuilder();
        int cnt = getPuppyCount();
        for (int i = 0; i < cnt; i++) {
            if (PUPPIES[i][1].equals(breed)) {
                sb.append(PUPPIES[i][0]).append(";");
            }
        }
        return sb.toString().split(";");
    }
    public static void resetKennel() {
        int cnt = getPuppyCount();
        for (int i = 0; i < cnt; i++) {
            PUPPIES[i] = null;
        }
    }

    public static boolean removePuppy(String name) {
        int cnt = getPuppyCount();
        boolean isPresent = false;
        int idx = 0;
        for (int i = 0; i < cnt; i++) {
            if (PUPPIES[i][0].equals(name)) {
                isPresent = true;
                idx = i;
                break;
            }
        }
        if (isPresent) {
            System.arraycopy(PUPPIES, idx + 1, PUPPIES, idx, cnt - idx);
        }
        return isPresent;
    }
}
// END

