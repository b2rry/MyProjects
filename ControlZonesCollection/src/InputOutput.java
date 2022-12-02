import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.Files.readAllLines;

public class InputOutput {
    public int InputIntegerNumber() {
        InputStream inputStream = System.in;
        Reader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        boolean repeatFlag;
        int inputNum = 0;
        String inputStr = null;

        do {
            //System.out.print("Enter integer number: ");
            repeatFlag = false;
            try {
                inputStr = bufferedReader.readLine();
            } catch (IOException ex1) {
                System.out.println(ex1.getMessage());
            }

            try {
                inputNum = Integer.parseInt(inputStr);
            } catch (NumberFormatException ex2) {
                System.out.println("Recognized NumberFormatException " + ex2.getMessage() + ". Input number again.");
                repeatFlag = true;
            }

        } while (repeatFlag);
        return inputNum;
    }

    public double InputDoubleNumber() {
        InputStream inputStream = System.in;
        Reader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        boolean repeatFlag;
        double inputNum = 0.0;
        String inputStr = null;

        do {

            //System.out.print("Enter double number: ");
            repeatFlag = false;
            try {
                inputStr = bufferedReader.readLine();
            } catch (IOException ex1) {
                System.out.println(ex1.getMessage());
            }

            try {
                inputNum = Double.parseDouble(inputStr);
            } catch (NumberFormatException ex2) {
                System.out.println("Recognized NumberFormatException " + ex2.getMessage() + ". Input number again.");
                repeatFlag = true;
            }

        } while (repeatFlag);
        return inputNum;
    }

    public String InputString() {
        InputStream inputStream = System.in;
        Reader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String inputStr = null;

        try {
            inputStr = bufferedReader.readLine(); //читаем строку с клавиатуры
        } catch (IOException ex1) {
            System.out.println(ex1.getMessage());
        }

        return inputStr;
    }
    public void outputZonesList(ArrayList<ControlZone> zones){
        int zoneNum = 0;
        for(ControlZone zone : zones) {
            zoneNum++;
            System.out.println("Zone " + zoneNum + ": " + zone);
        }
        System.out.println();
    }
    public void outputFoundZonesList(ArrayList<ControlZone> foundZones){
        int zoneNum = 0;
        for(ControlZone found : foundZones) {
            zoneNum++;
            System.out.println("Zone " + zoneNum + ": " + found);
        }
        System.out.println();
    }
    public void outputMapFile() throws IOException {
        List<String> lines = readAllLines(Paths.get("/home/kirill/MyProjects/ControlZonesCollection/data/map.txt"));
        for(String line : lines){
            System.out.println(line);
        }
    }
    public void outputPointInfo(Point point){
        Square square = point.getContainingSquare();
        double px = point.getX();
        double py = point.getY();
        double sx = square.getX();
        double sy = square.getY();
        System.out.println("Point coordinates:");
        System.out.println("X coordinate: "+px);
        System.out.println("Y coordinate: "+py+"\n");
        System.out.println("Square containing point coordinates:");
        System.out.println("X coordinate: "+sx);
        System.out.println("Y coordinate: "+sy+"\n");
    }
}
