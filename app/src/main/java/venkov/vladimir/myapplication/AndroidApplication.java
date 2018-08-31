package venkov.vladimir.myapplication;

import android.app.Application;

import java.util.HashMap;
import java.util.Map;

import venkov.vladimir.myapplication.http.HttpRequester;
import venkov.vladimir.myapplication.http.OkHttpHttpRequester;
import venkov.vladimir.myapplication.models.Superhero;
import venkov.vladimir.myapplication.parsers.GsonJsonParser;
import venkov.vladimir.myapplication.parsers.base.JsonParser;
import venkov.vladimir.myapplication.repositories.HttpRepository;
import venkov.vladimir.myapplication.repositories.base.Repository;
import venkov.vladimir.myapplication.services.HttpSuperheroesService;
import venkov.vladimir.myapplication.services.base.SuperheroesService;

public class AndroidApplication extends Application {
    public static Repository<Superhero> superheroRepository;
    private static HttpRequester httpRequester;
    private static JsonParser<Superhero> superheroJsonParser;
    private static Map<String, Repository> repositoriesMap;
    private static Map<String, JsonParser> jsonParsersMap;

    static {
        repositoriesMap = new HashMap<>();
        jsonParsersMap = new HashMap<>();
    }

    private static SuperheroesService superheroesService;

    public static <T> Repository<T> getRepository(Class<T> klass, Class<T[]> arrayKlass) {
        String klassKey = klass.getSimpleName();
        if (!repositoriesMap.containsKey(klassKey)) {
            String serverUrl =
                    getServerBaseUrl() + "/" +
                            klass.getSimpleName()
                                    .toLowerCase() +
                            "s";

            HttpRequester httpRequester = getHttpRequester();
            JsonParser<T> jsonParser = getJsonParser(klass, arrayKlass);
            Repository<T> repository = new HttpRepository<>(
                    serverUrl,
                    httpRequester,
                    jsonParser
            );

            repositoriesMap.put(klassKey, repository);
        }

        return repositoriesMap.get(klassKey);
    }

    public static String getServerBaseUrl() {

       // return "http://10.109.72.58:8080/api";
        return "http://192.168.1.248:8080/api";
    }

    private static <T> JsonParser<T> getJsonParser(Class<T> klass, Class<T[]> arrayKlass) {
        String klassKey = klass.getSimpleName();
        if (!jsonParsersMap.containsKey(klassKey)) {
            JsonParser<T> jsonParser = new GsonJsonParser<>(klass, arrayKlass);
            jsonParsersMap.put(klassKey, jsonParser);
        }

        return jsonParsersMap.get(klassKey);
    }

    public static Repository<Superhero> getSuperheroRepository() {
        if (superheroRepository == null) {
            String serverUrl = getServerBaseUrl() + "/superheroes";
            HttpRequester httpRequester = getHttpRequester();
            JsonParser<Superhero> jsonParser = getSuperheroJsonParser();
            superheroRepository = new HttpRepository<>(
                    serverUrl,
                    httpRequester,
                    jsonParser
            );
        }

        return superheroRepository;
    }

    public static HttpRequester getHttpRequester() {
        if (httpRequester == null) {
            httpRequester = new OkHttpHttpRequester();
        }

        return httpRequester;
    }


    public static JsonParser<Superhero> getSuperheroJsonParser() {
        if (superheroJsonParser == null) {
            superheroJsonParser = new GsonJsonParser<>(
                    Superhero.class,
                    Superhero[].class
            );
        }
        return superheroJsonParser;
    }

    public static SuperheroesService getSuperheroesService() {
        if (superheroesService == null) {
            superheroesService = new HttpSuperheroesService(getRepository(Superhero.class, Superhero[].class));
        }
        return superheroesService;
    }




}
