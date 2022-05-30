package menuStartPackage.Prowincje;



import java.util.Arrays;
import java.util.List;

public class Coast extends Province {
    List<String> resources = Arrays.asList("bursztyn", "owoce morza");
    String type = "Coast";
    List<String> possibleBuildings = Arrays.asList("Zbieracze bursztynu", "Zbieracze owoców morza");
    List<String> baseBuildings = List.of("latarnia");

    @Override
    public String iconPath(){
        if(ownerId!=0){
            return "provinceIcons/wybrzeze.jpg";
        }else{
            return "provinceIcons/wybrzeze (kopia).jpg";
        }
    }


    public Coast(){
        setResources(resources);
        setType(type);
        setBaseProduction(type);
        setPossibleBuildings(possibleBuildings);
        setBaseBuildings(baseBuildings);
    }
}
