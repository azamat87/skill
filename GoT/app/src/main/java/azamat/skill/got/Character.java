package azamat.skill.got;

import java.util.ArrayList;

/**
 * Created by Asus on 19.03.2018.
 */

public class Character {

    private String name;
    private String desc;
    private String words;
    private String born;
    private ArrayList<String> titles;
    private ArrayList<String> aliases;
    private String father;
    private String mother;
    private String died;
    private String culture;

    public Character() {}

    public Character(String name,
                     String desc,
                     String words,
                     String born,
                     ArrayList<String> titles,
                     ArrayList<String> aliases,
                     String father,
                     String mother,
                     String died) {
        this.name = name;
        this.desc = desc;
        this.words = words;
        this.born = born;
        this.titles = titles;
        this.aliases = aliases;
        this.father = father;
        this.mother = mother;
        this.died = died;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public String getBorn() {
        return born;
    }

    public void setBorn(String born) {
        this.born = born;
    }

    public ArrayList<String> getTitles() {
        return titles;
    }

    public void setTitles(ArrayList<String> titles) {
        this.titles = titles;
    }

    public ArrayList<String> getAliases() {
        return aliases;
    }

    public void setAliases(ArrayList<String> aliases) {
        this.aliases = aliases;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }

    public String getMother() {
        return mother;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    public String getDied() {
        return died;
    }

    public void setDied(String died) {
        this.died = died;
    }

    public String getCulture() {
        return culture;
    }

    public void setCulture(String culture) {
        this.culture = culture;
    }
}
