package menuStartPackage.Frakcje;

import java.util.Arrays;
import java.util.List;

public class Kush extends Fraction{
    List<String> improvements = Arrays.asList("Nubijska świątynia", " ");
    List<String> militaryUnit = Arrays.asList("Kuszyccy łucznicy", "Zmasowany atak", "Słonie bojowe");
    String king = "Alara";

    public Kush() {
        setImprovements(improvements);
        setMilitaryUnit(militaryUnit);
        setKing(king);
    }
}
