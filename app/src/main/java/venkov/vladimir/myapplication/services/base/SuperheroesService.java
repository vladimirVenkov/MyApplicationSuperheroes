package venkov.vladimir.myapplication.services.base;

import java.io.IOException;
import java.util.List;

import venkov.vladimir.myapplication.models.Superhero;

public interface SuperheroesService {
    List<Superhero> getAllSuperheroes() throws IOException;

    Superhero getDetailsById(int id) throws IOException;

    List<Superhero> getFilteredSuperheroes(String pattern) throws IOException;

}
