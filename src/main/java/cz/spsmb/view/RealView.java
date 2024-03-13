package cz.spsmb.view;

import cz.spsmb.dao.CatRepository;
import cz.spsmb.model.Cat;
import cz.spsmb.model.Person;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;

import java.util.List;

@Named
@RequestScoped
public class RealView {

    @Inject
    CatRepository catRepository;

    List<Cat> catList;
    String name;
    int age;
    String color;

    @PostConstruct
    public void init() {
        catList = catRepository.listAll();
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

    public CatRepository getCatRepository() {
        return catRepository;
    }

    public void setCatRepository(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    public List<Cat> getCatList() {
        return catList;
    }

    public void setCatList(List<Cat> catList) {
        this.catList = catList;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Transactional
    public void saveCat() {
        Cat cat = new Cat(name, age, color);
        catRepository.persist(cat);
        catList.add(cat);
        System.out.println("Saved " + cat);
    }

}
