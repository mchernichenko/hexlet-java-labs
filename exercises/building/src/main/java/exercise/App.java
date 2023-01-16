// BEGIN
package exercise;

import com.google.gson.Gson;

class App {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }

    public static String toJson(String[] arrStr) {
        Gson gson = new Gson();
        return gson.toJson(arrStr);
    }
}
// END
