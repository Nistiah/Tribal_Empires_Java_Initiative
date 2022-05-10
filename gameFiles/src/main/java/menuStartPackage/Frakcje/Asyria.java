package menuStartPackage.Frakcje;

import java.util.Arrays;
import java.util.List;

public class Asyria extends Fraction{
    List<String> improvements = Arrays.asList("Pałac w Niniwie", "Lamassu", "Świątynia Nabu");
    List<String> militaryUnit = Arrays.asList("Gwardia Królewska", "Szwadron Kawalerii");
    String king = "Puzur-Aszur III";

    public Asyria() {
        setImprovements(improvements);
        setMilitaryUnit(militaryUnit);
        setKing(king);
    }
}
