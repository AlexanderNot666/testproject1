package com.testproject.asteroid;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.testproject.asteroid.Classes.GameObjects.Asteroid;
import com.testproject.asteroid.Classes.GameObjects.AsteroidFactory;
import com.testproject.asteroid.Classes.GameObjects.AsteroidType;
import com.testproject.asteroid.Classes.GameObjects.Spaceship;

import java.util.ArrayList;
import java.util.List;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private final Vector2 screenSizeFullHD = new Vector2(1920, 1080);
    private int asteroidsCount = 12;
    private SpriteBatch batch;
    private Background background;
    private Camera camera;
    private InputController inputController;
    private Spaceship spaceship;
    private final List<Asteroid> asteroids = new ArrayList<>();

    @Override
    public void create() {
        Gdx.graphics.setWindowedMode((int)screenSizeFullHD.x, (int)screenSizeFullHD.y);
        Gdx.graphics.setTitle("Space");

        batch = new SpriteBatch();
        inputController = new InputController();
        background = new Background();
        camera = new Camera(batch);
        spaceship = new Spaceship();
        inputController.addLMoveistener(spaceship);
        inputController.addDirectionListener(spaceship);
        generateAsteroids(spaceship.shipSize.x / 2);
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
    }

    private void generateAsteroids(float centerSafeArea) {
        for (int i = 0; i < asteroidsCount - 1; i++) {
            int asteroidType = MathUtils.random(0, AsteroidType.values().length - 1);
            Asteroid asteroid = AsteroidFactory.getAsteroid(AsteroidType.values()[asteroidType]);
            assert asteroid != null;
            asteroid.initialize(centerSafeArea);
            asteroids.add(asteroid);
        }
    }

    private void renderAsteroids(float deltaTime) {
        for (int i = 0; i < asteroids.size(); i++) {
            asteroids.get(i).render(batch, deltaTime);
        }
    }

    private void resizeAsteroids() {
        for (int i = 0; i < asteroids.size(); i++) {
            asteroids.get(i).resize();
        }
    }
}
