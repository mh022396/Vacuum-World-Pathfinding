package vacuum_world.Search.World;

public class Position {

    public int x;
    public int y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }
    @Override
    public boolean equals(Object obj){
        if(this == obj)
            return true;
        if(obj == null || obj.getClass()!= this.getClass())
            return false;
        Position p = (Position) obj;
        return this.x == p.x && this.y == p.y;
    }
    @Override
    public int hashCode() {
        int h = (y + ((x+1) / 2));
        return x + (h * h);
    }
}
