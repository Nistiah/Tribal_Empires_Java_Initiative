package menuStartPackage.Frakcje;
import java.util.List;

public class Fraction {
    List<String> improvements;
    List<String> militaryUnit;

    public String description = ""; //TODO: add descriptions for all fractions

    public String getKing() {
        return king;
    }

    String king;

    public void setImprovements(List<String> improvements) {
        this.improvements = improvements;
    }

    public void setMilitaryUnit(List<String> militaryUnit) {
        this.militaryUnit = militaryUnit;
    }

    public void setKing(String king) {
        this.king = king;
    }

}
