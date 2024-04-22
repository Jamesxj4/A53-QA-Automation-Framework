package models;

public class Playlist {
    private int id;
    private String name;
    private Rule[] rules;
    private boolean is_smart;

    public Playlist(String name, Rule[] rules, boolean is_smart){
        this.name = name;
        this.rules = rules;
        this.is_smart = is_smart;
    }

    public Playlist() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Rule[] getRules() {
        return rules;
    }

    public void setRules(Rule[] rules) {
        this.rules = rules;
    }

    public boolean isIs_smart() {
        return is_smart;
    }

    public void setIs_smart(boolean is_smart) {
        this.is_smart = is_smart;
    }
}
