package menuStartPackage.Frakcje;

import java.util.Arrays;
import java.util.List;

public class Fenicja extends Fraction{
    List<String> improvements = Arrays.asList("Port wojskowy", "Świątynia");
    List<String> militaryUnit = Arrays.asList("Okręty wojenne", "Kompania piechoty");
    String king = "Hiram I";

    public Fenicja() {
        setImprovements(improvements);
        setMilitaryUnit(militaryUnit);
        setKing(king);
    }
}
