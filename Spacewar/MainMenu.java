import greenfoot.*;

public class MainMenu extends World {
    
    public MainMenu() {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(800, 600, 1); 
        setBackground("main_menu_bg.png");
        prepare();
    }
    
    private void prepare() {
        // Add Click To Start text at the center and slightly to the bottom
        addObject(new StartButton(), getWidth() / 2, getHeight() * 3 / 4);
    }
}