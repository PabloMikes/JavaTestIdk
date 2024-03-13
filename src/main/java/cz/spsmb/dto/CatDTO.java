package cz.spsmb.dto;

public class CatDTO {

    String name;
    String age;
    String color;
    String person;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "CatDTO{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", color='" + color + '\'' +
                ", person='" + person + '\'' +
                '}';
    }
}
