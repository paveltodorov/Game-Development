package pavel.todorov.space.odyssey.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import pavel.todorov.space.odyssey.MyGame;
import pavel.todorov.space.odyssey.assets.Assets;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Cosmos extends Image {

    private MyGame myGame;
    private World physicsWorld;
    private List<WallSegment> wallSegments;
    private List<Planet> planets;
    private List<CosmicDebrie> debries;
    private float x;
    private float y;
    private Texture texture;
    private Stage stage;
    private float radius = 2;
    //private float step = 100.4f;
    private float step = 3.14f / 10f;
    private boolean direction;
    private long startTime;
    private float width = 0.5f;
    private float height = 0.5f;

    public Cosmos(MyGame myGame, World physicsWorld, Stage stage, Texture texture,
                  float x, float y, float radius, boolean direction) {
        startTime = System.currentTimeMillis();
        this.myGame = myGame;
        this.physicsWorld = physicsWorld;
        this.stage = stage;
        this.texture = texture;
        this.x = x;
        Random random = new Random();
        this.y = y - 3 + random.nextInt(8);
        this.radius = radius;
        this.direction = direction;
        initialize();
    }

    private void initialize() {
        wallSegments = new ArrayList<WallSegment>(5);
        planets = new ArrayList<Planet>(5);
        debries = new ArrayList<CosmicDebrie>(15);
        Random random = new Random();
        int index = random.nextInt(10);
        for (int i = 0; i < index; i++) {
            float positionX = (float) (x + (float) radius * Math.sin(i * step));
            float positionY = (float) (y + (float) radius * Math.cos(i * step));
            WallSegment segment = new WallSegment(myGame, physicsWorld,
                    texture, positionX, positionY, 0.5f, 0.5f);
            Planet planet = new Planet(myGame, physicsWorld, myGame.assets.manager.get(Assets.star, Texture.class), x, y, 1, 1);

            CosmicDebrie upperDebrie = new CosmicDebrie(myGame, physicsWorld, texture,
                    x + random.nextInt(10), y + random.nextInt(10) + 3, 0.5f, 0.5f);
            debries.add(upperDebrie);

            CosmicDebrie underDebrie = new CosmicDebrie(myGame, physicsWorld, texture,
                    x + random.nextInt(10), y + random.nextInt(10) - 13, 0.5f, 0.5f);
            debries.add(underDebrie);

            wallSegments.add(segment);
            planets.add(planet);
        }
        addElemetsToStage();
    }

    private void addElemetsToStage() {
        for (WallSegment segment : wallSegments) {
            stage.addActor(segment);
        }

        for (Planet planet : planets) {
            stage.addActor(planet);
        }

        for (CosmicDebrie debrie : debries) {
            stage.addActor(debrie);
        }

    }


    public float getX() {
        return x;
    }

    @Override
    public void act(float delta) {
        for (int i = 0; i < wallSegments.size(); i++) {
            long time = (System.currentTimeMillis() - startTime) / 100;
            if (!direction) {
                time = -time;
            }
            float positionX = (float) (x + (float) radius * Math.sin((i + time) * step));
            float positionY = (float) (y + (float) radius * Math.cos((i + time) * step));
            wallSegments.get(i).setPosition(positionX, positionY);
        }
    }

    public float getWidth() {
        return 0.5f;
    }
}
