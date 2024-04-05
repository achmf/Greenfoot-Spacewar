import greenfoot.*;

public class Asteroid extends Actor {
    private int SPEED = 1;
    private int hitCount = 0; // Number of times the asteroid has been hit
    private MainWorld world; // Reference to the world

    public Asteroid(MainWorld world) {
        this.world = world;
        setRotation(Greenfoot.getRandomNumber(360));
        // Load the image for the asteroid
        GreenfootImage image = new GreenfootImage("asteroid.png"); // Replace "asteroid_image.png" with the actual file name of your image
        setImage(image);
    }

    public void act() {
        move(SPEED);
        checkEdge();
        checkCollision(); // Check for collisions with lasers
    }

    private void checkEdge() {
        if (isAtEdge()) {
            getWorld().removeObject(this); // Remove asteroid when it reaches the edge
        }
    }

    private void checkCollision() {
        // Check if the asteroid is still in the world
        if (getWorld() != null) {
            // Check for collisions with lasers
            Actor laser = getOneIntersectingObject(Laser.class);
            if (laser != null) {
                getWorld().removeObject(laser); // Remove the laser
                Greenfoot.playSound("laser_hit.mp3");
                hitCount++; // Increment hit count
                if (hitCount >= 3) { // If hit three times, remove the asteroid
                    getWorld().removeObject(this);
                    Greenfoot.playSound("asteroid_break.mp3");
                    world.increaseScore(1); // Increase score by 1
                    int fragmentIncrease = Greenfoot.getRandomNumber(3) + 1; // Randomly increase fragments by 1 to 3
                    world.increaseFragments(fragmentIncrease);
                }
            }
        }
    }

    public void setSpeed(int speed) {
        this.SPEED = speed;
    }

    public int getSpeed() {
        return SPEED;
    }
}
