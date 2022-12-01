import java.util.ArrayList;

public class Square {
    private int x;
    private int y;
    private ArrayList<ControlZone> inputZones;

    public Square(int x, int y){
        inputZones = new ArrayList<ControlZone>();
        this.x = x;
        this.y = y;
    }
    public void addZone(ControlZone inputZ){
        inputZones.add(inputZ);
    }
    public ArrayList<ControlZone> getZones(){
        return inputZones;
    }
    public String toString(){
        return " X "+x+" "+"Y "+y;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
}
