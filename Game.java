import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import static javafx.scene.input.KeyCode.DOWN;
import static javafx.scene.input.KeyCode.LEFT;
import static javafx.scene.input.KeyCode.RIGHT;
import static javafx.scene.input.KeyCode.UP;
import javafx.stage.Stage;

public class Game extends Application {
    Position fp;
    Position[] path;
    @Override
    public void start(Stage primaryStage) throws IOException{
        Map m = new Map("map0.txt");
        BotPlayer bp = new BotPlayer(m);

        Food food = new Food(m, bp);
		//bp.DoIt(food);

		new Thread(() -> {
			try{
				for (int i = 0; i<10; i++){
				Thread.sleep(500);

  				PathFinder pathFinder = new PathFinder(m.getMap());
  				Position[] path = pathFinder.find(new Position(bp.getPosition()), new Position(food.getPosition()));

          for (Position pass : path) {
            pass.toStr();
          }

          for(Position p: path){
              Platform.runLater(() ->{bp.getBot(p.getX(), p.getY());});
              Thread.sleep(180);
          }
				}
			}
          catch (InterruptedException ex) {}
        }).start();

        Scene scene = new Scene(m);
        primaryStage.setTitle("PACMAN");
        primaryStage.setScene(scene);
        primaryStage.show();
        m.requestFocus();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
