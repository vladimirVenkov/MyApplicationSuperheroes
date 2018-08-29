package venkov.vladimir.myapplication.repositories;

import venkov.vladimir.myapplication.async.AsyncRunner;
import venkov.vladimir.myapplication.http.HttpRequester;
import venkov.vladimir.myapplication.parsers.base.JsonParser;
import venkov.vladimir.myapplication.repositories.base.Repository;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

public class HttpRepository<T> implements Repository<T> {
    private final HttpRequester mHttpRequester;
    private final String mServerUrl;
    private final JsonParser<T> mJsonParser;

    public HttpRepository(
            String serverUrl,
            HttpRequester httpRequester,
            JsonParser<T> jsonParser
    ) {
        mServerUrl = serverUrl;
        mHttpRequester = httpRequester;
        mJsonParser = jsonParser;
    }

    @Override
    public void getAll(Consumer<List<T>> action) {
        AsyncRunner.runInBackground(() -> {
            String superheroesJson = null;
            try {
                superheroesJson = mHttpRequester.get(mServerUrl);
                List<T> superheroes = mJsonParser.fromJsonArray(superheroesJson);
                action.accept(superheroes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void add(T item, Consumer<T> action) {
        AsyncRunner.runInBackground(() -> {
            String requestBody = mJsonParser.toJson(item);
            try {
                String responseBody = mHttpRequester.post(mServerUrl, requestBody);
                T createdSuperhero = mJsonParser.fromJson(responseBody);
                action.accept(createdSuperhero);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
