package com.testproject.asteroid.Interfaces;

import com.badlogic.gdx.math.Vector2;
import com.testproject.asteroid.Classes.DirectionEvent;

public interface IMovableObject {
    void move(Vector2 direction, float speed, float deltaTime);

    public void updateVision();

    public void rotate (Vector2 direction);

    public boolean isObjectVisible(Vector2 position);
}
