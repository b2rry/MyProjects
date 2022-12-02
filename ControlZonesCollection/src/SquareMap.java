import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class SquareMap {

    private ArrayList<ArrayList<Square>> map;
    private int webDimensionX;
    private int webDimensionY;
    private int squareDimension;

    public SquareMap(int webDimensionX, int webDimensionY, int squareDimension){
        this.webDimensionX = webDimensionX;
        this.webDimensionY = webDimensionY;
        this.squareDimension = squareDimension;
        map = new ArrayList<ArrayList<Square>>();
    }

    public void createMap(){
        for(int x = 0; x < webDimensionX; x+=squareDimension){
            ArrayList<Square> buf = new ArrayList<Square>();
            for(int y = 0; y < webDimensionY; y+=squareDimension){
                buf.add(new Square(x,y));
            }
            map.add(buf);
        }
    }
    public void fillMap(ArrayList<ControlZone> zonesList){
        for(ControlZone zone : zonesList){
            ArrayList<Square> squaresInZone = zone.getInputSquares();
            for(Square currSquare : squaresInZone){
                int currXInd = currSquare.getX()/squareDimension;
                int currYInd = currSquare.getY()/squareDimension;
                map.get(currXInd).get(currYInd).addZone(zone);
            }
        }
    }
    public ArrayList<ControlZone> findZones(Point point){
        point.defineSquare(map, squareDimension);
        ArrayList<ControlZone> foundZones = new ArrayList<ControlZone>();
        Square foundSquare = point.getContainingSquare();
        for(ControlZone curr : foundSquare.getZones()){
            if(curr.containPoint(point)) foundZones.add(curr);
        }
        return foundZones;
    }
    public void createMapFile() throws IOException {
        String path = "/home/kirill/MyProjects/ControlZonesCollection/data/map.txt";
        Files.delete(Paths.get(path));
        Files.createFile(Paths.get(path));
        ArrayList<String> lines = new ArrayList<String>();
        for(ArrayList<Square> list : map){
            StringBuilder out = new StringBuilder();
            for(Square square : list){
                out.append("Square with coordinates: ").append(square).append("\nControl Zones:\n");//в карту добавляются переносы строки в конце цикла для X из-за последнего переноса в этой строчке кода, баг но удобно=)
                for (ControlZone zone : square.getZones()) {
                    out.append(zone).append("\n");
                }
            }
            lines.add(out.toString());
        }
        Files.write(Paths.get(path),lines);
    }
    public ArrayList<ArrayList<Square>> getMap(){
        return map;
    }

    public int getSquareDimension() {
        return squareDimension;
    }
    public int getWebDimensionX() {
        return webDimensionX;
    }
    public int getWebDimensionY() {
        return webDimensionY;
    }
}