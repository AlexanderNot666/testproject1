package com.testproject.asteroid;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.testproject.asteroid.Classes.GameObjects.Spaceship;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private static final Vector2 screenSizeFullHD = new Vector2(1920, 1080);
    private SpriteBatch batch;
    private Background background;
    private Camera camera;
    private InputController inputController;
    private Spaceship spaceship;

    @Override
    public void create() {
        Gdx.graphics.setWindowedMode((int)screenSizeFullHD.x, (int)screenSizeFullHD.y);
        Gdx.graphics.setTitle("Asteroids");

        batch = new SpriteBatch();
        inputController = new InputController();
        background = new Background();
        camera = new Camera(batch);
        spaceship = new Spaceship();
        inputController.addLMoveistener(spaceship);
        inputController.addDirectionListener(spaceship);
    }

    @Override
    public void render() {
        float delta = Gdx.graphics.getDeltaTime();

        ScreenUtils.clear(0.0f, 0.0f, 0.0f, 1.0f);
        batch.begin();

        inputController.update(delta);
        background.render(batch);
        spaceship.render(batch);


        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        camera.resize();
        spaceship.resize();
    }

    @Override
    public void dispose() {
        inputController.removeMoveListener(spaceship);
        inputController.removeDirectionListener(spaceship);
        background.dispose();
        batch.dispose();
    }

}
