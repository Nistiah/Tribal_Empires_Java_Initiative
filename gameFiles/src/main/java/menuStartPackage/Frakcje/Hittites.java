package menuStartPackage.Frakcje;

import java.util.Arrays;
import java.util.List;

public class Hittites extends Fraction {
    private List<String> improvements = Arrays.asList("Królewska Cytadela", "Zbiorniki Wschodnie", "Brama Królewska");
    private List<String> militaryUnit = Arrays.asList("Oddział dwukonnych rydwanów wojennych",
                                                      "Hetycka piechota uzbrojona w topory");
    private String king = "Telepinu";


    public Hittites() {
        setImprovements(improvements);
        setMilitaryUnit(militaryUnit);
        setKing(king);
        description = "\nThe Hittites manufacture advanced iron goods, rule over their kingdom through government officials with independent authority over various branches of government, and worshipped storm gods.";
    }
}
