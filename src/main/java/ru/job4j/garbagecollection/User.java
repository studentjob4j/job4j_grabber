package ru.job4j.garbagecollection;

public  class User {

    private String name;
    private int count;

    public User(String name, int count) {
        this.name = name;
        this.count = count;
    }

    @Override
    public String toString() {
        return "User{"
                + "name='" + name + '\'' + '}';
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.printf("Removed %s%n", name);
    }
}
