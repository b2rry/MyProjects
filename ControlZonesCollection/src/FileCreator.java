import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileCreator {
    private Path path;
    private int latitude;
    private double x;
    private int longitude;
    private double y;
    private int id;

    public FileCreator(String path){
        this.path = Paths.get(path);
        //latitude = -89;
        x = -100;
        //longitude = -179;
        y = -100;
        id = 0;
    }
    public void createFile() throws IOException {
        Files.createFile(path);
    }
    public void addZones() throws IOException {
        ArrayList<String> lines = new ArrayList<String>();
        for(; latitude < 90; latitude++){
            for(; longitude < 181; longitude++){
                lines.add(createStr());
                id++;
            }
            longitude = -179;
        }
        Files.write(path,lines);
    }
    public void addZonesTest() throws IOException {
        ArrayList<String> lines = new ArrayList<String>();
        for(; x < 101; x+=10){
            for(; y < 101; y+=10){
                lines.add(createStrTest());
                id++;
            }
            y = -100;
        }
        Files.write(path,lines);
    }
    public String createStr(){
        int radius = (int) (10 + Math.random()*91);
        return id + " " + latitude + " " + longitude + " " + radius;
    }
    public String createStrTest(){
        int radius = (int) (1 + Math.random()*5);
        return id + " " + x + " " + y + " " + radius;
    }
}
