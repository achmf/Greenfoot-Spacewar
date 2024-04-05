import greenfoot.*;

public class RestartButton extends Actor {
     private static final int FONT_SIZE = 24;
    public RestartButton() {
        GreenfootImage textImage = new GreenfootImage("RELAUNCH", FONT_SIZE, Color.WHITE, new Color(0, 0, 0, 0));
        setImage(textImage);
    }

    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            Greenfoot.setWorld(new MainMenu());
        }
    }
}
