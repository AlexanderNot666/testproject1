package com.testproject.asteroid.Classes;

import com.badlogic.gdx.math.Vector2;

import java.util.EventObject;

public class MoveEvent extends EventObject {

    private final Vector2 direction;
    private final float deltaTime;

    public MoveEvent(Object source, Vector2 direction, float deltaTime) {
        super(source);
        this.direction = direction;
        this.deltaTime = deltaTime;
    }

    public Vector2 getDirection() {
        return direction;
    }

    public float getDeltaTime() { return deltaTime; }
}


