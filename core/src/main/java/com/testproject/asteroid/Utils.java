package com.testproject.asteroid;

import com.badlogic.gdx.math.Vector2;

public final class Utils {

    private Utils() {
    }

    public static boolean CheckIsObjectOutOfScreen(Vector2 objectPosition, Vector2 objectSize, Vector2 screenSize)
    {
        boolean result = false;
        float borderX = (screenSize.x + objectSize.x) / 2.0f;
        float borderY = (screenSize.y + objectSize.y) / 2.0f;

        result &= objectPosition.x < -borderX;
        result &= objectPosition.x > borderX;
        result &= objectPosition.y < -borderY;
        result &= objectPosition.y > borderY;

        return result;
    }

    public static Vector2 GetObjectReloadPosition(Vector2 objectPosition, Vector2 objectSize, Vector2 screenSize)
    {
        float newPositionX = objectPosition.x;
        float newPositionY = objectPosition.y;
        float borderX = (screenSize.x + objectSize.x) / 2.0f;
        float borderY = (screenSize.y + objectSize.y) / 2.0f;

        if (objectPosition.x < -borderX){
            newPositionX = borderX;
        }
        if (objectPosition.x > borderX){
            newPositionX = -borderX;
        }
        if (objectPosition.y < -borderY){
            newPositionY = borderY;
        }
        if (objectPosition.y > borderY){
            newPositionY = -borderY;
        }

        return new Vector2(newPositionX, newPositionY);
    }
}
