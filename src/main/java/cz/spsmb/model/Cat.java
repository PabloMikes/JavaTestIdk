package cz.spsmb.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "Cat-Epic-Table")
public class Cat implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    long id;
    String name;
    int age;
    String color;

    @ManyToOne(fetch = FetchType.LAZY)
    Person person;

    public Cat(){}

    public Cat(String name, Integer age, String color){
        this.name = name;
        this.age = age;
        this.color = color;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", color='" + color + '\'' +
                '}';
    }
}
