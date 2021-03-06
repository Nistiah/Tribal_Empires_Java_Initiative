package menuStartPackage.Frakcje;

import java.util.Arrays;
import java.util.List;

public class Assyria extends Fraction {
    private List<String> improvements = Arrays.asList("Pałac w Niniwie", "Lamassu", "Świątynia Nabu");
    private List<String> militaryUnit = Arrays.asList("Gwardia Królewska", "Szwadron Kawalerii");
    private String       king         = "Puzur-Aszur III";

    private final String[] cityNames = {"Ashur", "Nineveh", "Dur Sharrukin", "Babylon", "Susa", "Haran", "Calah"};

    public String getCityName(int id){
        if(id>cityNames.length)
            return "New "+cityNames[id-7];
        return cityNames[id];
    }



    public Assyria() {
        setImprovements(improvements);
        setMilitaryUnit(militaryUnit);
        setKing(king);
        description = "\nThe Assyrians are one of the major people to live in Mesopotamia. They live in northern Mesopotamia near the start of the Tigris and Euphrates Rivers. They are a warrior society where fighting is a part of life. It is how they survive.";
    }
}