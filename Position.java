public class Position {
    private int x, y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }
    public Position(Position p){
        this.x = p.getX();
        this.y = p.getY();
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public boolean equals(Position p){
        boolean c = false;
        if(this.x == p.x &&  this.y == p.y){
            c = true;
        }
        return c;
    }
    public boolean equalss(Object o){
        if(!(o instanceof Position)) return false;
            return (((Position)o).getX()==x) &&(((Position)o).getY()==y);
    }
    public void toStr(){
      System.out.println("X is "+x+" Y is "+y);
    }
}
