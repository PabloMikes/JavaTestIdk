package cz.spsmb.view;

import cz.spsmb.dao.CarRepository;
import cz.spsmb.dao.CatRepository;
import cz.spsmb.dto.CarDTO;
import cz.spsmb.dto.CatDTO;
import cz.spsmb.dto.PersonDTO;
import cz.spsmb.model.Car;
import cz.spsmb.model.Cat;
import cz.spsmb.model.Person;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;
import cz.spsmb.dao.PersonRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class TestView {

    @Inject
    PersonRepository personRepository;

    @Inject
    CatRepository catRepository;

    @Inject
    CarRepository carRepository;

    List<Person> personList;
    List<Cat> catList;
    List<Car> carList;
    String name;
    int age;

    PersonDTO newPerson;
    CatDTO newCat;
    CarDTO newCar;

    String selectedPersonCat;
    String selectedCar;


    @PostConstruct
    public void init() {

        Map<String, String> params = FacesContext.getCurrentInstance().
                getExternalContext().getRequestParameterMap();
        String personName = params.get("name");

        System.out.println(personName);

        personList = personRepository.listAll();
        System.out.println(personList);

        personList = personRepository.listAll();
        carList = carRepository.listAll();
        catList = catRepository.listAll();

        newPerson = new PersonDTO();
        newCat = new CatDTO();
        newCar = new CarDTO();

    }

    public String getSelectedCar() {
        return selectedCar;
    }

    public void setSelectedCar(String selectedCar) {
        this.selectedCar = selectedCar;
    }

    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }

    public CarRepository getCarRepository() {
        return carRepository;
    }

    public void setCarRepository(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Cat> getCatList() {
        return catList;
    }

    public void setCatList(List<Cat> catList) {
        this.catList = catList;
    }

    public String getSelectedPersonCat() {
        return selectedPersonCat;
    }

    public void setSelectedPersonCat(String selectedPersonCat) {
        this.selectedPersonCat = selectedPersonCat;
    }

    public CarDTO getNewCar() {
        return newCar;
    }

    public void setNewCar(CarDTO newCar) {
        this.newCar = newCar;
    }

    public CatRepository getCatRepository() {
        return catRepository;
    }

    public void setCatRepository(CatRepository catRepository) {
        this.catRepository = catRepository;
    }

    public CatDTO getNewCat() {
        return newCat;
    }

    public void setNewCat(CatDTO newCat) {
        this.newCat = newCat;
    }

    public PersonRepository getPersonRepository() {
        return personRepository;
    }

    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public PersonDTO getNewPerson() {
        return newPerson;
    }

    public void setNewPerson(PersonDTO newPerson) {
        this.newPerson = newPerson;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public List<String> getPersonNames() {
        return personList.stream().map(person -> person.getName()).collect(Collectors.toList());
    }

    public List<String> getCarNames(){
        return carList.stream().map(car -> car.getBrand()).collect(Collectors.toList());
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Transactional
    public void savePerson(){

        System.out.println("SavePerson");
        if(validateInputPerson(newPerson)){
            Person person = new Person();
            person.setName(newPerson.getName());
            person.setAge(newPerson.getAge());

            person.setCar(carRepository.listByName(selectedCar).get());

            personRepository.persist(person);
            personList = personRepository.listAll();
        }
    }

    private boolean validateInputPerson(PersonDTO personDTO){
        return !(personDTO.getName().isEmpty() || personDTO.getAge() == 0 || selectedCar.isEmpty());
    }

    @Transactional
    public void saveCat(){
        System.out.println("SaveCaT");
        if(validateInputCat(newCat)){
            Cat cat = new Cat();
            cat.setName(newCat.getName());
            cat.setAge(newCat.getAge());
            cat.setColor(newCat.getColor());
            Optional<Person> personOptional = personRepository.listByName(selectedPersonCat);
            cat.setPerson(personOptional.get());

            catRepository.persist(cat);
            catList = catRepository.listAll();
        }
    }

    private boolean validateInputCat(CatDTO catDTO){
        return !(catDTO.getName().isEmpty() || catDTO.getAge() == 0 || catDTO.getColor().isEmpty());
    }

    @Transactional
    public void saveCar(){
        System.out.println("SaveCaR");
        if(validateInputCar(newCar)){
            Car car = new Car();
            car.setBrand(newCar.getBrand());
            car.setModel(newCar.getModel());
            car.setColor(newCar.getColor());

            carRepository.persist(car);
            carList = carRepository.listAll();
        }
    }

    private boolean validateInputCar(CarDTO carDTO){
        return !(carDTO.getBrand().isEmpty() || carDTO.getModel().isEmpty() || carDTO.getColor().isEmpty());
    }
}
