import java.util.Objects;

public class ControlZone implements Comparable<ControlZone>{
    private int id;
    protected double x;
    protected double y;
    protected double radius;

    public ControlZone(String inputZone){
        String[] mass = inputZone.split(" ");
        id = Integer.parseInt(mass[0]);
        x = Double.parseDouble(mass[1]);
        y = Double.parseDouble(mass[2]);
        radius = Double.parseDouble(mass[3]);
    }
    public ControlZone(){
        id = 0;
        x = 0;
        y = 0;
        radius = 0;
    }

    public int compareTo(ControlZone o){
        System.out.print("1");
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
    public String toString(){
        return "id: "+id+" X coord: "+x+" Y coord: "+y+" radius: "+radius;
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
}
