package com.testproject.asteroid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.testproject.asteroid.Classes.DirectionEvent;
import com.testproject.asteroid.Classes.MoveEvent;
import com.testproject.asteroid.Interfaces.DirectionEventListener;
import com.testproject.asteroid.Interfaces.MoveEventListener;

import java.util.ArrayList;
import java.util.List;

public class InputController {

    public InputController() {
    }

    private final List<MoveEventListener> moveListeners = new ArrayList<>();

    public void addLMoveistener(MoveEventListener listener) {

        moveListeners.add(listener);
    }

    public void removeMoveListener(MoveEventListener listener) {

        moveListeners.remove(listener);
    }
    private final List<DirectionEventListener> directionListeners = new ArrayList<>();

    public void addDirectionListener(DirectionEventListener listener) {
        directionListeners.add(listener);
    }

    public void removeDirectionListener(DirectionEventListener listener) {

        directionListeners.remove(listener);
    }

    public void update(float delta) {
        int x = 0;
        int y = 0;
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            x--;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            x++;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            y++;
        }
        if (x != 0 || y != 0) {
            Vector2 direction = new Vector2(x, y);
            for (MoveEventListener listener : moveListeners) {
                listener.positionChanged(new MoveEvent(this, direction, delta));
            }
        }

        if (Gdx.input.getDeltaX() != 0 || Gdx.input.getDeltaY() != 0) {
            Vector2 mousePosition = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            for (DirectionEventListener listener : directionListeners) {
                listener.directionChanged(new DirectionEvent(this, mousePosition) );
            }
        }
    }
}
