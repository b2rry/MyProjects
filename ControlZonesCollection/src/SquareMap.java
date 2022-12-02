import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class SquareMap {
    private ArrayList<ArrayList<Square>> map;
    private int dimension;

    public SquareMap(int dimension){
        this.dimension = dimension;
        map = new ArrayList<ArrayList<Square>>();
    }

    public void createMap(){
        for(int x = 0; x < dimension; x++){
            ArrayList<Square> buf = new ArrayList<Square>();
            for(int y = 0; y < dimension; y++){
                buf.add(new Square(x,y));
            }
            map.add(x,buf);
        }
    }
    public void fillMap(ArrayList<ControlZone> zonesList){
        for(ControlZone zone : zonesList){
            ArrayList<Square> squaresInZone = zone.getInputSquares();
            for(Square currSquare : squaresInZone){
                int currX = currSquare.getX();
                int currY = currSquare.getY();
                map.get(currX).get(currY).addZone(zone);
            }
        }
    }
    public ControlZone findZone(Point point){
        point.defineSquare(map);
        Square foundSquare = point.getContainingSquare();
        for(ControlZone curr : foundSquare.getZones()){
            if(curr.containPoint(point)) return curr;
        }
        return null;
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
}