package com.testproject.asteroid.Classes.GameObjects;

import com.badlogic.gdx.physics.box2d.*;

public class PhysicalObject extends FlyingObject {
    protected Body body;
    private World world;

    PolygonShape spriteBox;

    protected void buildPhysicalObject(World world) {

        this.world = world;
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(objectPosition.x,objectPosition.y);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(bodyDef);

        spriteBox = new PolygonShape();
        spriteBox.setAsBox(objectSize.x / 2.0f,objectSize.y / 2.0f);
        body.createFixture(spriteBox, 0.0f);
        body.setUserData(this);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 10;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.3f;
        fixtureDef.shape = spriteBox;

        Fixture fixture = body.createFixture(fixtureDef);
    }

    public void dispose()
    {
        spriteBox.dispose();
        world.destroyBody(body);
    }
}
