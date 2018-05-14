import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class BotPlayer implements Player{
    Circle ball;
    private Map map;
    private Position pos;
    int[][] matrix;
    int x, y, s;

    public BotPlayer(Map m){
        this.map = m;
        this.pos = m.getStartPosition();
        matrix = map.getMap();
        this.s = m.getSize();
        this.ball = new Circle(pos.getX() * m.getUnit()+ m.getUnit()/2, pos.getY() * m.getUnit() + m.getUnit()/2, m.getUnit()/2);
        this.ball.setFill(Color.RED);
        this.ball.setStroke(Color.BLACK);
        this.map.getChildren().add(ball);
        }

    @Override
    public void getBot(int x, int y){
        this.pos.setX(x);
        this.pos.setY(y);
        this.ball.setCenterX(x * map.getUnit() + map.getUnit()/2);
        this.ball.setCenterY(y * map.getUnit() + map.getUnit()/2);
    }
    @Override
    public void moveRight(){
        if ((pos.getX() + 1) >= map.getSize() || matrix[pos.getY()][pos.getX() + 1] == 1){}
        else {
            ball.setCenterX(ball.getCenterX() + map.getUnit());
            pos.setX(pos.getX() + 1);
        }
    }
    @Override
    public void moveLeft(){
        if ((pos.getX() - 1) < 0 || matrix[pos.getY()][pos.getX() - 1] == 1){}
        else {
            ball.setCenterX(ball.getCenterX() - map.getUnit());
            pos.setX(pos.getX() - 1);
        }
    }
    @Override
    public void moveUp(){
        if ((pos.getY() - 1) < 0 || matrix[pos.getY() - 1][pos.getX()] == 1){}
        else {ball.setCenterY(ball.getCenterY() - map.getUnit());
            pos.setY(pos.getY() - 1);
        }
    }
    @Override
    public void moveDown(){
        if ((pos.getY() + 1) >= map.getSize() || matrix[pos.getY() + 1][pos.getX()] == 1){}
        else {
            ball.setCenterY(ball.getCenterY() + map.getUnit());
            pos.setY(pos.getY() + 1);
        }
    }
    @Override
    public Position getPosition(){
        return pos;
    }
}
