import java.util.ArrayList;

public class Point extends ControlZone{
    private double distance;
    private Square containingSquare;
    public Point(double x,double y){
        super();
        distance = 0;
        this.x = x;
        this.y = y;
    }

    public void defineSquare(ArrayList<ArrayList<Square>> map){//map не объект типа SquareMap потому что вызывается из класса SquareMap
        int sqX = (int)x;//улучшать для нахождения на сетке
        int sqY = (int)y;
        containingSquare = map.get(sqX).get(sqY);
    }
    public void countDistance(double x,double y) {
        this.distance = Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2));
    }
    public double countAndReturnDistance(double x,double y) {
        return Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2));
    }
    public int compareToFORTREE(ControlZone o){
        System.out.println("\nenter");
        this.countDistance(o.x,o.y);
        System.out.print("   "+this.x);
        System.out.print("   "+this.y);
        System.out.print("   "+o.x);
        System.out.print("   "+o.y);
        System.out.print("  radius cz - "+o.radius);
        System.out.println("  distance - "+this.distance);

        int compNumX = createCompNum(this.x,o.x,o.radius);
        if (this.x > o.x && (compNumX == 0 || compNumX == 3)){
            System.out.print(" this.x > o.x");
            return 1;
        }else if(this.x == o.x || (compNumX != 0 && compNumX != 3)){
            System.out.print(" this.x == o.x");
            int compNumY = createCompNum(this.y,o.y,o.radius);
            if(this.y > o.y && (compNumY == 0 || compNumY == 3)){
                System.out.print(" this.y > o.y");
                return 1;
            }else if(this.y == o.y || (compNumY != 0 && compNumY != 3)){
                if(o.radius >= this.distance) {
                    System.out.println("Congratulations!");
                    return 0;
                }
            }
        }
        System.out.println(" -1");
        return -1;
    }
    public int createCompNum(double thisXY,double oXY,double r){
        int compNum = 0;
        if(thisXY > oXY) compNum++;
        if(thisXY > (oXY+r)) compNum++;
        if(thisXY > (oXY-r)) compNum++;
        return compNum;
    }
    public Square getContainingSquare(){
        return containingSquare;
    }
}
