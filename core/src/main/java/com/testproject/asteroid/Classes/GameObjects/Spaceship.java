package com.testproject.asteroid.Classes.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.testproject.asteroid.Classes.DirectionEvent;
import com.testproject.asteroid.Classes.MoveEvent;
import com.testproject.asteroid.Interfaces.DirectionEventListener;
import com.testproject.asteroid.Interfaces.MoveEventListener;

public class Spaceship implements MoveEventListener, DirectionEventListener {
    private float shipSpeed = 200.0f;
    private Texture image;
    TextureRegion region;
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
        SetShipPosition(Vector2.Zero);
        region = new TextureRegion(image, 0.0f, shipSize.y, shipSize.x, 0.0f);
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

    private void SetShipPosition(Vector2 newPosition) {
        shipPosition = Vector2.Zero;
        shipDrawPosition = new Vector2(shipPosition.x - (shipSize.x / 2.0f), shipPosition.y - (shipSize.y / 2.0f));
    }

    @Override
    public void positionChanged(MoveEvent event) {
        Vector2 deltaPosition = new Vector2(event.getDirection().x * shipSpeed * event.getDeltaTime(), event.getDirection().y * shipSpeed * event.getDeltaTime());
        SetShipPosition(shipPosition.add(deltaPosition));
    }

    @Override
    public void directionChanged(DirectionEvent event) {
        Vector2 direction = new Vector2(event.getMousePosition().x - ( shipPosition.x + screenWidth / 2 ), screenHeight / 2 - (event.getMousePosition().y + shipPosition.y));
        shipRotation = (float)Math.toDegrees(Math.atan2(direction.y, direction.x)) - 90.0f;
    }
}
