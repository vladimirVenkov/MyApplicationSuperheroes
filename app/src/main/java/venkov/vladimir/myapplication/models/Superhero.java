package venkov.vladimir.myapplication.models;

public class Superhero {
    public String name;
    public  String secretId;

    public Superhero() {
        //public constructor is needed for Firebase parsing to work
    }

    public Superhero(String name, String secretId) {
        this.name = name;
        this.secretId = secretId;
    }

}
