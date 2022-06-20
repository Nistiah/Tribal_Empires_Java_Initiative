package menuStartPackage.Prowincje;

import menuStartPackage.Budynki.Building;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Province {
    public boolean       isCity = false;
    protected double        gold   = 0;
    protected double        belief = 0;
    protected int        food   = 0;
    protected int        bronze = 0;
    protected int        iron   = 0;
    protected int        dices  = 0;
    protected int        horses = 0;
    protected int        wood   = 0;
    private int          i;

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
    }

    public int getPop(){
        return -1;
    }

    private int          j;
    protected int        ownerId;
    private String       type;
    private List<String> resources;
    private List<String> possibleBuildings;
    List<String>         baseBuildings;
    public List<String>  builtBuildings = new ArrayList<>();
    public List<Building> build = new ArrayList<Building>();

    public Vector<Building> builtBuildingsVector = new Vector<Building>();

    public void setBuildingsProduction(int id){
        //setBaseProduction(this.type);
        Building b = builtBuildingsVector.get(builtBuildingsVector.size()-1);
        String temp = builtBuildings.get(builtBuildings.size()-1);
        temp = temp.replaceAll("\\s+","");
        b.setBaseProduction(temp);
        b.setOwner(id);
        gold += b.getGold();
        belief += b.getBelief();
        food += b.getFood();
        bronze += b.getBronze();
        iron += b.getIron();
        dices += b.getDices();
        horses += b.getHorses();
        wood += b.getWood();
        if(ownerId == 2 && temp.equals("IronMine")){
            iron += 2;
        }
    }

    public void addBuiltBuildingsVector(Building building) {
        builtBuildingsVector.add(building);
    }

    public void setBuild(){
        possibleBuildings.forEach(bld -> {
            Building bld2 = new Building();
            bld2.setBaseProduction(bld);
            build.add(bld2);
        });
    }

    public String iconPath() {
        return null;
    }

    public void setBaseBuildings(List<String> baseBuildings) {
        this.baseBuildings = baseBuildings;
    }

    public List<String> getBaseBuildings() {
        return baseBuildings;
    }

    public void setBaseProduction(String type) {
        switch (type) {
        case "Sea" :
            food = 1;

            break;

        case "DesertFlat" :
            belief = 1;

            break;

        case "DesertWyzyny" :
            belief = 1;

            break;

        case "TrawaFlat" :
            food = 1;

            break;

        case "TrawaWyzyny" :
            food = 1;

            break;

        case "ForestFlat" :
            wood = 1;

            break;

        case "ForestWyzyny" :
            wood = 1;

            break;

        case "Mountains" :
            break;

        case "Coast" :
            food = 1;

            break;

        case "RiversideArea" :
            food = 2;

            break;

        case "City" :
            food = 1;
            gold = 1;

            break;
        }
    }

    public double getBelief() {
        return belief;
    }

    public int getBronze() {
        return bronze;
    }

    public void setCoordinates(int i, int j) {
        this.setI(i);
        this.setI(j);
    }

    public int getDyes() {
        return dices;
    }

    public int getFood() {
        return food;
    }

    public double getGold() {
        return gold;
    }

    public int getHorses() {
        return horses;
    }

    public int getI() {
        return i;
    }

    protected void setI(int i) {
        this.i = i;
    }

    public int getIron() {
        return iron;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int x) {
        ownerId = x;
    }

    public List<String> getPossibleBuildings() {
        return possibleBuildings;
    }

    public void setPossibleBuildings(List<String> possibleBuildings) {
        this.possibleBuildings = possibleBuildings;
    }

    public List<String> getResources() {
        return resources;
    }

    public void setResources(List<String> resources) {
        this.resources = resources;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getWood() {
        return wood;
    }
}
