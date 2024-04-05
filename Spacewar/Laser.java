import greenfoot.*;

public class Laser extends Actor {
    private final int SPEED = 10;

    public Laser(int rotation) {
        setRotation(rotation);
        GreenfootImage image = new GreenfootImage(30, 3); // Create a larger image for the laser
        setImage(image);
        
        // Draw a longer and slightly bigger red line representing the laser
        image.setColor(Color.RED);
        image.drawLine(0, 1, 29, 1); // Draw a line representing the laser with a width of 3 pixels
    }

    public void act() {
        move(SPEED);
        checkEdge();
    }

    private void checkEdge() {
        if (isAtEdge()) {
            getWorld().removeObject(this); // Remove laser when it reaches the edge
        }
    }
}