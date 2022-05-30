package menuStartPackage.Prowincje;

import java.util.Arrays;
import java.util.List;

public class Mountains extends Province {
    List<String> resources = List.of();
    String type = "Mountains";
    List<String> possibleBuildings = List.of();
    List<String> baseBuildings = List.of();

    @Override
    public String iconPath(){
        if(ownerId!=0){
            return "provinceIcons/mountain.png";
        }else{
            return "provinceIcons/mountain (kopia).png";
        }
    }

    public Mountains(){
        setResources(resources);
        setType(type);
        setBaseProduction(type);
        setPossibleBuildings(possibleBuildings);
        setBaseBuildings(baseBuildings);
    }
}
