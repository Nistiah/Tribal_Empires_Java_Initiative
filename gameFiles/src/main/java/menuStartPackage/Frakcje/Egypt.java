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
        description = "Egypt is located along the Nile River. The Nile is the source of much of Egypt's wealth. The Nile provides food, soil, water, and transportation for the Egyptians. Great floods would come each year and would provide fertile soil for growing food.";
    }
}