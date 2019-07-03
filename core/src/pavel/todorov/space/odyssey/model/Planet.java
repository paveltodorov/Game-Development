package pavel.todorov.space.odyssey.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import pavel.todorov.space.odyssey.MyGame;

import java.util.Random;

public class Planet extends Image {

    private MyGame myGame;
    private World physicsWorld;
    private Body body;
    private boolean transition;

    public Planet(MyGame myGame, World physicsWorld, Texture appearance, float x, float y,
                  float width, float height) {
        super(appearance);
        this.setX(x);
        this.setY(y);
        this.setOrigin(x, y);
        this.setWidth(width);
        this.setHeight(height);
        this.myGame = myGame;
        this.physicsWorld = physicsWorld;
        this.transition = false;

        Color color = new Color();
        Random random = new Random();

        float r = random.nextFloat();
        float g = random.nextFloat();
        float b = random.nextFloat();
        color.set(r, g, b, 1);

        this.setColor(Color.WHITE.cpy().lerp(color, 0.99f));
    }

    private void initBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(getX(), getY());
        bodyDef.type = BodyDef.BodyType.StaticBody;

        body = physicsWorld.createBody(bodyDef);

        CircleShape bodyShape = new CircleShape();
        bodyShape.setRadius(8f);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = bodyShape;
        fixtureDef.density = 0.0f;
        fixtureDef.friction = 0.0f;
        fixtureDef.restitution = 0.0f;

        body.createFixture(fixtureDef);
        body.setUserData(this);
        body.setLinearVelocity(5f, 0);

        bodyShape.dispose();
    }
}

