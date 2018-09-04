package venkov.vladimir.myapplication.diconfig;

import dagger.Module;
import dagger.Provides;
import venkov.vladimir.myapplication.models.Superhero;
import venkov.vladimir.myapplication.parsers.GsonJsonParser;
import venkov.vladimir.myapplication.parsers.base.JsonParser;

@Module
public class ParsersModule {
    @Provides
    public JsonParser<Superhero> superheroJsonParser() {
        return new GsonJsonParser<>(Superhero.class, Superhero[].class);
    }
}

