package com.testproject.asteroid.Classes;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.testproject.asteroid.Interfaces.EndGameEventListener;

import java.util.ArrayList;
import java.util.List;

public class CollisionContactListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        String classA = contact.getFixtureA().getBody().getUserData().getClass().getName();
        String classB = contact.getFixtureB().getBody().getUserData().getClass().getName();
        if (classA.contains("Spaceship") && classB.contains("Asteroid")){
            restartGame();
        }
        else if (classA.contains("Asteroid") && classB.contains("Spaceship")){
            restartGame();
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold manifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {

    }

    private final List<EndGameEventListener> listeners = new ArrayList<>();

    public void addListener(EndGameEventListener listener) {

        listeners.add(listener);
    }

    public void removeListener(EndGameEventListener listener) {

        listeners.remove(listener);
    }

    private void restartGame()
    {
        for (EndGameEventListener listener : listeners) {
            listener.onGameEnded(new EndGameEvent(this));
        }
    }
}
