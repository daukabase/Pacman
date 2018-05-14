import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Map extends Pane{
    int[][] array;
    Position start;
    Circle c;
    private int x, y, s, u = 40;

    public Map(String m) throws IOException{
        Scanner in = new Scanner(new File(m));

        int n = in.nextInt();
        array = new int[n][n];
        s = n;
        int x = 0, y = 0;

        for(int i = 0; i < n; i++){
            for(int j = 0; j<n; j++){
                Rectangle rec = new Rectangle(x * u, y * u, u, u);
                array[i][j] = in.nextInt();
                rec.setStroke(Color.BLACK);
                rec.setFill(Color.WHITE);

                ImageView iv = new ImageView(new Image("file:w1.png"));
                iv.setFitHeight(u);
                iv.setFitWidth(u);
                iv.setX(x * u);
                iv.setY(y * u);
                if (array[i][j] == 1){
                    getChildren().add(rec);
                }
                else if (array[i][j] == 0){
                    getChildren().add(rec);
                    getChildren().add(iv);}
                x++;
                if(x == n){x = 0; y++;}
            }
        }
        randomPosition();
    }

    public void randomPosition(){
        x = (int) (Math.random() * s);
        y = (int) (Math.random() * s);
        if (array[y][x] == 0){randomPosition();}
        else{start = new Position(x, y);}
    }
    public int[][] getMap(){
        return array;
    }
    public Position getStartPosition(){
        return start;
    }
    public int getSize(){
        return s;
    }
    public int getUnit(){
        return u;
    }
}
