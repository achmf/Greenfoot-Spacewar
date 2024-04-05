import greenfoot.*;

public class MainWorld extends World {
    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private final int INITIAL_ASTEROIDS = 5; // Initial number of asteroids
    private final int ASTEROID_DELAY = 100; // Delay between asteroid spawns (in act cycles)
    private int asteroidTimer = 0; // Timer to track when to spawn new asteroids
    int fragments = 0; // Currency of the game
    int score = 0; // Score variable

    public MainWorld() {
        super(800, 600, 1);
        prepare();
        setBackground("bg1.png");
        playBackgroundMusic();
        showFragments(); // Display fragments when the world is prepare
        showScore(); // Display score when the world is prepare
        showObjective(); // Display objective.
    }

    private void prepare() {
        Spaceship spaceship = new Spaceship();
        addObject(spaceship, getWidth() / 2, getHeight() / 2);
    }

    public void act() {
        spawnAsteroids();
        checkLevelChange();
        removeOutOfBoundsAsteroids(); // Check and remove out of bounds asteroids
    }

    private void spawnAsteroids() {
        if (asteroidTimer == 0) {
            for (int i = 0; i < INITIAL_ASTEROIDS; i++) {
                Asteroid asteroid = new Asteroid(this); // Pass MainWorld instance to Asteroid constructor
                int side = Greenfoot.getRandomNumber(4); // Randomly choose one of the four sides
                int x = 0, y = 0;
                switch (side) {
                    case 0: // Top edge
                        x = Greenfoot.getRandomNumber(WIDTH);
                        y = 0;
                        break;
                    case 1: // Right edge
                        x = WIDTH;
                        y = Greenfoot.getRandomNumber(HEIGHT);
                        break;
                    case 2: // Bottom edge
                        x = Greenfoot.getRandomNumber(WIDTH);
                        y = HEIGHT;
                        break;
                    case 3: // Left edge
                        x = 0;
                        y = Greenfoot.getRandomNumber(HEIGHT);
                        break;
                }
                addObject(asteroid, x, y);
            }
        }
        asteroidTimer = (asteroidTimer + 1) % ASTEROID_DELAY;
    }

    private void playBackgroundMusic() {
        GreenfootSound backgroundMusic = new GreenfootSound("bg_music.mp3");
        backgroundMusic.setVolume(30);
        backgroundMusic.play();
    }

    public void increaseFragments(int amount) {
        fragments += amount; // Increase fragments
        showFragments(); // Update fragments display
    }

    private void showFragments() {
        showText("Fragments: " + fragments, 100, 50); // Display fragments on the screen
    }

    public void increaseScore(int points) {
        score += points; // Increase score
        showScore(); // Update score display
    }

    private void showScore() {
        showText("Score: " + score, 100, 30); // Display score on the screen
    }
    
    private void showObjective() {
        showText("Objective: Destroy 15 or mor asteroids to get fragments and survive!", WIDTH / 2, HEIGHT - 20); // Display objective message at the center bottom
    }
    
    private void checkLevelChange() {
        if (score >= 2) {
            addObject(new NextLevel2Button(), getWidth() / 2, getHeight() / 2); // Add NextLevel2Button at the center
        }
    }
    
    private void removeOutOfBoundsAsteroids() {
        for (Object obj : getObjects(Asteroid.class)) {
            Asteroid asteroid = (Asteroid) obj;
            int x = asteroid.getX();
            int y = asteroid.getY();
            if (x < 0 || x >= getWidth() || y < 0 || y >= getHeight()) {
                removeObject(asteroid); // Remove the asteroid if it's out of bounds
            }
        }
    }
}
