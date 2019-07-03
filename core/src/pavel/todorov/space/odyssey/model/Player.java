package pavel.todorov.space.odyssey.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import pavel.todorov.space.odyssey.MyGame;


public class Player extends Image {

    private MyGame myGame;
    private World physicsWorld;
    private Body body;
    private boolean transition;
    private int lifes;
    private final int MAXLIFES = 3;
    private int giftsTaken;

    public Player(MyGame myGame, World physicsWorld, Texture appearance, float x, float y,
                  float width, float height) {
        super(appearance);
        this.setX(x);
        this.setY(y);
        this.setOrigin(x, y);
        this.setWidth(width);
        this.setHeight(height);
        this.myGame = myGame;
        this.physicsWorld = physicsWorld;
        this.initBody();
        this.transition = false;
        this.lifes = MAXLIFES;
        this.giftsTaken = 0;
    }

    private void repositionBody(float y) {

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(body.getPosition().x, y);
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        physicsWorld.destroyBody(body);
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
        body.setLinearVelocity(5f, 0);

        bodyShape.dispose();
        //this.initBody()bodyDef.position.set(body.getPosition().x,y);
    }

    private void initBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(getX(), getY());
        bodyDef.type = BodyDef.BodyType.DynamicBody;

        body = physicsWorld.createBody(bodyDef);

        PolygonShape bodyShape = new PolygonShape();
        bodyShape.setAsBox(getWidth() / 2, getHeight() / 2);

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

    @Override
    public void act(float delta) {
        this.setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
        // this.setRotation(body.getAngle() * MathUtils.radiansToDegrees);
        keepPlayerInViewPort();
    }

    public boolean getTransition() {
        return this.transition;
    }

    public void jump() {
        body.setLinearVelocity(body.getLinearVelocity().x, 0);
        // body.setLinearVelocity(body.getLinearVelocity().x,0);
        //body.applyForceToCenter(0f, 200f, true);
        body.applyForceToCenter(10f, 200f, true);
        //body.applyAngularImpulse(5f,true);
    }

    public void highJump() {
        body.setLinearVelocity(body.getLinearVelocity().x, 0);
        // body.setLinearVelocity(body.getLinearVelocity().x,0);
        //body.applyForceToCenter(0f, 200f, true);
        body.applyForceToCenter(60f, 200f, true);
        //body.applyAngularImpulse(5f,true);
    }

    public int getLifes() {
        return lifes;
    }

    public void setLifes(int lifes) {
        this.lifes = lifes;
    }

    public void removeOneLife() {
        this.lifes -= 1;
    }

    public void addOneLife() {
        this.lifes += 1;
    }

    public void die() {

        myGame.gameState = MyGame.GAME_STATE.MENU;
    }

    public void incrementGiftsTaken() {
        this.giftsTaken += 1;
    }

    public int getGiftsTaken() {
        return giftsTaken;
    }

    private void keepPlayerInViewPort() {
        if (getY() > MyGame.WORLD_HEIGHT) {
            repositionBody(-getHeight());
            transition = true;
        } else if (getY() < -2 * getHeight()) {
            repositionBody(MyGame.WORLD_HEIGHT);
            transition = true;
        } else {
            transition = false;
        }
    }
}
