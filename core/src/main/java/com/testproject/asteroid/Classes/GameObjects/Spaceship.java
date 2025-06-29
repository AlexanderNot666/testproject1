package com.testproject.asteroid.Classes.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.testproject.asteroid.Classes.DirectionEvent;
import com.testproject.asteroid.Classes.MoveEvent;
import com.testproject.asteroid.Interfaces.DirectionEventListener;
import com.testproject.asteroid.Interfaces.IMovableObject;
import com.testproject.asteroid.Interfaces.MoveEventListener;
import com.testproject.asteroid.Utils;

public class Spaceship implements MoveEventListener, DirectionEventListener, IMovableObject {
    private float shipSpeed = 300.0f;
    private Texture image;
    private Vector2 shipPosition;
    private Vector2 shipDrawPosition;
    private Vector2 shipSize;
    private Sprite sprite;

    private float screenWidth;
    float screenHeight;
    private float shipRotation = 0.0f;

    public Spaceship() {
        screenWidth = (float)Gdx.graphics.getWidth();
        screenHeight = (float)Gdx.graphics.getHeight();
        image = new Texture(Gdx.files.internal("playerShip1_blue.png"));
        shipSize = new Vector2(image.getWidth(), image.getHeight());
        shipPosition = Vector2.Zero;
        updateVision();
        sprite = new Sprite(image, 0, 0, (int) shipSize.x, (int) shipSize.y);
    }

    public void render(SpriteBatch batch) {
        sprite.setPosition(shipDrawPosition.x, shipDrawPosition.y);
        sprite.setRotation(shipRotation);
        sprite.draw(batch);
    }

    public void resize() {
        screenWidth = (float)Gdx.graphics.getWidth();
        screenHeight = (float)Gdx.graphics.getHeight();
    }

    @Override
    public void positionChanged(MoveEvent event) {
        move(event.getDirection(), shipSpeed, event.getDeltaTime());
    }

    @Override
    public void directionChanged(DirectionEvent event) {
        Vector2 direction = new Vector2(event.getMousePosition().x - ( shipPosition.x + screenWidth / 2 ), screenHeight / 2 - (event.getMousePosition().y + shipPosition.y));
        rotate(direction);
    }

    @Override
    public void move(Vector2 direction, float speed, float deltaTime)
    {
        Vector2 deltaPosition = new Vector2(direction.x * shipSpeed * deltaTime, direction.y * shipSpeed * deltaTime);
        shipPosition.add(deltaPosition);
        if (isObjectVisible(shipPosition))
        {
            updateVision();
        }
        else
        {
            Vector2 newPosition = Utils.GetObjectReloadPosition(shipPosition, shipSize, new Vector2(screenWidth, screenHeight));
            shipPosition = newPosition;
            updateVision();
        }
    }

    @Override
    public void updateVision() {
        shipDrawPosition = new Vector2(shipPosition.x - (shipSize.x / 2.0f), shipPosition.y - (shipSize.y / 2.0f));
    }

    @Override
    public void rotate(Vector2 direction) {
        shipRotation = (float)Math.toDegrees(Math.atan2(direction.y, direction.x)) - 90.0f;
    }

    @Override
    public boolean isObjectVisible(Vector2 position){
        return Utils.CheckIsObjectOutOfScreen(position, shipSize, new Vector2(screenWidth, screenHeight));
    }
}
