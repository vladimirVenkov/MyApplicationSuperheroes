package venkov.vladimir.myapplication.repositories;

import venkov.vladimir.myapplication.async.AsyncRunner;
import venkov.vladimir.myapplication.http.HttpRequester;
import venkov.vladimir.myapplication.models.Superhero;
import venkov.vladimir.myapplication.parsers.base.JsonParser;
import venkov.vladimir.myapplication.repositories.base.Repository;
import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;


public class SuperheroesHttpRepository implements Repository<Superhero> {
    private final HttpRequester mHttpRequester;
    private final String mServerUrl;
    private final JsonParser<Superhero> mJsonParser;

    public SuperheroesHttpRepository(
            String serverUrl,
            HttpRequester httpRequester,
            JsonParser<Superhero> jsonParser
    ) {
        mServerUrl = serverUrl;
        mHttpRequester = httpRequester;
        mJsonParser = jsonParser;
    }

    @Override
    public void getAll(Consumer<List<Superhero>> action) {
        AsyncRunner.runInBackground(() -> {
            String superheroesJson = null;
            try {
                superheroesJson = mHttpRequester.get(mServerUrl);
                List<Superhero> superheroes = mJsonParser.fromJsonArray(superheroesJson);
                action.accept(superheroes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void add(Superhero item, Consumer<Superhero> action) {
        AsyncRunner.runInBackground(() -> {
            String requestBody = mJsonParser.toJson(item);
            try {
                String responseBody = mHttpRequester.post(mServerUrl, requestBody);
                Superhero createdSuperhero = mJsonParser.fromJson(responseBody);
                action.accept(createdSuperhero);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
