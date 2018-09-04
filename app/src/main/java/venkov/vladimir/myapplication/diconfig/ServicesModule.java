package venkov.vladimir.myapplication.diconfig;

import dagger.Module;
import dagger.Provides;
import venkov.vladimir.myapplication.models.Superhero;
import venkov.vladimir.myapplication.repositories.base.Repository;
import venkov.vladimir.myapplication.services.HttpSuperheroesService;
import venkov.vladimir.myapplication.services.base.SuperheroesService;
import venkov.vladimir.myapplication.validators.base.Validator;

@Module
public class ServicesModule {
    @Provides
    public SuperheroesService superheroesService(Repository<Superhero> repository, Validator<Superhero> validator) {
        return new HttpSuperheroesService(repository, validator);
    }
}
