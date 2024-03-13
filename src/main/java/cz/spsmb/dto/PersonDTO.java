package cz.spsmb.dto;

public class PersonDTO {

    String name;
    String age;
    String car;

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

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", car='" + car + '\'' +
                '}';
    }
}
