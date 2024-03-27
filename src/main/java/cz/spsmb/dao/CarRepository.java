package cz.spsmb.dao;

import cz.spsmb.model.Car;
import cz.spsmb.model.Cat;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CarRepository implements PanacheRepository<Car> {
    public Optional<Car> listByName(String brand){
        return find("brand",brand).singleResultOptional();
    }

    public Car listbyID(Long id) { return findById(id);}
}
