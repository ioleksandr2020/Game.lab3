import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.List;

public class Drone {
    private int x;
    private int y;
    private double bulletX;
    private double bulletY;
    private boolean hasBullet;
    private double dx;
    private double dy;
    static int FIELD_WIDTH = DroneGame.FIELD_WIDTH;
    private static final double BULLET_SPEED = 0.3;
    static int FIELD_HEIGHT = DroneGame.FIELD_HEIGHT;
    static int DRONE_SIZE = DroneGame.DRONE_SIZE;

    private int health;
    private int damage;

    public Drone(int x, int y) {
        this.x = x;
        this.y = y;
        this.hasBullet = false;
        this.dx = 0;
        this.dy = 0;
        this.health = 200;
        this.damage = 50;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getHealth() {
        return health;
    }


    public void move(int dx, int dy, List<Obstacle> obstacles) {
        int newX = x + dx;
        int newY = y + dy;
        boolean canMove = true;
        for (Obstacle obstacle : obstacles) {
            if ((newX == obstacle.getX() && newY == obstacle.getY()) && (obstacle.getType() == 'W' || obstacle.getType() == 'V')) {
                canMove = false;
                break;
            }
        }
        if (newX >= 0 && newX < FIELD_WIDTH && newY >= 0 && newY < FIELD_HEIGHT && canMove) {
            x = newX;
            y = newY;
        }
    }


    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
        if (health == 0) {
            removeBullet();
        }
    }

    private int point = 0;

    public void shootLeft() {
        if (!hasBullet) {
            point = 1;
            bulletX = x - 1.0;
            bulletY = y + 0.5;
            dx = -BULLET_SPEED;
            dy = 0;
            hasBullet = true;
            damage = (int) (Math.random() * 0.5 * this.damage + 0.75 * this.damage);
        }
    }

    public void shootRight() {
        if (!hasBullet) {
            point = 1;
            bulletX = x + 1.0;
            bulletY = y + 0.5;
            dx = BULLET_SPEED;
            dy = 0;
            hasBullet = true;
            damage = (int) (Math.random() * 0.5 * this.damage + 0.75 * this.damage);
        }
    }

    public void shootDown() {
        if (!hasBullet) {
            point = 2;
            bulletX = x + 0.5;
            bulletY = y - 1.0;
            dx = 0;
            dy = -BULLET_SPEED;
            hasBullet = true;
            damage = (int) (Math.random() * 0.5 * this.damage + 0.75 * this.damage);
        }
    }

    public void shootUp() {
        if (!hasBullet) {
            point = 2;
            bulletX = x + 0.5;
            bulletY = y + 1.0;
            dx = 0;
            dy = BULLET_SPEED;
            hasBullet = true;
            damage = (int) (Math.random() * 0.5 * this.damage + 0.75 * this.damage);
        }
    }


    public void drawBulletForDrone1(GraphicsContext gc, Drone drone1) {
        if (drone1.hasBullet()) {
            gc.setFill(Color.BLACK);
            int bulletPixelX1 = (int) (drone1.getBulletX() * DroneGame.DRONE_SIZE);
            int bulletPixelY1 = (int) ((DroneGame.FIELD_HEIGHT - 1 - drone1.getBulletY()) * DroneGame.DRONE_SIZE);

            if (DroneGame.getcodeprew1() == 'D') {
                // Снаряд першого дрона летить вправо
                gc.fillRect(bulletPixelX1, bulletPixelY1 + DroneGame.DRONE_SIZE, 4, 4);
            } else if (DroneGame.getcodeprew1() == 'A') {
                // Снаряд першого дрона летить вліво
                gc.fillRect(bulletPixelX1 + DroneGame.DRONE_SIZE, bulletPixelY1 + DroneGame.DRONE_SIZE, 4, 4);
            } else if (DroneGame.getcodeprew1() == 'S') {
                // Снаряд першого дрона летить вниз
                gc.fillRect(bulletPixelX1, bulletPixelY1, 4, 4);
            } else if (DroneGame.getcodeprew1() == 'W') {
                // Снаряд першого дрона летить вверх
                gc.fillRect(bulletPixelX1, bulletPixelY1 + DroneGame.DRONE_SIZE, 4, 4);
            }
        }
    }

