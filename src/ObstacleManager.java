import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class ObstacleManager {
     public static ArrayList<Obstacle> obstacles;
     static int FIELD_HEIGHT = DroneGame.FIELD_HEIGHT;
    static int DRONE_SIZE = DroneGame.DRONE_SIZE;
    public static final int OBSTACLE_SIZE = 30;
    public ObstacleManager() {
        obstacles = new ArrayList<>();

        obstacles.add(new Obstacle(3, 4, 'V')); // Вода
        obstacles.add(new Obstacle(7, 7, 'V')); // Вода
        obstacles.add(new Obstacle(7, 8, 'V')); // Вода
        obstacles.add(new Obstacle(6, 9, 'V')); // Вода
        obstacles.add(new Obstacle(5, 9, 'V')); // Вода\
        obstacles.add(new Obstacle(4, 7, 'V')); // Вода
        obstacles.add(new Obstacle(5, 7, 'V')); // Вода
        obstacles.add(new Obstacle(7, 6, 'V')); // Вода
        obstacles.add(new Obstacle(7, 5, 'V')); // Вода
        obstacles.add(new Obstacle(7, 4, 'V')); // Вода
        obstacles.add(new Obstacle(7, 3, 'V')); // Вода
        obstacles.add(new Obstacle(7, 2, 'V')); // Вода
        obstacles.add(new Obstacle(9, 7, 'V'));
        obstacles.add(new Obstacle(6, 12, 'V'));
        obstacles.add(new Obstacle(1, 10, 'V'));
        obstacles.add(new Obstacle(11, 1, 'V'));
        obstacles.add(new Obstacle(4, 4, 'V'));

        obstacles.add(new Obstacle(1, 2, 'K')); // Кущ
        obstacles.add(new Obstacle(4, 6, 'K')); // Кущ
        obstacles.add(new Obstacle(9, 10, 'K')); // Кущ
        obstacles.add(new Obstacle(12, 13, 'K')); // Кущ
        obstacles.add(new Obstacle(6, 3, 'K')); // Кущ
        obstacles.add(new Obstacle(3, 1, 'K')); // Кущ (зроблено двійним)
        obstacles.add(new Obstacle(4, 1, 'K')); // Кущ (зроблено двійним)
        obstacles.add(new Obstacle(8, 2, 'K')); // Кущ (зроблено двійним)
        obstacles.add(new Obstacle(9, 2, 'K')); // Кущ (зроблено двійним)
        obstacles.add(new Obstacle(5, 3, 'K')); // Кущ (зроблено двійним)
        obstacles.add(new Obstacle(6, 5, 'K'));
        obstacles.add(new Obstacle(11, 9, 'K'));
        obstacles.add(new Obstacle(2, 12, 'K'));
        obstacles.add(new Obstacle(14, 5, 'K'));
        obstacles.add(new Obstacle(1, 9, 'K'));
        obstacles.add(new Obstacle(7, 10, 'K'));
        obstacles.add(new Obstacle(8, 14, 'K'));
        obstacles.add(new Obstacle(13, 13, 'K'));
        obstacles.add(new Obstacle(4, 15, 'K'));
        obstacles.add(new Obstacle(12, 8, 'K'));
        obstacles.add(new Obstacle(3, 11, 'K'));
        obstacles.add(new Obstacle(9, 5, 'K'));
        obstacles.add(new Obstacle(5, 13, 'K'));
        obstacles.add(new Obstacle(10, 4, 'K'));
        obstacles.add(new Obstacle(15, 6, 'K'));


        obstacles.add(new Obstacle(11, 8, 'W'));
        obstacles.add(new Obstacle(14, 16, 'W'));
        obstacles.add(new Obstacle(8, 7, 'W'));
        obstacles.add(new Obstacle(3, 12, 'W'));
        obstacles.add(new Obstacle(15, 9, 'W'));
        obstacles.add(new Obstacle(5, 1, 'W'));
        obstacles.add(new Obstacle(12, 6, 'W'));
        obstacles.add(new Obstacle(10, 12, 'W'));
        obstacles.add(new Obstacle(2, 5, 'W'));
        obstacles.add(new Obstacle(7, 14, 'W'));
        obstacles.add(new Obstacle(13, 4, 'W'));
        obstacles.add(new Obstacle(9, 2, 'W'));
        obstacles.add(new Obstacle(1, 14, 'W'));
        obstacles.add(new Obstacle(15, 11, 'W'));
        obstacles.add(new Obstacle(13, 11, 'W'));
        obstacles.add(new Obstacle(9, 6, 'W'));
        obstacles.add(new Obstacle(5, 11, 'W'));
        obstacles.add(new Obstacle(3, 14, 'W'));
        obstacles.add(new Obstacle(12, 2, 'W'));
        obstacles.add(new Obstacle(1, 7, 'W'));
        obstacles.add(new Obstacle(4, 3, 'W'));
        obstacles.add(new Obstacle(2, 6, 'W'));
        obstacles.add(new Obstacle(7, 13, 'W'));
        obstacles.add(new Obstacle(10, 10, 'W'));
        obstacles.add(new Obstacle(15, 3, 'W'));
        obstacles.add(new Obstacle(3, 5, 'W'));
        obstacles.add(new Obstacle(9, 8, 'W'));
        obstacles.add(new Obstacle(2, 9, 'W'));
        obstacles.add(new Obstacle(13, 12, 'W'));
        obstacles.add(new Obstacle(4, 7, 'W'));
        obstacles.add(new Obstacle(10, 11, 'W'));
        obstacles.add(new Obstacle(6, 1, 'W'));
        obstacles.add(new Obstacle(12, 5, 'W'));
        obstacles.add(new Obstacle(7, 15, 'W'));
        obstacles.add(new Obstacle(14, 2, 'W'));
    }


    public static void addObstacles(ArrayList<Obstacle> newObstacles) {
        obstacles.addAll(newObstacles);
    }

    public static void render(GraphicsContext gc) {
        for (Obstacle obstacle : obstacles) {
            if (obstacle.getType() == 'V') {
                gc.setFill(Color.BLUE); // Вода - синій колір
            } else if (obstacle.getType() == 'K') {
                gc.setFill(Color.GREEN); // Кущ - зелений колір
            } else {
                gc.setFill(Color.GRAY); // Стіна - сірий колір (за замовчуванням)
            }
            gc.fillRect(obstacle.getX() * DRONE_SIZE, (FIELD_HEIGHT - 1 - obstacle.getY()) * DRONE_SIZE, OBSTACLE_SIZE, OBSTACLE_SIZE);
        }
    }
}


