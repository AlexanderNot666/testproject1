package com.testproject.asteroid.Classes;

import com.badlogic.gdx.math.Vector2;

import java.util.EventObject;

public class DirectionEvent extends EventObject {

    private final Vector2 mousePosition;

    public DirectionEvent(Object source, Vector2 mousePosition) {
        super(source);
        this.mousePosition = mousePosition;
    }

    public Vector2 getMousePosition() {
        return mousePosition;
    }
}
