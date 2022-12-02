import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import static java.nio.file.Files.readAllLines;

public class CZProcessing {
    ArrayList<ControlZone> zonesList;
    List<String> lines;
    TreeMap<ControlZone,ControlZone> zonesTree;

    public CZProcessing(){
        zonesList = new ArrayList<ControlZone>();
        zonesTree = new TreeMap<ControlZone,ControlZone>();
    }

    public void inputZoneTree() throws IOException {
        lines = readAllLines(Paths.get("/home/kirill/MyProjects/ControlZonesCollection/data/controlZonesTestXY.txt"));
        for(String line : lines){
            ControlZone newObj = new ControlZone(line);
            zonesTree.put(newObj,newObj);
        }
    }
    public void inputZoneList() throws IOException {
        lines = readAllLines(Paths.get("/home/kirill/MyProjects/ControlZonesCollection/data/handleZones.txt"));
        for(String line : lines){
            zonesList.add(new ControlZone(line));
        }
    }
    public void testingOutputTree(){
        for(ControlZone cz : zonesTree.keySet()){
            System.out.println(cz);
        }
    }
    public void testBiggerThan(ControlZone a, ControlZone b){
        System.out.println("\n"+ a+"\n"+b+"\n");
        System.out.println(a.compareTo(b));
    }
    public void testerWithTree(){
        Point need = new Point(82.5,47.5);
        System.out.println(need);
        System.out.println(zonesTree.get(need));
    }
    public void testerWithSquaresAndArrayLists() throws IOException {
        inputZoneList();
        SquareMap map = new SquareMap(21);
        map.createMap();
        map.fillMap(zonesList);
        map.createMapFile();
        //Point object = new Point(5.8,5.6);
        Point object = new Point(11,11);
        ControlZone containingZone = map.findZone(object);
        System.out.println("Found zone: "+ containingZone);
    }
    public static void main(String[] args) throws IOException {
        CZProcessing run = new CZProcessing();
        run.testerWithSquaresAndArrayLists();
    }
}
