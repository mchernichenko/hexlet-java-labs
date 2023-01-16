class Example {

    private String name;
    private int age;

    Example(String userName, int userAge) {
        this.name = userName;
        this.age = userAge;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public String getSentence() {
        return String.format(
            "%s is %d years old", this.getName(), this.getAge()
        );
    }

    public static void main(String[] args) {
        Example example = new Example("Andrey", 25);
        System.out.println(example.getSentence()); // Andrey is 25 years old
    }
}
