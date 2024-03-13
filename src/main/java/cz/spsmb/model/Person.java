package cz.spsmb.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "test-person-table")
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    long id;
    String name;
    int age;

    @OneToMany(mappedBy = "person")
    List<Cat> cat;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    Car car;

    public Person(){}

    public Person(String name, Integer age){
        this.name = name;
        this.age = age;
    }

    public List<Cat> getCat() {
        return cat;
    }

    public void setCat(List<Cat> cat) {
        this.cat = cat;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
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

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
