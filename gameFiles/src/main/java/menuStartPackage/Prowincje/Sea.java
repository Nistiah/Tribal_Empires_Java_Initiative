package menuStartPackage.Prowincje;
import java.util.Arrays;
import java.util.List;

public class Sea extends Province {

    List<String> resources = Arrays.asList("ryby", "barwiniki");
    String type = "Sea";
    List<String> possibleBuildings = Arrays.asList("Poławiacze ryb", "Poławiacze szkarłatników");
    List<String> baseBuildings = Arrays.asList();

    @Override
    public String iconPath(){
        if(ownerId!=0){
            return "provinceIcons/sea2.jpg";
        }else{
            return "provinceIcons/sea2 (kopia).jpg";
        }
    }



    public Sea(){
        setResources(resources);
        setType(type);
        setBaseProduction(type);
        setPossibleBuildings(possibleBuildings);
        setBaseBuildings(baseBuildings);
    }
}
