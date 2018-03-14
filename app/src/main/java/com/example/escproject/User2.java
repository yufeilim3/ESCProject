package com.example.escproject;

public abstract class User2 {
    private String id;
    private String name;
    private int type;

    public User2() {
    }

    public User2(String id, String name, int type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getType() {
        return type;
    }
}
