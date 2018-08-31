package venkov.vladimir.myapplication.services;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import venkov.vladimir.myapplication.models.Superhero;
import venkov.vladimir.myapplication.repositories.base.Repository;
import venkov.vladimir.myapplication.services.base.SuperheroesService;

public class HttpSuperheroesService implements SuperheroesService {

    private final Repository<Superhero> mSuperheroesRepository;

    public HttpSuperheroesService(Repository<Superhero> superheroesRepository) {
        mSuperheroesRepository = superheroesRepository;
    }

    @Override
    public List<Superhero> getAllSuperheroes() throws IOException {
        return mSuperheroesRepository.getAll();
    }

    @Override
    public Superhero getDetailsById(int id) throws IOException {
        return mSuperheroesRepository.getById(id);
    }

    @Override
    public List<Superhero> getFilteredSuperheroes(String pattern) throws IOException {
        String patternToLower = pattern.toLowerCase();

        return getAllSuperheroes().stream()
                .filter(superhero -> superhero.getName().toLowerCase().contains(patternToLower))
                .collect(Collectors.toList());
    }
}
