package azamat.skill.got;

import java.util.ArrayList;

/**
 * Created by Asus on 19.03.2018.
 */

     //Starks  https://anapioficeandfire.com/api/houses/362
    //Lannisters https://anapioficeandfire.com/api/houses/229
    //Targaryens https://anapioficeandfire.com/api/houses/378


public class House {

    private String url;
    private String region;
    private String coatOfArms;
    private String words;
    private ArrayList<String> titles;
    private ArrayList<String> swornMembers;

    public House() {
    }

    public House(String url, String region, String coatOfArms, String words, ArrayList<String> titles, ArrayList<String> swornMembers) {
        this.url = url;
        this.region = region;
        this.coatOfArms = coatOfArms;
        this.words = words;
        this.titles = titles;
        this.swornMembers = swornMembers;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCoatOfArms() {
        return coatOfArms;
    }

    public void setCoatOfArms(String coatOfArms) {
        this.coatOfArms = coatOfArms;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public ArrayList<String> getTitles() {
        return titles;
    }

    public void setTitles(ArrayList<String> titles) {
        this.titles = titles;
    }

    public ArrayList<String> getSwornMembers() {
        return swornMembers;
    }

    public void setSwornMembers(ArrayList<String> swornMembers) {
        this.swornMembers = swornMembers;
    }
}
