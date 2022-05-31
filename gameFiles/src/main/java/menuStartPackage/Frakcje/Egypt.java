package menuStartPackage.Frakcje;

import java.util.Arrays;
import java.util.List;

public class Egypt extends Fraction{
    List<String> improvements = Arrays.asList("Piramida Cheopsa", "Świątynia Hatszepsut", "Wielki Sfinks");
    List<String> militaryUnit = Arrays.asList("Oddział rydwanów", "Kompania Nosiciela Sztandaru");
    String king = "Amenhotep III";


    public Egypt() {
        setImprovements(improvements);
        setMilitaryUnit(militaryUnit);
        setKing(king);
        description = " Egypt blablabla";
    }
}

