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

public class CosmicDebrie extends Image {

    private MyGame myGame;
    private World physicsWorld;
    private Body body;

    public CosmicDebrie(MyGame myGame, World physicsWorld, Texture appearance,
                        float x, float y, float width, float height) {
        super(appearance);
        this.myGame = myGame;
        this.physicsWorld = physicsWorld;
        setPosition(x, y);
        setOrigin(x, y);
        setWidth(width);
        setHeight(height);
        Color color = new Color();
        this.setColor(Color.WHITE.cpy().lerp(color.set(210f / 255f, 82f / 255f, 82f / 255f, 1), 0.9f));
        initBody();
    }

    private void initBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(getX(), getY());
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.bullet = true;
        body = physicsWorld.createBody(bodyDef);

        /*PolygonShape bodyShape = new PolygonShape();
        bodyShape.setAsBox(getWidth(), getHeight());*/

        CircleShape bodyShape = new CircleShape();
        bodyShape.setRadius(getWidth() / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = bodyShape;
        fixtureDef.density = 0.5f;
        fixtureDef.friction = 0.5f;
        fixtureDef.restitution = 0.5f;

        body.createFixture(fixtureDef);
        body.setUserData(this);

        bodyShape.dispose();
    }
}
