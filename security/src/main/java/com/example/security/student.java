package com.example.security;

public class student {

    private int Id;
    private String Name;
    private int age;


    public student(int age, String name, int id) {
        this.age = age;
        Name = name;
        Id = id;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "student{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                ", age=" + age +
                '}';
    }
}
