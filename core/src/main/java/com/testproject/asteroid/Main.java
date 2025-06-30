package com.testproject.asteroid;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.ScreenUtils;
import com.testproject.asteroid.Classes.CollisionContactListener;
import com.testproject.asteroid.Classes.EndGameEvent;
import com.testproject.asteroid.Classes.GameObjects.Asteroid;
import com.testproject.asteroid.Classes.GameObjects.AsteroidFactory;
import com.testproject.asteroid.Classes.GameObjects.AsteroidType;
import com.testproject.asteroid.Classes.GameObjects.Spaceship;
import com.testproject.asteroid.Interfaces.EndGameEventListener;

import java.util.ArrayList;
import java.util.List;

public class Main extends ApplicationAdapter implements EndGameEventListener {
    private final Vector2 screenSizeFullHD = new Vector2(1920, 1080);
    private int asteroidsCount = 15;
    private SpriteBatch batch;
    private Background background;
    private Camera camera;
    private InputController inputController;
    private Spaceship spaceship;
    private final List<Asteroid> asteroids = new ArrayList<>();
    private float centerSafeArea;
    private World world;
    private Box2DDebugRenderer debugRenderer;
    private CollisionContactListener collisionContactListener;
    private final boolean isDebugRendererEnabled = false;

    @Override
    public void create() {
        Gdx.graphics.setWindowedMode((int)screenSizeFullHD.x, (int)screenSizeFullHD.y);
        Gdx.graphics.setTitle("Space");

        world = new World(new Vector2(0, 0), true);
        collisionContactListener = new CollisionContactListener();
        world.setContactListener(collisionContactListener);
        collisionContactListener.addListener(this);
        if (isDebugRendererEnabled){
            debugRenderer = new Box2DDebugRenderer();
        }

        batch = new SpriteBatch();
        inputController = new InputController();
        background = new Background();
        camera = new Camera(batch);
        spaceship = new Spaceship(world);
        inputController.addLMoveistener(spaceship);
        inputController.addDirectionListener(spaceship);
        centerSafeArea = spaceship.getShipSize() / 2;
        generateAsteroids();
    }

    @Override
    public void render() {
        float delta = Gdx.graphics.getDeltaTime();

        ScreenUtils.clear(0.0f, 0.0f, 0.0f, 1.0f);
        batch.begin();

        inputController.update(delta);
        background.render(batch);
        spaceship.render(batch);
        renderAsteroids(delta);
        if (isDebugRendererEnabled){
            debugRenderer.render(world, camera.getCamera().combined);
        }
        world.step(Gdx.graphics.getDeltaTime(), 6, 2);

        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        camera.resize();
        spaceship.resize();
        resizeAsteroids();
    }

    @Override
    public void dispose() {
        inputController.removeMoveListener(spaceship);
        inputController.removeDirectionListener(spaceship);
        background.dispose();
        batch.dispose();
        world.dispose();
        if (debugRenderer != null) {
            debugRenderer.dispose();
        }

        collisionContactListener.removeListener(this);
    }

    private void generateAsteroids() {
        for (int i = 0; i < asteroidsCount; i++) {
            int asteroidType = MathUtils.random(0, AsteroidType.values().length - 1);
            Asteroid asteroid = AsteroidFactory.getAsteroid(AsteroidType.values()[asteroidType]);
            assert asteroid != null;
            asteroid.initialize(centerSafeArea, world, true);
            asteroids.add(asteroid);
        }
    }

    private void renderAsteroids(float deltaTime) {
        for (Asteroid asteroid : asteroids) {
            asteroid.render(batch, deltaTime);
        }
    }

    private void resizeAsteroids() {
        for (Asteroid asteroid : asteroids) {
            asteroid.resize();
        }
    }

    @Override
    public void onGameEnded(EndGameEvent endGameEvent) {
        for (Asteroid asteroid : asteroids) {
            asteroid.initialize(centerSafeArea, world, false);
        }
        spaceship.initialize();
    }
}
