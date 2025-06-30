package com.testproject.asteroid.Classes.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.testproject.asteroid.Interfaces.IMovableObject;
import com.testproject.asteroid.Utils;

import java.util.Random;

public class Asteroid implements IMovableObject {
    private float asteroidSpeed;
    private Texture image;
    private Vector2 asteroidPosition;
    private Vector2 asteroiDDrawPosition;
    private Vector2 asteroidSize;
    private Sprite sprite;

    private float screenWidth;
    private float screenHeight;
    private float asteroidRotation;
    private float asteroidRotationShift;
    private Vector2 asteroidDirection;

    public Asteroid(String imageName) {
        screenWidth = (float) Gdx.graphics.getWidth();
        screenHeight = (float)Gdx.graphics.getHeight();
        image = new Texture(Gdx.files.internal(imageName));
        asteroidSize = new Vector2(image.getWidth(), image.getHeight());
    }

    public void initialize(float centerSafeArea)
    {
        asteroidSpeed = MathUtils.random(200.0f, 400.f);
        asteroidPosition = getRandomPosition(centerSafeArea);
        asteroidRotation = 0.0f;
        asteroidRotationShift = MathUtils.random(-1.0f, 1.f);
        asteroidDirection = getRandomDirection();
        updateVision();
        sprite = new Sprite(image, 0, 0, (int) asteroidSize.x, (int) asteroidSize.y);
    }

    public void render(SpriteBatch batch, float deltaTime) {
        move(asteroidDirection, asteroidSpeed, deltaTime);
        sprite.setPosition(asteroiDDrawPosition.x, asteroiDDrawPosition.y);
        sprite.setRotation(asteroidRotation);
        sprite.draw(batch);
    }

    public void resize() {
        screenWidth = (float)Gdx.graphics.getWidth();
        screenHeight = (float)Gdx.graphics.getHeight();
    }
    @Override
    public void move(Vector2 direction, float speed, float deltaTime) {
        Vector2 deltaPosition = new Vector2(direction.x * speed * deltaTime, direction.y * speed * deltaTime);
        asteroidPosition.add(deltaPosition);
        if (isObjectVisible(asteroidPosition))
        {
            updateVision();
        }
        else
        {
            Vector2 newPosition = Utils.GetObjectReloadPosition(asteroidPosition, asteroidSize, new Vector2(screenWidth, screenHeight));
            asteroidPosition = newPosition;
            updateVision();
        }
        rotate(null, asteroidRotationShift);
    }

    @Override
    public void updateVision() {
        asteroiDDrawPosition = new Vector2(asteroidPosition.x - (asteroidSize.x / 2.0f), asteroidPosition.y - (asteroidSize.y / 2.0f));
    }

    @Override
    public void rotate(Vector2 direction, float angle) {
        asteroidRotation += angle;
    }

    @Override
    public boolean isObjectVisible(Vector2 position) {
        return Utils.CheckIsObjectOutOfScreen(position, asteroidSize, new Vector2(screenWidth, screenHeight));
    }

    private Vector2 getRandomPosition(float centerSafeArea){
        float x = MathUtils.random(centerSafeArea + asteroidSize.x / 2, screenWidth - asteroidSize.x / 2);
        float y = MathUtils.random(centerSafeArea + asteroidSize.y / 2, screenHeight - asteroidSize.y / 2);

        return new Vector2(x * randomFlip(), y * randomFlip());
    }

    private Vector2 getRandomDirection(){
        float x = MathUtils.random(0.0f, 1.0f);
        float y = MathUtils.random(0.0f, 1.0f);

        return new Vector2(x * randomFlip(), y * randomFlip());
    }

    private int randomFlip(){ return (MathUtils.random(1, 100) % 2 == 0) ? (1) : (-1); }
}
