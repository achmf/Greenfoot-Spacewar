import greenfoot.*;

public class Spaceship extends Actor {
    private int SPEED = 3;
    private final int ROTATION_SPEED = 3;
    private int FIRE_RATE = 20; // Cooldown duration (in act cycles)
    private int fireCooldown = 0; // Cooldown timer
    private int speedUpgradeCost = 10; // Cost of speed upgrade in fragments
    private int firepowerUpgradeCost = 15; // Cost of firepower upgrade in fragments
    private int shieldUpgradeCost = 20; // Cost of shield upgrade in fragments

    public Spaceship() {
        GreenfootImage image = new GreenfootImage("spaceship.png"); // Replace "spaceship_image.png" with the actual file name of your image
        setImage(image);
    }

    public void act() {
        move();
        checkKeyPress();
        checkCollision();
        cooldown(); // Decrease cooldown timer
    }

    private void move() {
        if (Greenfoot.isKeyDown("up")) {
            move(SPEED);
        }
        if (Greenfoot.isKeyDown("left")) {
            turn(-ROTATION_SPEED);
        }
        if (Greenfoot.isKeyDown("right")) {
            turn(ROTATION_SPEED);
        }
    }

    private void checkKeyPress() {
        if (Greenfoot.isKeyDown("space") && fireCooldown == 0) {
            shoot();
            fireCooldown = FIRE_RATE; // Reset cooldown timer
        }
    }

    private void shoot() {
        Laser laser = new Laser(getRotation());
        getWorld().addObject(laser, getX(), getY());
        laser.move(10); // Move the laser a bit away from the spaceship
        
        // Laser Sound
        Greenfoot.playSound("laser_sound.mp3");
    }

    private void checkCollision() {
        Actor asteroid = getOneIntersectingObject(Asteroid.class);
        if (asteroid != null) {
            Greenfoot.playSound("crash.mp3");
            Greenfoot.setWorld(new GameOverScreen());
        }
        
        Actor alienSpaceship = getOneIntersectingObject(AlienSpaceship.class);
        if (alienSpaceship != null) {
            Greenfoot.playSound("crash.mp3");
            Greenfoot.setWorld(new GameOverScreen());
        }
        
        Actor alienProjectile = getOneIntersectingObject(AlienProjectile.class);
        if (alienProjectile != null) {
            Greenfoot.playSound("crash.mp3");
            Greenfoot.setWorld(new GameOverScreen());
        }
    }

    private void cooldown() {
        if (fireCooldown > 0) {
            fireCooldown--; // Decrease cooldown timer
        }
    }
}