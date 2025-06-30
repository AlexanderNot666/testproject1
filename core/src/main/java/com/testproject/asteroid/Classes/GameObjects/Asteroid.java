package com.testproject.asteroid.Classes.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.testproject.asteroid.Interfaces.IMovableObject;
import com.testproject.asteroid.Utils;

public class Asteroid extends PhysicalObject implements IMovableObject {
    private float asteroidRotationShift;
    private Vector2 asteroidDirection;

    public Asteroid(String imageName) {
        screenWidth = (float) Gdx.graphics.getWidth();
        screenHeight = (float)Gdx.graphics.getHeight();
        image = new Texture(Gdx.files.internal(imageName));
        objectSize = new Vector2(image.getWidth(), image.getHeight());
    }

    public void initialize(float centerSafeArea, World world, boolean isNeedReloadBody)
    {
        speed = MathUtils.random(250.0f, 500.f);
        objectPosition = getRandomPosition(centerSafeArea);
        objectRotation = 0.0f;
        asteroidRotationShift = MathUtils.random(-5.0f, 5.f);
        asteroidDirection = getRandomDirection();
        updateVision();
        sprite = new Sprite(image, 0, 0, (int) objectSize.x, (int) objectSize.y);
        if (isNeedReloadBody){
            super.buildPhysicalObject(world);
        }
    }

    public void render(SpriteBatch batch, float deltaTime) {
        move(asteroidDirection, speed, deltaTime);
        super.render(batch);
    }

    @Override
    public void move(Vector2 direction, float speed, float deltaTime) {
        Vector2 deltaPosition = new Vector2(direction.x * speed * deltaTime, direction.y * speed * deltaTime);
        objectPosition.add(deltaPosition);
        if (isObjectVisible(objectPosition))
        {
            updateVision();
        }
        else
        {
            objectPosition = Utils.GetObjectReloadPosition(objectPosition, objectSize, new Vector2(screenWidth, screenHeight));
            updateVision();
        }
        rotate(null, asteroidRotationShift);
        body.setTransform(objectPosition, (float)Math.toRadians(objectRotation));
    }

    @Override
    public void rotate(Vector2 direction, float angle) {
        objectRotation += angle;
        body.setTransform(objectPosition, (float)Math.toRadians(objectRotation));
    }

    private Vector2 getRandomPosition(float centerSafeArea){
        float x = MathUtils.random(centerSafeArea + objectSize.x / 2, screenWidth - objectSize.x / 2);
        float y = MathUtils.random(centerSafeArea + objectSize.y / 2, screenHeight - objectSize.y / 2);

        return new Vector2(x * randomFlip(), y * randomFlip());
    }

    private Vector2 getRandomDirection(){
        float x = MathUtils.random(0.0f, 1.0f);
        float y = MathUtils.random(0.0f, 1.0f);

        return new Vector2(x * randomFlip(), y * randomFlip());
    }

    private int randomFlip(){ return (MathUtils.random(1, 100) % 2 == 0) ? (1) : (-1); }
}
