import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class StartButton here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StartButton extends Actor
{
    /**
     * Act - do whatever the StartButton wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public StartButton() {
        // Set the image for the ClickToStart text
        GreenfootImage textImage = new GreenfootImage("Click To Start", 40, Color.WHITE, new Color(0, 0, 0, 0));
        setImage(textImage);
    }    
    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            Greenfoot.setWorld(new MainWorld());
        }
    }
}
