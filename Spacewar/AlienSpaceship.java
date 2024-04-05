import greenfoot.*;

public class AlienSpaceship extends Actor {
    private int SPEED = 2;
    private int shootDelay = Greenfoot.getRandomNumber(100) + 50; // Delay between shots (random)
    private int shootTimer = 0; // Timer to track when to shoot
    private boolean shouldRemove = false; // Flag to track whether the spaceship should be removed
    private int hitCount = 0; // Number of times the asteroid has been hit
    private Level2World world; // Reference to the world

    public AlienSpaceship(Level2World world) {
        this.world = world; // Assign the world instance
        GreenfootImage image = new GreenfootImage("alien_spaceship.png"); // Replace "alien_spaceship.png" with the actual file name of your image
        setImage(image);
    }

    public void act() {
        moveRandomly();
        checkEdge();
        shoot();
        checkCollision();
        if (shouldRemove) {
            getWorld().removeObject(this);
        }
    }
    
    private void moveRandomly() {
        if (!shouldRemove) {
            move(SPEED);
            if (Greenfoot.getRandomNumber(100) < 10) { // 10% chance to turn
                turn(Greenfoot.getRandomNumber(20) - 10); // Random turn angle between -10 and 10
            }
        }
    }

    private void checkEdge() {
        if (isAtEdge()) {
            // Change direction when touching the edge
            setRotation(getRotation() + 180); // Reverse direction
        }
    }

    private void shoot() {
        if (shootTimer == 0) {
            AlienProjectile projectile = new AlienProjectile();
            getWorld().addObject(projectile, getX(), getY());
            Greenfoot.playSound("alien_laser.mp3");
            shootTimer = shootDelay; // Reset shoot timer
        } else {
            shootTimer--;
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
                if (hitCount >= 5) { // If hit three times, remove the asteroid
                    getWorld().removeObject(this);
                    Greenfoot.playSound("asteroid_break.mp3");
                    world.increaseScore(1); // Increase score by 1
                    int fragmentIncrease = Greenfoot.getRandomNumber(3) + 1; // Randomly increase fragments by 1 to 3
                    world.increaseFragments(fragmentIncrease);
                }
            }
        }
    }
}
