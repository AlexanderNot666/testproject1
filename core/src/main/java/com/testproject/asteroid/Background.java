package com.testproject.asteroid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Background {
    private Texture image;

    public Background() {
        image = new Texture(Gdx.files.internal("purple.png"));
        image.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
    }

    public void render(SpriteBatch batch)
    {
        float screenWidth = (float)Gdx.graphics.getWidth();
        float screenHeight = (float)Gdx.graphics.getHeight();
        float tileMultiplierX = (screenWidth / image.getWidth());
        float tileMultiplierY = (screenHeight / image.getHeight());
        batch.draw(image, -screenWidth / 2.0f, -screenHeight / 2.0f, image.getWidth() * tileMultiplierX, image.getHeight() * tileMultiplierY, 0.0f, 0.0f, tileMultiplierX, tileMultiplierY);
    }

    public void dispose() {
        image.dispose();
    }
}
