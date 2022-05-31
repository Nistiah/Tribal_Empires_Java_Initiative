package menuStartPackage.Frakcje;

import java.util.Arrays;
import java.util.List;

public class Kush extends Fraction {
    private List<String> improvements = Arrays.asList("Nubijska świątynia", " ");
    private List<String> militaryUnit = Arrays.asList("Kuszyccy łucznicy", "Zmasowany atak", "Słonie bojowe");
    private String       king         = "Alara";

    public Kush() {
        setImprovements(improvements);
        setMilitaryUnit(militaryUnit);
        setKing(king);
    }
}
