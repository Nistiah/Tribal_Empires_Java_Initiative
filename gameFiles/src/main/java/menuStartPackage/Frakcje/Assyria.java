package menuStartPackage.Frakcje;

import java.util.Arrays;
import java.util.List;

public class Assyria extends Fraction {
    private List<String> improvements = Arrays.asList("Pałac w Niniwie", "Lamassu", "Świątynia Nabu");
    private List<String> militaryUnit = Arrays.asList("Gwardia Królewska", "Szwadron Kawalerii");
    private String       king         = "Puzur-Aszur III";

    public Assyria() {
        setImprovements(improvements);
        setMilitaryUnit(militaryUnit);
        setKing(king);
        description = " Assyria blablabla";
    }
}