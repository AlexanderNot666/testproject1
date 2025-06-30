package com.testproject.asteroid.Classes.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.testproject.asteroid.Classes.DirectionEvent;
import com.testproject.asteroid.Classes.MoveEvent;
import com.testproject.asteroid.Interfaces.DirectionEventListener;
import com.testproject.asteroid.Interfaces.IMovableObject;
import com.testproject.asteroid.Interfaces.MoveEventListener;
import com.testproject.asteroid.Utils;

public class Spaceship extends PhysicalObject implements MoveEventListener, DirectionEventListener, IMovableObject {

    public Spaceship(World world) {
        screenWidth = (float)Gdx.graphics.getWidth();
        screenHeight = (float)Gdx.graphics.getHeight();
        image = new Texture(Gdx.files.internal("playerShip1_blue.png"));
        objectSize = new Vector2(image.getWidth(), image.getHeight());
        objectPosition = Vector2.Zero;
        updateVision();
        sprite = new Sprite(image, 0, 0, (int) objectSize.x, (int) objectSize.y);
        speed = 300.0f;
        super.buildPhysicalObject(world);
    }

    public void initialize() {
        objectPosition = Vector2.Zero;
        objectRotation = 0.0f;
    }

    @Override
    public void positionChanged(MoveEvent event) {
        move(event.getDirection(), speed, event.getDeltaTime());
    }

    @Override
    public void directionChanged(DirectionEvent event) {
        Vector2 direction = new Vector2(event.getMousePosition().x - ( objectPosition.x + screenWidth / 2 ), screenHeight / 2 - (event.getMousePosition().y + objectPosition.y));
        rotate(direction, 0.0f);
    }

    @Override
    public void move(Vector2 direction, float speed, float deltaTime)
    {
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
        body.setTransform(objectPosition, (float)Math.toRadians(objectRotation));
    }

    @Override
    public void rotate(Vector2 direction, float angle) {
        objectRotation = (float)Math.toDegrees(Math.atan2(direction.y, direction.x)) - 90.0f;
        body.setTransform(objectPosition, (float)Math.toRadians(objectRotation));
    }

    public float getShipSize() { return objectSize.x; }
}
