package com.testproject.asteroid;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class Camera {

    private final OrthographicCamera camera;
    private SpriteBatch batch;

    public Camera(SpriteBatch batch) {
        this.batch = batch;
        camera = new OrthographicCamera((float)Gdx.graphics.getWidth(), (float)Gdx.graphics.getHeight());
        updateCameraOptions(Vector3.Zero);
    }

    private void updateCameraOptions(Vector3 cameraPosition) {
        camera.position.set(cameraPosition);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
    }

    public void resize() {
        camera.setToOrtho(false, (float)Gdx.graphics.getWidth(), (float)Gdx.graphics.getHeight());
        updateCameraOptions(Vector3.Zero);
    }
}
