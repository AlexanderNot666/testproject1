package com.testproject.asteroid.Interfaces;

import com.badlogic.gdx.math.Vector2;

public interface IMovableObject {
    void move(Vector2 direction, float speed, float deltaTime);

    void rotate (Vector2 direction, float angle);
}
