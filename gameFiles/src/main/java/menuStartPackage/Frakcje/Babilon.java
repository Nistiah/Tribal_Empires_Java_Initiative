package menuStartPackage.Frakcje;

import java.util.Arrays;
import java.util.List;

public class Babilon extends Fraction{
    List<String> improvements = Arrays.asList("Świątynia boga Nanny", "Brama Isztar", "wiszące ogrody");
    List<String> militaryUnit = Arrays.asList("Oddział rydwanów", "Babiloński oddział piechoty");
    String king = "Kadaszman-Enlil I";

    public Babilon() {
        setImprovements(improvements);
        setMilitaryUnit(militaryUnit);
        setKing(king);
    }
}
