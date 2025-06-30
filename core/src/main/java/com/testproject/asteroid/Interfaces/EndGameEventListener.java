package com.testproject.asteroid.Interfaces;

import com.testproject.asteroid.Classes.EndGameEvent;

public interface EndGameEventListener {
    void onGameEnded(EndGameEvent endGameEvent);
}
