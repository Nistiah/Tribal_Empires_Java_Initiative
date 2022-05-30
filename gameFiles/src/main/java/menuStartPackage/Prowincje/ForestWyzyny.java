package menuStartPackage.Prowincje;

import java.util.Arrays;
import java.util.List;
public class ForestWyzyny extends Province {
    List<String> resources = Arrays.asList("drewno", "brąz", "żelazo", "złoto");
    String type = "ForestWyzyny";
    List<String> possibleBuildings = Arrays.asList("Tartak", "Kopalnia brąz", "Kopalnia żelazo", "Kopalnia złoto", "Wycięcie lasu");
    List<String> baseBuildings = List.of("Łowca");

    @Override
    public String iconPath(){
        if(ownerId!=0){
            return "provinceIcons/forest - wyz.png";
        }else{
            return "provinceIcons/forest - wyz (kopia).png";
        }
    }


    public ForestWyzyny(){
        setResources(resources);
        setType(type);
        setBaseProduction(type);
        setPossibleBuildings(possibleBuildings);
        setBaseBuildings(baseBuildings);
    }
}
