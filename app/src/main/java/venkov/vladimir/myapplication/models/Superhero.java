package venkov.vladimir.myapplication.models;

import java.io.Serializable;

public class Superhero implements Serializable{
    public int id;
    public String name;
    public String secretIdentity;

    public Superhero() {
        // public constructor is needed for Firebase parsing to work
    }

    public Superhero(String name, String secretIdentity) {
        this.name = name;
        this.secretIdentity = secretIdentity;
    }

    public String getName() {
        return name;
    }

    public String getSecretIdentity() {
        return secretIdentity;
    }

    public int getId() {
        return id;
    }
}
