package venkov.vladimir.myapplication.utils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import venkov.vladimir.myapplication.models.Superhero;

public class SuperheroesGenerator {

    public static List<Superhero> generateSuperheroesList(int count, String suffix) {
        return IntStream.range(1, count + 1)
                .mapToObj(index -> suffix + " " + index)
                .map(SuperheroesGenerator::generateSuperhero)
                .collect(Collectors.toList());
    }

    public static List<Superhero> generateSuperheroesList(int count) {
        return generateSuperheroesList(count, "");
    }

    public static Superhero generateSuperhero() {
        return generateSuperhero("");
    }

    public static Superhero generateSuperhero(String suffix) {
        return new Superhero(
                "Name " + suffix,
                "Identity " + suffix,
                "http://image-" + suffix + ".png"
        );
    }
}

