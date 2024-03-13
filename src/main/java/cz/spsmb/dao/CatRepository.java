package cz.spsmb.dao;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import cz.spsmb.model.Cat;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class CatRepository implements PanacheRepository<Cat> {
    public List<Cat> listByName(String name){
        return find("name",name).list();
    }

    public Cat listbyID(Long id) { return findById(id);}
}
