import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PathFinder{
    int[][] fillmap;
    int[][] map;
    List<Position> buf = new ArrayList<>();
    int size;

    PathFinder(int[][] map){
        this.map = map;
        this.fillmap = new int[map.length][map.length];
    }

    void push(Position p, int n){
        if(fillmap[p.getY()][p.getX()]<=n) return;
        fillmap[p.getY()][p.getX()]=n;
        buf.add(p);
    }

    Position pop(){
        if(buf.isEmpty()) return null;
        return (Position)buf.remove(0);
    }

    Position[] find(Position start, Position end){
        int tx=0, ty=0, n = 0, t=0;
        Position p;
        for(int i=0; i<fillmap.length;i++)
            Arrays.fill(fillmap[i], Integer.MAX_VALUE);
        push(start, 0);
        while((p = pop())!=null){
          n=fillmap[p.getY()][p.getX()]+map[p.getY()][p.getX()];
          size = map.length;

          if((p.getY()+1<map.length)&&map[p.getY()+1][p.getX()]!=0) push(new Position(p.getX(), p.getY()+1), n);
          if((p.getY()-1>=0)&&(map[p.getY()-1][p.getX()]!=0)) push(new Position(p.getX(), p.getY()-1), n);
          if((p.getX()+1<map.length)&&(map[p.getY()][p.getX()+1]!=0)) push(new Position(p.getX()+1, p.getY()), n);
          if((p.getX()-1>=0)&&(map[p.getY()][p.getX()-1]!=0)) push(new Position(p.getX()-1, p.getY()), n);

        }


        List<Position> path = new ArrayList<>();
        path.add(end);
        int x = end.getX();
        int y = end.getY();
        n = Integer.MAX_VALUE;
        while((x!=start.getX())||(y!=start.getY())){
          
            if(x<size-1)  {if(fillmap[y][x+1]<n){tx=x+1; ty=y; t=fillmap[y][x+1];}}
            if(y<size-1)  {if(fillmap[y+1][x]<n){tx=x; ty=y+1; t=fillmap[y+1][x];}}

            if (y>0){if((fillmap[y-1][x]<n)){tx=x; ty=y-1; t=fillmap[y-1][x];}}
            if (x>0){if((fillmap[y][x-1]<n)){tx=x-1; ty=y; t=fillmap[y][x-1];}}

            x = tx;
            y = ty;
            n = t;

            Position p0 = new Position(x, y);
            path.add(p0);
        }

	      Position[] result = new Position[path.size()];
        t = path.size();
        for(Object position: path)
            result[--t] = (Position)position;
         return result;
    }
}
