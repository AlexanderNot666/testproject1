package com.testproject.asteroid;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Texture image;
    private OrthographicCamera camera;

    public static final Vector2 screenSizeFullHD = new Vector2(1920, 1080);

    @Override
    public void create() {
        batch = new SpriteBatch();
        image = new Texture(Gdx.files.internal("purple.png"));
        image.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

        Gdx.graphics.setWindowedMode((int)screenSizeFullHD.x, (int)screenSizeFullHD.y);
        Gdx.graphics.setTitle("Asteroids");

        camera = new OrthographicCamera((float)Gdx.graphics.getWidth(), (float)Gdx.graphics.getHeight());
        updateCameraOptions(Vector3.Zero);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.0f, 0.0f, 0.0f, 1.0f);
        batch.begin();

        //render tiled background
        float screenWidth = (float)Gdx.graphics.getWidth();
        float screenHeight = (float)Gdx.graphics.getHeight();
        float tileMultiplierX = (screenWidth / image.getWidth());
        float tileMultiplierY = (screenHeight / image.getHeight());
        batch.draw(image, -screenWidth / 2.0f, -screenHeight / 2.0f, image.getWidth() * tileMultiplierX, image.getHeight() * tileMultiplierY, 0.0f, 0.0f, tileMultiplierX, tileMultiplierY);

        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, (float)Gdx.graphics.getWidth(), (float)Gdx.graphics.getHeight());
        updateCameraOptions(Vector3.Zero);
    }

    @Override
    public void dispose() {
        batch.dispose();
        image.dispose();
    }

    private void updateCameraOptions(Vector3 cameraPosition) {
        camera.position.set(cameraPosition);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
    }
}
