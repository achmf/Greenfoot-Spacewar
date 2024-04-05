import greenfoot.*;  

public class NextLevel2Button extends Actor
{
    private GreenfootImage buttonImage = new GreenfootImage("next_level.png");
    int score, fragments;
    
    public NextLevel2Button() {
        setImage(buttonImage);
    }
    
    public void addedToWorld(World world) {
        // Calculate coordinates for top right corner
        int screenWidth = world.getWidth();
        int buttonWidth = getImage().getWidth();
        
        int topRightX = screenWidth - buttonWidth;
        
        // Place the button at the top right corner with some margin from the top
        setLocation(topRightX, 20); // You can adjust the '20' to add more space from the top if needed
    }
    
    public void act()
    {
        if (Greenfoot.mouseClicked(this)) {
            Greenfoot.setWorld(new Level2World(score, fragments));
        }
    }
}
