* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package packman;

import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Wall extends Application {

   @Override
   public void start(Stage primaryStage) {
       Arc e = new Arc(20, 20, 20, 20, 30, 280);
       e.setType(ArcType.ROUND);
       e.setFill(Color.YELLOW);
       e.setStroke(Color.BLACK);

       PathTransition pt = new PathTransition();
       pt.setDuration(Duration.millis(200));


       Pane root = new Pane();
       root.getChildren().add(e);
       Scene scene = new Scene(root);


       primaryStage.setScene(scene);
       primaryStage.show();
   }

   /**
    * @param args the command line arguments
    */
   public static void main(String[] args) {
       launch(args);
   }

}
