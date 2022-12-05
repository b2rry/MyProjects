import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

import static java.nio.file.Files.readAllLines;

public class CZProcessing {
    ArrayList<ControlZone> zonesList;
    HashMap<String,ControlZone> checkList;
    List<String> lines;
    TreeMap<ControlZone,ControlZone> zonesTree;
    SquareMap map;
    Point point;
    ArrayList<ControlZone> foundZones;
    InputOutput inpOut;

    public CZProcessing(){
        zonesList = new ArrayList<ControlZone>();
        zonesTree = new TreeMap<ControlZone,ControlZone>();
        checkList = new HashMap<String, ControlZone>();
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
            ControlZone currZone = new ControlZone(line,map.getSquareDimension(),map.getWebDimensionX(),map.getWebDimensionY());
            addZone(currZone,false);
        }
    }
    public void inputZoneByHand(int id, double x,double y, double radius,boolean addAfterCreationFlag){
        System.out.print("Enter ID of zone:(int now) ");
        id = inpOut.InputIntegerNumber();
        System.out.print("Enter X coordinate of zone: ");
        x = inpOut.InputDoubleNumber();
        System.out.print("Enter Y coordinate of zone: ");
        y = inpOut.InputDoubleNumber();
        System.out.print("Enter Radius of zone: ");
        radius = inpOut.InputDoubleNumber();
        ControlZone currZone = new ControlZone(id,x,y,radius,map.getSquareDimension(),map.getWebDimensionX(),map.getWebDimensionY());
        addZone(currZone,addAfterCreationFlag);
    }
    private void addZone(ControlZone zone,boolean addAfterCreationFlag){
        if(checkList.containsKey(zone.checkStr)){
            //вывод об ошибке добавления
            System.out.println("Error of creation zone, zone with such coordinates exists yet.");
        }else{
            checkList.put(zone.checkStr, zone);
            zonesList.add(zone);
            System.out.println("Zone(s) successfully added.");
            if(addAfterCreationFlag){
                map.addOneZoneInMap(zone);
            }
        }
    }
    public void changePoint(double x,double y){
        point = new Point(x,y);
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
        boolean restartFlag = true;
        InputOutput input = new InputOutput();
        while(restartFlag) {
            restartFlag = false;
            CZProcessing run = new CZProcessing();
            System.out.println("Setting parameters of web in your map.");
            System.out.print("Enter integer width dimension of web in km: ");
            int webDimensionX = input.InputIntegerNumber();
            System.out.print("Enter integer length dimension of web in km: ");
            int webDimensionY = input.InputIntegerNumber();
            System.out.print("Enter integer dimension of square in km (enter number 1 default): ");
            int squareDimension = input.InputIntegerNumber();

            run.map = new SquareMap(webDimensionX, webDimensionY, squareDimension);
            run.map.createMap();
            System.out.println("Web in map is created.");

            boolean addFlag = true;
            while (addFlag) {
                System.out.println("How to add Control Zones to map?\nEnter considered number.\n1. Add file with CZ\n2. Add CZ by hand");
                int variantAdd = input.InputIntegerNumber();
                switch (variantAdd) {//важно запускать после объявления объекта карты ( createMap() )
                    case 1:
                        run.inputZoneList();
                        break;
                    case 2:
                        run.inputZoneByHand(0, 0, 0, 0,false);
                        break;
                    default:
                        System.out.println("Incorrect number. Zones haven't been added, try again.");
                }
                int variantStopAdd;
                System.out.println("Enter 1 if you want to add CZ more, if no - any different number.");
                variantStopAdd = input.InputIntegerNumber();

                addFlag = false;
                if (variantStopAdd == 1) addFlag = true;
            }
            run.map.fillMap(run.zonesList);
            System.out.println("Map successfully filled with CZ.");
            System.out.println("Enter 1 if you want to create map file, if no - any different number.");
            int variantMapFile = input.InputIntegerNumber();
            if (variantMapFile == 1){
                run.map.createMapFile();//можно сделать в методе запись названия файла вручную + проверку на существование файла с именем
                System.out.println("Map file created.");
            }
            System.out.println("Begin to create a point.");
            System.out.println("Enter X coordinate of desired point: ");
            double pX = input.InputDoubleNumber();
            System.out.println("Enter Y coordinate of desired point: ");
            double pY = input.InputDoubleNumber();
            run.changePoint(pX, pY);
            run.foundZones = run.map.findZones(run.point);
            System.out.println("Point created.");
            boolean repeatActionFlag = true;
            while (repeatActionFlag) {
                System.out.println("Enter action");
                System.out.println("1. Change point\n2. Output point info\n3. Output zones list\n4. Output point containing zones list\n5. Output Map file\n6. Create new map(restart)\n7. Add CZ\n8. Recreate Map file(if new zone added)\n0. End program");
                int variantAction = input.InputIntegerNumber();
                switch (variantAction) {
                    case 1://changePoint
                        System.out.println("Enter X coordinate of desired point: ");
                        double tempX = input.InputDoubleNumber();
                        System.out.println("Enter Y coordinate of desired point: ");
                        double tempY = input.InputDoubleNumber();
                        run.changePoint(tempX, tempY);
                        run.foundZones = run.map.findZones(run.point);
                        System.out.println("Point changed.");
                        break;
                    case 2://point info
                        run.inpOut.outputPointInfo(run.point);
                        break;
                    case 3://zones list
                        run.inpOut.outputZonesList(run.zonesList);
                        break;
                    case 4://foundZones list
                        run.inpOut.outputFoundZonesList(run.foundZones);
                        break;
                    case 5://output mapFile
                        run.inpOut.outputMapFile();
                        break;
                    case 6://restart map creation
                        restartFlag = true;
                        repeatActionFlag = false;
                        break;
                    case 7://add CZ
                        run.inputZoneByHand(0, 0, 0, 0,true);
                        run.changePoint(run.point.getX(), run.point.getY());
                        run.foundZones = run.map.findZones(run.point);
                        break;
                    case 8://recreate Map
                        run.map.createMapFile();
                        System.out.println("Map recreated.");
                        break;
                    case 0://exit
                        repeatActionFlag = false;
                        break;
                    case 777:
                        System.out.println("В компьютер скачано 16 дополнительных ГБ оперативной памяти!");
                        break;
                    default:
                        System.out.println("Incorrect number.");
                }
            }
        }
        //в идеале сделать обработки IOExceptions в программе
    }
}