    public void drawBulletForDrone2(GraphicsContext gc, Drone drone2) {
        if (drone2.hasBullet()) {
            gc.setFill(Color.BLACK);
            int bulletPixelX2 = (int) (drone2.getBulletX() * DroneGame.DRONE_SIZE);
            int bulletPixelY2 = (int) ((DroneGame.FIELD_HEIGHT - 1 - drone2.getBulletY()) * DroneGame.DRONE_SIZE);

            if (DroneGame.getcodeprew2() == 'J') {
                // Снаряд другого дрона летить вліво
                gc.fillRect(bulletPixelX2 + DroneGame.DRONE_SIZE, bulletPixelY2 + DroneGame.DRONE_SIZE, 4, 4);
            } else if (DroneGame.getcodeprew2() == 'L') {
                // Снаряд другого дрона летить вправо
                gc.fillRect(bulletPixelX2, bulletPixelY2 + DroneGame.DRONE_SIZE, 4, 4);
            } else if (DroneGame.getcodeprew2() == 'K') {
                // Снаряд другого дрона летить вниз
                gc.fillRect(bulletPixelX2, bulletPixelY2, 4, 4);
            } else if (DroneGame.getcodeprew2() == 'I') {
                // Снаряд другого дрона летить вверх
                gc.fillRect(bulletPixelX2, bulletPixelY2 + DroneGame.DRONE_SIZE, 4, 4);
            }
        }
    }


    private int steps = 0;

    public void updateBulletPosition(Drone otherDrone, List<Obstacle> obstacles) {
        if (hasBullet) {
            double distance = Math.sqrt(dx * dx + dy * dy);
            if (distance > 0) {
                dx = dx * BULLET_SPEED / distance;
                dy = dy * BULLET_SPEED / distance;
            }
            bulletX += dx;
            bulletY += dy;
            steps += 1;
            if (bulletX < 0 || bulletX >= FIELD_WIDTH || bulletY < 0 || bulletY >= FIELD_HEIGHT ||
                    hitDrone(otherDrone) || hitObstacle(obstacles)) {
                steps = 0;
                removeBullet();
            }
            if (steps == 30) {
                steps = 0;
                removeBullet();
            }
        }
    }

    private boolean hitDrone(Drone otherDrone) {
        if (this != otherDrone) {
            double distance = (Math.sqrt(Math.pow(bulletX - otherDrone.getX(), 2) + Math.pow(bulletY - otherDrone.getY(), 2))-2.0);
            if (distance <= 0.5) { //
                otherDrone.takeDamage(damage);
                return true;
            }
        }
        return false;
    }


    double distanceX;
    double distanceY;
    private boolean hitObstacle(List<Obstacle> obstacles) {
        for (Obstacle obstacle : obstacles) {
            if (obstacle.getType() == 'W' || obstacle.getType() == 'K'|| DroneGame.getcodeprew1() == 'D'||DroneGame.getcodeprew1() == 'A') {
                int bulletPixelX = (int) (getBulletX() * DroneGame.DRONE_SIZE);
                int bulletPixelY = (int) ((DroneGame.FIELD_HEIGHT - 1 - getBulletY()) * DroneGame.DRONE_SIZE);

                double obstaclePixelX = obstacle.getX() * DRONE_SIZE;
                double obstaclePixelY = (FIELD_HEIGHT - 1 - obstacle.getY()) * DRONE_SIZE;

                if (DroneGame.getcodeprew2() == 'L' || DroneGame.getcodeprew2() == 'J') {
                    if(Math.abs(bulletPixelY - (obstaclePixelY+15)) <= 4){
                        double distanceX = Math.abs(bulletPixelX - obstaclePixelX);
                        if (distanceX <= 6) {
                            return true;
                        }
                    }



                }
                if (DroneGame.getcodeprew2() == 'K' || DroneGame.getcodeprew2() == 'I'|| DroneGame.getcodeprew2() == 'W'|| DroneGame.getcodeprew2() == 'S') {
                    if(Math.abs(bulletPixelX - (obstaclePixelX+15)) <= 4) {
                        double distanceY = Math.abs(bulletPixelY - obstaclePixelY);
                        if (distanceY <= 6) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }


    public void removeBullet() {
        hasBullet = false;
    }

    public double getBulletX() {
        return bulletX;
    }

    public double getBulletY() {
        return bulletY;
    }

    public boolean hasBullet() {
        return hasBullet;
    }
}
