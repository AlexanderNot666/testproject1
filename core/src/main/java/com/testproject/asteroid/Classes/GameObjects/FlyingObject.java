package com.testproject.asteroid.Classes.GameObjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.testproject.asteroid.Utils;

public class FlyingObject {
    protected float speed;

    protected Texture image;

    protected Vector2 objectPosition;

    protected Vector2 drawPosition;

    protected Vector2 objectSize;

    protected Sprite sprite;

    protected float screenWidth;

    protected float screenHeight;

    protected float objectRotation;

    public void render(SpriteBatch batch) {
        sprite.setPosition(drawPosition.x, drawPosition.y);
        sprite.setRotation(objectRotation);
        sprite.draw(batch);
    }

    public void resize() {
        screenWidth = (float) Gdx.graphics.getWidth();
        screenHeight = (float)Gdx.graphics.getHeight();
    }

    protected void updateVision() {
        drawPosition = new Vector2(objectPosition.x - (objectSize.x / 2.0f), objectPosition.y - (objectSize.y / 2.0f));
    }

    protected boolean isObjectVisible(Vector2 position){
        return Utils.CheckIsObjectOutOfScreen(position, objectSize, new Vector2(screenWidth, screenHeight));
    }

    public void dispose()
    {
        this.dispose();
    }
}
