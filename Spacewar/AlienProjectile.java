import greenfoot.*;

public class AlienProjectile extends Actor {
    private int speed = 5;

    public AlienProjectile() {
        GreenfootImage image = new GreenfootImage(10, 3); // Create an image for the projectile
        image.setColor(Color.RED);
        image.fill(); // Fill the image with red color
        setImage(image);

        setRotation(Greenfoot.getRandomNumber(360)); // Set a random initial rotation angle
    }

    public void act() {
        move(speed); // Move the projectile
        checkEdge(); // Check if the projectile is at the edge of the world
    }

    private void checkEdge() {
        if (isAtEdge()) {
            getWorld().removeObject(this); // Remove the projectile if it reaches the edge of the world
        }
    }
}
