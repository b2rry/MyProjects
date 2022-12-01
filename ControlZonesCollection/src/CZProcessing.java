import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import static java.nio.file.Files.readAllLines;

public class CZProcessing {
    ArrayList<ControlZone> list;
    List<String> lines;
    TreeMap<ControlZone,ControlZone> tree;

    public CZProcessing(){
        list = new ArrayList<ControlZone>();
        tree = new TreeMap<ControlZone,ControlZone>();
    }

    public void testingOutput(){
        for(ControlZone cz : tree.keySet()){
            System.out.println(cz);
        }
    }
    public void testingInputTree() throws IOException {
        lines = readAllLines(Paths.get("/home/kirill/MyProjects/ControlZonesCollection/data/controlZonesTestXY.txt"));
        for(String line : lines){
            ControlZone newObj = new ControlZone(line);
            tree.put(newObj,newObj);
        }
    }
    public void testingInputList() throws IOException {
        lines = readAllLines(Paths.get("/home/kirill/MyProjects/ControlZonesCollection/data/controlZonesTestXY.txt"));
        for(String line : lines){
            list.add(new ControlZone(line));
        }
    }
    public void testBiggerThan(ControlZone a, ControlZone b){
        System.out.println("\n"+ a+"\n"+b+"\n");
        System.out.println(a.compareTo(b));
    }
    public void tester(){
        Point need = new Point(82,48);
        System.out.println(need);
        System.out.println(tree.get(need));//видимо нужно переопределять equals и hashCode
//        System.out.println(need.hashCode());
//        System.out.println(tree.get(need).hashCode());
//        System.out.println(need.equals(tree.get(need)));
    }
}
