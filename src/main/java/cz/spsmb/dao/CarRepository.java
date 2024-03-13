package cz.spsmb.dao;

import cz.spsmb.model.Car;
import cz.spsmb.model.Cat;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class CarRepository implements PanacheRepository<Car> {
    public List<Car> listByName(String brand){
        return find("brand",brand).list();
    }

    public Car listbyID(Long id) { return findById(id);}
}
