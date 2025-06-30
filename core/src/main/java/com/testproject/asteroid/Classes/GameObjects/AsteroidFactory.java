package com.testproject.asteroid.Classes.GameObjects;

public class AsteroidFactory {
    public static Asteroid getAsteroid(AsteroidType type) {
        return switch (type) {
            case BIGBROWN -> new BigBrownAsteroid();
            case BIGGREY -> new BigGreyAsteroid();
            case MEDIUMBROWN ->  new MediumBrownAsteroid();
            case MEDIUMGREY ->  new MediumGreyAsteroid();
            case SMALLBROWN -> new SmallBrownAsteroid();
            case SMALLGREY -> new SmallGreyAsteroid();
            default -> null;
        };
    }
}
