package menuStartPackage.Frakcje;

import java.util.Arrays;
import java.util.List;

public class Fenicja extends Fraction {
    private List<String> improvements = Arrays.asList("Port wojskowy", "Świątynia");
    private List<String> militaryUnit = Arrays.asList("Okręty wojenne", "Kompania piechoty");
    private String       king         = "Hiram I";

    public Fenicja() {
        setImprovements(improvements);
        setMilitaryUnit(militaryUnit);
        setKing(king);
    }
}
