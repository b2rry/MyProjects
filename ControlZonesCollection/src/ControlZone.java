import java.util.ArrayList;
import java.util.Objects;

public class ControlZone implements Comparable<ControlZone>{
    private int id;
    protected double x;
    protected double y;
    protected double radius;
    protected ArrayList<Square> inputSquares;

    public ControlZone(String inputZone, int squareDimension, int webDimensionX, int webDimensionY){
        String[] mass = inputZone.split(" ");
        id = Integer.parseInt(mass[0]);
        x = Double.parseDouble(mass[1]);
        y = Double.parseDouble(mass[2]);
        radius = Double.parseDouble(mass[3]);
        defineSquares(squareDimension,webDimensionX-1,webDimensionY-1);
    }
    public ControlZone(){
        id = 0;
        x = 0;
        y = 0;
        radius = 0;
    }

    public void defineSquares(int squareDimension, int webMaxX, int webMaxY){
        inputSquares = new ArrayList<Square>();
        double begX;
        double begY;
        int dimensionResultSquare;
        int bufNum;//максимальное кол-во затрагиваемых зоной квадратов в бок от центрального
        if(radius/squareDimension == (int)(radius/squareDimension)){//если радиус кратен длине квадрата
                bufNum = (int)(radius / squareDimension);
        }else{ //предполагается узнавать максимальное кол-во затрагиваемых зоной квадратов в бок от центрального и добавлять один квадрат если радиус не кратен длине квадрата (bufNum = ((int)radius / колво км в одном квадрате)+1)
            bufNum = (int)(radius/squareDimension)+1;
        }
        dimensionResultSquare = (bufNum*2)+1;
        begX = (int)x;
        begY = (int)y;
        while(begX/squareDimension - (int)(begX/squareDimension) != 0) {
            begX--;
        }
        while(begY/squareDimension - (int)(begY/squareDimension) != 0) {
            begY--;
        }
        begX = begX-(bufNum*squareDimension);//координаты верхнего левого квадрата в который возможно входит зона. (int)x/(int)y - координаты содержащего в себе центр зоны квадрата
        begY = begY-(bufNum*squareDimension);//нужно сделать улучшенный поиск для точек на сетке
        for(int i = 0; i < dimensionResultSquare; i++){
            for(int j = 0; j < dimensionResultSquare; j++){
                if((begX >=0 && begY >= 0) && (begX <= webMaxX && begY <= webMaxY)) inputSquares.add(new Square((int)begX,(int)begY));
                begY+=squareDimension;
            }
            begY-=dimensionResultSquare*squareDimension;
            begX+=squareDimension;
        }
    }
    public boolean containPoint(Point o){
        if(this.radius >= o.countAndReturnDistance(x,y)) return true;
        return false;
    }

    public int compareTo(ControlZone o){
        //System.out.print("1");
        if (this.x > o.x){
            return 1;
        }else if(this.x == o.x){
            if(this.y > o.y){
                return 1;
            }else if(this.y == o.y){
                return 0;
            }
        }
        return -1;
    }

    public boolean equals(Object o){
        System.out.print("2");
        ControlZone obj = (ControlZone) o;
        if(this.hashCode() == obj.hashCode()){
            return true;
        }
        return false;
    }
    public int hashCode(){
        System.out.print("3");
        String str = Double.toString(x);
        String str2 = Double.toString(y);
        String ret = str + str2;
        return Objects.hash(ret);
    }
    public String toString(){
        return "id: "+id+" X coord: "+x+" Y coord: "+y+" radius: "+radius;
    }
    public ArrayList<Square> getInputSquares(){
        return inputSquares;
    }
}
