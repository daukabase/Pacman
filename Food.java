import java.util.Random;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Food {
    private Map map;
    private int n;int n2;
    private Pane foodPane;
    private Player player;
    private Image image;
    private ImageView imageView;
    private Circle circle;
    private Position foodPosition;
    private Label seconds;
    private final int timer = 5;
    private int numOfCircles = 10;
    private int time;
    private int points;
    private int size;
    private int[][] cells;

    public Food(Map map, Player player) {
        this.map = map;
        this.foodPane = new Pane();
        this.map.getChildren().add(this.foodPane);
        this.player = player;
        this.size = this.map.getSize();
        this.cells = this.map.getMap();
        Thread thread = new Thread(() -> {
            while (this.numOfCircles > 0) {
                this.createFood();
                Platform.runLater(() -> {
                    this.foodPane.getChildren().addAll(new Node[]{this.imageView, this.seconds});
                }
                );
                this.time = 5;
                while (this.time > 0) {
                    Platform.runLater(() -> {
                        this.seconds.setText("" + this.time);
                    }
                    );
                    if (this.player.getPosition().equals(this.foodPosition)) {
                        this.points += this.time;
                        break;
                    }
                    try {
                        Thread.sleep(1000);
                    }
                    catch (InterruptedException var1_2) {
                        // empty catch block
                    }
                    --this.time;
                }
                try {
                    Thread.sleep(10);
                }
                catch (InterruptedException var1_3) {
                    // empty catch block
                }
                Platform.runLater(() -> {
                    this.foodPane.getChildren().clear();
                }
                );
                --this.numOfCircles;
            }
            System.out.println(this.getPoints());
        }
        );
        thread.start();
    }
    public int getPoints() {
        return this.points;
    }

    private void createFood() {
        Random random = new Random();
        double d = this.map.getUnit();
        do {
            n = random.nextInt(this.size);
            n2 = random.nextInt(this.size);
        } while (this.player.getPosition().equals(new Position(n, n2)) || this.map.getMap()[n2][n] == 0);
        if ((int) Math.random() * 2 == 0) {this.imageView = new ImageView(new Image("file:1.png"));}
        else {this.imageView = new ImageView(new Image("file:2.png"));}

        this.imageView.setFitHeight(d * 6 / 7);
        this.imageView.setFitWidth(d * 6 / 7);
        this.imageView.setX(n * d + d / 14);
        this.imageView.setY(n2 * d + d / 14);

        this.foodPosition = new Position(n, n2);
        this.seconds = new Label("5");
        this.seconds.setTranslateX((double)n * d);
        this.seconds.setTranslateY((double)n2 * d);
    }

    public Position getPosition() {return this.foodPosition;}
    public int getPosX(){return n;}
    public int getPosY(){return n2;}
}
