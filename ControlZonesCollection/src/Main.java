import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        //FileCreator test = new FileCreator("/home/kirill/MyProjects/ControlZonesCollection/data/controlZonesTestXY.txt");
        //test.createFile();
        //test.addZonesTest();
        CZProcessing run = new CZProcessing();
        run.testingInputTree();
        run.tester();
//        System.out.println(0b000+ " "+ 0b001+ " "+0b010+" "+0b011+" "+0b100);
//        System.out.println(0b010&0b000);
//        System.out.println(0b000|0b000);
//        System.out.println(0b111^0b000);
    }
}