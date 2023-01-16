package exercise;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class App {
    // BEGIN
    public static final String INDENT = "  ";
    public static String buildList(String[] arrStr) {
        if (arrStr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder("<ul>\n");
        for (String item : arrStr) {
            sb.append(INDENT).append("<li>").append(item).append("</li>").append("\n");
        }
        return sb.append("</ul>").toString();
    }

    public static String getUsersByYear(String[][] arrData, int birthDay) {
        StringBuilder sb = new StringBuilder("<ul>\n");
        boolean isFound = false;
        for (String[] items : arrData) {
            int year = LocalDate.parse(items[1], DateTimeFormatter.ofPattern("yyyy-MM-dd")).getYear();
            if (year == birthDay) {
                sb.append(INDENT).append("<li>").append(items[0]).append("</li>").append("\n");
                isFound = true;
            }
        }
        return isFound ? sb.append("</ul>").toString() : "";
    }

    public static String getYoungestUser(String[][] arrData, String dateStr) {
        long countDay = Long.MIN_VALUE;
        int idx = -1;
        int iter = 0;
        LocalDate conditionDate = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("d MMM yyyy"));
        for (String[] items : arrData) {
            LocalDate userDate = LocalDate.parse(items[1], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            if ((userDate.compareTo(conditionDate) < 0) && (userDate.toEpochDay() > countDay)) {
                countDay = userDate.toEpochDay();
                idx = iter;
            }
            iter++;
        }
        return (idx == -1) ? "" : arrData[idx][0];
    }
    // END
}
