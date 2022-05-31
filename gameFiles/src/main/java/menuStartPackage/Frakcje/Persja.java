package menuStartPackage.Frakcje;

import java.util.Arrays;
import java.util.List;

public class Persja extends Fraction {
    private List<String> improvements = Arrays.asList("Apadana w Persepolis", "Ziggurat w Czoga Zanbil");
    private List<String> militaryUnit = Arrays.asList("Nieśmiertelni", "Jazda konna");
    private String       king         = "Dejokes";

    public Persja() {
        setImprovements(improvements);
        setMilitaryUnit(militaryUnit);
        setKing(king);
    }
}
