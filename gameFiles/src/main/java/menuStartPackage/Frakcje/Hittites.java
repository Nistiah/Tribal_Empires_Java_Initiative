package menuStartPackage.Frakcje;

import java.util.Arrays;
import java.util.List;

public class Hittites extends Fraction{
    List<String> improvements = Arrays.asList("Królewska Cytadela", "Zbiorniki Wschodnie", "Brama Królewska");
    List<String> militaryUnit = Arrays.asList("Oddział dwukonnych rydwanów wojennych", "Hetycka piechota uzbrojona w topory");
    String king = "Telepinu";

    public Hittites() {
        setImprovements(improvements);
        setMilitaryUnit(militaryUnit);
        setKing(king);
        description = " Hittites blablabla";
    }
}