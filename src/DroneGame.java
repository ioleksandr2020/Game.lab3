import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.ArrayList;


public class DroneGame extends Application {

    public static final int FIELD_WIDTH = 15;
    public static final int FIELD_HEIGHT = 20;
    public static final int DRONE_SIZE = 30;


    private Drone drone1;
    private Drone drone2;
    private static char codeprew1 = (char) 0;
    private static char codeprew2 = (char) 0;
    private AnimationTimer animationTimer;

    public static char getcodeprew1() {
        return codeprew1;
    }

    public static char getcodeprew2() {
        return codeprew2;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Гра з дронами");

        Pane root = new Pane();
        Scene scene = new Scene(root, FIELD_WIDTH * DRONE_SIZE, FIELD_HEIGHT * DRONE_SIZE);
        primaryStage.setScene(scene);

        Canvas canvas = new Canvas(FIELD_WIDTH * DRONE_SIZE, FIELD_HEIGHT * DRONE_SIZE);
        root.getChildren().add(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();

        drone1 = new Drone(13, 0);
        drone2 = new Drone(2, 19);
        ObstacleManager obstacleManager = new ObstacleManager();
        ArrayList<Obstacle> obstacles = ObstacleManager.obstacles; // Отримання доступу до масиву obstacles

        scene.setOnKeyPressed(event -> {
            KeyCode code = event.getCode();
            if (code == KeyCode.W) {
                codeprew1 = 'W';
                drone1.move(0, 1, obstacles);
            } else if (code == KeyCode.S) {
                codeprew1 = 'S';
                drone1.move(0, -1, obstacles);
            } else if (code == KeyCode.A) {
                codeprew1 = 'A';
                drone1.move(-1, 0, obstacles);
            } else if (code == KeyCode.D) {
                codeprew1 = 'D';
                drone1.move(1, 0, obstacles);
            } else if (code == KeyCode.I) {
                codeprew2 = 'I';
                drone2.move(0, 1, obstacles);
            } else if (code == KeyCode.K) {
                codeprew2 = 'K';
                drone2.move(0, -1, obstacles);
            } else if (code == KeyCode.J) {
                codeprew2 = 'J';
                drone2.move(-1, 0, obstacles);
            } else if (code == KeyCode.L) {
                codeprew2 = 'L';
                drone2.move(1, 0, obstacles);
            } else if ((code == KeyCode.Q || code == KeyCode.E)) {
                if (codeprew1 == 'W') {
                    drone1.shootUp();
                }
                if (codeprew1 == 'S') {
                    drone1.shootDown();
                }
                if (codeprew1 == 'D') {
                    drone1.shootRight();
                }
                if (codeprew1 == 'A') {
                    drone1.shootLeft();
                }
            } else if ((code == KeyCode.O || code == KeyCode.U)) {
                if (codeprew2 == 'I') {
                    drone2.shootUp();
                }
                if (codeprew2 == 'K') {
                    drone2.shootDown();
                }
                if (codeprew2 == 'L') {
                    drone2.shootRight();
                }
                if (codeprew2 == 'J') {
                    drone2.shootLeft();
                }
            }
        });


        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateGame();
                drawGame(gc);
            }
        };

        animationTimer.start();

        primaryStage.show();
        drawGame(gc);
    };

    private void updateGame() {
        drone1.updateBulletPosition(drone2, ObstacleManager.obstacles);
        drone2.updateBulletPosition(drone1, ObstacleManager.obstacles);
    }

    private void drawGame(GraphicsContext gc) {
        gc.clearRect(0, 0, FIELD_WIDTH * DRONE_SIZE, FIELD_HEIGHT * DRONE_SIZE);

        gc.setFill(Color.YELLOW);
        gc.fillRect(drone1.getX() * DRONE_SIZE, (FIELD_HEIGHT - 1 - drone1.getY()) * DRONE_SIZE, DRONE_SIZE, DRONE_SIZE);
        gc.setFill(Color.RED);
        gc.fillRect(drone2.getX() * DRONE_SIZE, (FIELD_HEIGHT - 1 - drone2.getY()) * DRONE_SIZE, DRONE_SIZE, DRONE_SIZE);

        ObstacleManager.render(gc);

        drone1.drawBulletForDrone1(gc, drone1);
        drone2.drawBulletForDrone2(gc, drone2);

        checkGameOver();
    }


    private void checkGameOver() {
        if (drone1.getHealth() <= 0 || drone2.getHealth() <= 0) {
            animationTimer.stop();
            String winner = (drone1.getHealth() <= 0) ? "Дрон 2" : "Дрон 1";
            System.out.println("Гра завершена! " + winner + " переміг.");
        }
    }
}

