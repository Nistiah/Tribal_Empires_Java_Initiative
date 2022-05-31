package menuStartPackage.Frakcje;

import java.util.List;

public class Fraction {
    protected String     description = "";    // TODO: add descriptions for all fractions
    private List<String> improvements;
    private List<String> militaryUnit;
    private String       king;

    public String getDescription() {
        return description;
    }

    public void setImprovements(List<String> improvements) {
        this.improvements = improvements;
    }

    public String getKing() {
        return king;
    }

    public void setKing(String king) {
        this.king = king;
    }

    public void setMilitaryUnit(List<String> militaryUnit) {
        this.militaryUnit = militaryUnit;
    }
}
