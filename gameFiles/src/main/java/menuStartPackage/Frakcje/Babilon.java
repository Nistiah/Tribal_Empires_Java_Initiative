package menuStartPackage.Frakcje;

import java.util.Arrays;
import java.util.List;

public class Babilon extends Fraction {
    private List<String> improvements = Arrays.asList("Świątynia boga Nanny", "Brama Isztar", "wiszące ogrody");
    private List<String> militaryUnit = Arrays.asList("Oddział rydwanów", "Babiloński oddział piechoty");
    private String       king         = "Kadaszman-Enlil I";

    public Babilon() {
        setImprovements(improvements);
        setMilitaryUnit(militaryUnit);
        setKing(king);
    }
}
