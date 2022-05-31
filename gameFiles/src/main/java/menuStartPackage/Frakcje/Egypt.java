package menuStartPackage.Frakcje;

import java.util.Arrays;
import java.util.List;

public class Egypt extends Fraction {
    private List<String> improvements = Arrays.asList("Piramida Cheopsa", "Świątynia Hatszepsut", "Wielki Sfinks");
    private List<String> militaryUnit = Arrays.asList("Oddział rydwanów", "Kompania Nosiciela Sztandaru");
    private String       king         = "Amenhotep III";

    public Egypt() {
        setImprovements(improvements);
        setMilitaryUnit(militaryUnit);
        setKing(king);
        description = " Egypt blablabla";
    }
}