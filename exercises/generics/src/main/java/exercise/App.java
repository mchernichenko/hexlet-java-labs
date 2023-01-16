package exercise;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

// BEGIN
public class App {
    public static <T> ArrayList<HashMap<T, T>> findWhere(ArrayList<HashMap<T, T>> books, HashMap<T, T> where) {
        if ((where == null) || (where.size() == 0)) {
            return books;
        }
        ArrayList<HashMap<T, T>> select = new ArrayList<>();
        for (HashMap<T, T> book : books) {
            Set<Map.Entry<T, T>> bookFields = book.entrySet();
            if (bookFields.containsAll(where.entrySet())) {
                select.add(book);
            }
        }
        return select;
    }
}
//END
