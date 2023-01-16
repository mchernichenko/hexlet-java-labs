package exercise;

class Converter {
    // BEGIN
    public static int convert(int size, String measurement) {
        int i = 1024;
        switch (measurement) {
            case "b":
                size *= i;
                break;
            case "Kb":
                size /= i;
                break;
            default:
                size = 0;
                break;
        }
        return size;
    }

    public static void main() {
        int size = 10240;
        String measurement = "Kb";
        int convertSize = convert(size, measurement);
        System.out.println(convertSize + " " + measurement + " = " + size + " b");
    }
    // END
}
