import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import static java.nio.file.Files.newInputStream;
import static java.nio.file.Files.readAllLines;

public class CZProcessing {
    ArrayList<ControlZone> zonesList;
    List<String> lines;
    TreeMap<ControlZone,ControlZone> zonesTree;
    SquareMap map;
    ArrayList<ControlZone> foundZones;
    InputOutput inpOut;

    public CZProcessing(){
        zonesList = new ArrayList<ControlZone>();
        zonesTree = new TreeMap<ControlZone,ControlZone>();
        inpOut = new InputOutput();
    }

    public void inputZoneTree() throws IOException {
        lines = readAllLines(Paths.get("/home/kirill/MyProjects/ControlZonesCollection/data/controlZonesTestXY.txt"));
        for(String line : lines){
            ControlZone newObj = new ControlZone(line,map.getSquareDimension(),map.getWebDimensionX(),map.getWebDimensionY());
            zonesTree.put(newObj,newObj);
        }
    }
    public void inputZoneList() throws IOException {
        lines = readAllLines(Paths.get("/home/kirill/MyProjects/ControlZonesCollection/data/handleZones.txt"));
        for(String line : lines){
            zonesList.add(new ControlZone(line,map.getSquareDimension(),map.getWebDimensionX(),map.getWebDimensionY()));
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
        map = new SquareMap(21,21,1);
        map.createMap();
        inputZoneList();//важно запускать после объявления объекта карты
        map.fillMap(zonesList);
        map.createMapFile();
        //Point object = new Point(5.8,5.6);
        Point point = new Point(5.5,5.5);
        foundZones = map.findZones(point);
        inpOut.outputPointInfo(point);
        System.out.println("Tracking zones:");
        inpOut.outputZonesList(zonesList);
        System.out.println("The object is located in the following zones:");
        inpOut.outputFoundZonesList(foundZones);
    }
    public static void main(String[] args) throws IOException {
        CZProcessing run = new CZProcessing();
        InputOutput input = new InputOutput();
        System.out.println("Setting parameters of web in your map.");
        System.out.print("Enter integer width dimension of web in km: ");
        int webDimensionX = input.InputIntegerNumber();
        System.out.print("Enter integer length dimension of web in km: ");
        int webDimensionY = input.InputIntegerNumber();
        System.out.print("Enter integer dimension of square in km (enter number 1 default): ");
        int squareDimension = input.InputIntegerNumber();

        run.map = new SquareMap(webDimensionX,webDimensionY,squareDimension);
        run.map.createMap();
        System.out.println("Web in map is created.");

        boolean addFlag = true;
        while(addFlag) {
        System.out.println("How to add Control Zones to map?\nEnter considered number.\n1. Add file with CZ\n2. Add CZ by hand");
        int variantAdd = input.InputIntegerNumber();
            switch (variantAdd) {
                case 1:
                    run.inputZoneList();//сделать проверку на зону-повторку
                    System.out.println("Zones successfully added.");
                    break;
                case 2:
                    //сделать метод для добавления зоны вручную + проверку выше
                    System.out.println("Zone successfully added.");
                    break;
                case 777:
                    System.out.println("В компьютер скачано 16 дополнительных ГБ оперативной памяти!");
                    break;
                default:
                    System.out.println("Incorrect number.");
            }
            int variantStop;
            System.out.println("Enter 1 if you want to add CZ more, if no - any different number.");
            variantStop = input.InputIntegerNumber();

            addFlag = false;
            if(variantStop == 1) addFlag = true;
        }
        run.map.fillMap(run.zonesList);
        System.out.println("Map successfully filled with CZ.");
        System.out.println("Enter 1 if you want to create map file, if no - any different number.");
        int variantMapFile = input.InputIntegerNumber();
        if(variantMapFile == 1) run.map.createMapFile();//можно сделать в методе запись названия файла вручную + проверку на существование файла с именем

        //добавление точки + выбор действий
        //в идеале сделать обработки IOExceptions в программе
        //дописать все методы в вышеуказанных комментариях

//        CZProcessing run = new CZProcessing();
//        run.testerWithSquaresAndArrayLists();
    }
}
