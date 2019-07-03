package pavel.todorov.space.odyssey.model;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import pavel.todorov.space.odyssey.MyGame;

import java.util.Random;

public class WallSegment extends Image {

    private MyGame myGame;
    private World physicsWorld;
    private Body body;

    public WallSegment(MyGame myGame, World physicsWorld, Texture appearance,
                       float x, float y, float width, float height) {
        super(appearance);
        this.myGame = myGame;
        this.physicsWorld = physicsWorld;
        setPosition(x, y);
        setOrigin(x, y);
        setWidth(width);
        setHeight(height);
        initBody();

        Color color = new Color();
        Random random = new Random();

        float r = random.nextFloat();
        float g = random.nextFloat();
        float b = random.nextFloat();
        color.set(r, g, b, 1);
        this.setColor(Color.WHITE.cpy().lerp(color, 0.9f));
    }

    private void initBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(getX(), getY());
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.bullet = true;
        body = physicsWorld.createBody(bodyDef);

        PolygonShape bodyShape = new PolygonShape();
        bodyShape.setAsBox(getWidth(), getHeight());

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = bodyShape;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.5f;

        body.createFixture(fixtureDef);
        body.setUserData(this);

        bodyShape.dispose();
    }

    @Override
    public void act(float delta) {

        // this.setPosition(body.getPosition().x - getWidth() / 2,body.getPosition().y - getHeight() / 2);
        //this.setPosition(body.getPosition().x + x,body.getPosition().y + y);
        //this.setRotation(body.getAngle() * MathUtils.radiansToDegrees);

        // repositionBody(body.getPosition().x ,body.getPosition().y);
        // this.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
        //this.rotateBy(delta*50);
        //this.body.setLinearVelocity(200f,200f*delta);
        //this.body.applyTorque(5f,true);
        // this.body.applyForceToCenter(100f,100f,true);
    }

    private void repositionBody(float x, float y) {

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(x, y);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        // bodyDef.bullet = true;
        physicsWorld.destroyBody(body);


        body = physicsWorld.createBody(bodyDef);

        /*CircleShape bodyShape = new CircleShape();
        bodyShape.setRadius(getWidth());*/

        PolygonShape bodyShape = new PolygonShape();
        bodyShape.setAsBox(getWidth(), getHeight());

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = bodyShape;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.5f;

        body.createFixture(fixtureDef);
        body.setUserData(this);
        body.setLinearVelocity(5f, 0);

        bodyShape.dispose();
    }
}
