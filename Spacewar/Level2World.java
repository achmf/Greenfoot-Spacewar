import greenfoot.*;

public class Level2World extends World {
    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    int fragments; // Currency of the game
    int score; // Score of the game
    private int aliensSpawned = 0; // Track the number of alien spaceships spawned

    public Level2World(int score, int fragments) {
        super(800, 600, 1);
        this.fragments = fragments;
        this.score = score; // Set the score
        prepare();
        setBackground("bg2.png"); // Set background for Level2World
        showFragments(); // Display fragments when the world is prepare
        showScore(); // Display score when the world is prepare
        showObjective(); // Display objective when the world is prepare
        spawnAlienSpaceships(); // Spawn alien spaceships
        int initialScore = 0;
        int initialFragments = 100; // Initial fragment amount for Level2World
        Greenfoot.setWorld(new Level2World(initialScore, initialFragments));
    }

    private void prepare() {
        Spaceship spaceship = new Spaceship();
        addObject(spaceship, getWidth() / 2, getHeight() / 2);
    }

    private void spawnAlienSpaceships() {
        while (aliensSpawned < 3) {
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
            addObject(new AlienSpaceship(this), x, y);
            aliensSpawned++;
        }
    }

    private void showObjective() {
        showText("Objective: Destroy asteroids and survive!", WIDTH / 2, HEIGHT / 2); // Display objective message
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
}
