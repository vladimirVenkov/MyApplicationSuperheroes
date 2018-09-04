package venkov.vladimir.myapplication.diconfig;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import venkov.vladimir.myapplication.models.Superhero;
import venkov.vladimir.myapplication.validators.SuperheroValidator;
import venkov.vladimir.myapplication.validators.base.Validator;

@Module
public class ValidatorsModule {
    @Provides
    @Singleton
    public Validator<Superhero> superheroValidator() {
        return new SuperheroValidator();
    }
}

