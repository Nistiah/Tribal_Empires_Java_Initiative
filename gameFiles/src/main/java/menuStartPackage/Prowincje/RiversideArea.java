package menuStartPackage.Prowincje;


import java.util.List;

public class RiversideArea extends Province {
    List<String> resources = List.of();
    String type = "RiversideArea";
    List<String> possibleBuildings = List.of("System irygacji");
    List<String> baseBuildings = List.of("Farma nadrzeczna");

    @Override
    public String iconPath(){
        if(ownerId!=0){
            return "provinceIcons/river2.png";
        }else{
            return "provinceIcons/river2 (kopia).png";
        }
    }

    public RiversideArea(){
        setResources(resources);
        setType(type);
        setBaseProduction(type);
        setPossibleBuildings(possibleBuildings);
        setBaseBuildings(baseBuildings);
    }
}
