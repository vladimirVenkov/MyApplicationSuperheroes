package venkov.vladimir.myapplication.services.base;

import java.io.IOException;
import java.util.List;

import venkov.vladimir.myapplication.models.Superhero;

public interface SuperheroesService {
    List<Superhero> getAllSuperheroes() throws Exception;

    Superhero getDetailsById(int id) throws Exception;

    List<Superhero> getFilteredSuperheroes(String pattern) throws Exception;

    Superhero createSuperhero(Superhero superhero) throws Exception;
}
