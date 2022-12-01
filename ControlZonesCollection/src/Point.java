public class Point extends ControlZone{
    private double distance;
    public Point(double x,double y){
        super();
        distance = 0;
        this.x = x;
        this.y = y;
    }

    public int compareTo(ControlZone o){
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
    public void countDistance(double x,double y) {
        this.distance = Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2));
    }
    public int createCompNum(double thisXY,double oXY,double r){
        int compNum = 0;
        if(thisXY > oXY) compNum++;
        if(thisXY > (oXY+r)) compNum++;
        if(thisXY > (oXY-r)) compNum++;
        return compNum;
    }
}