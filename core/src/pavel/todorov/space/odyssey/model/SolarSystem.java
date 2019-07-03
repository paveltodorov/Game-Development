package pavel.todorov.space.odyssey.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import pavel.todorov.space.odyssey.MyGame;
import pavel.todorov.space.odyssey.assets.Assets;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SolarSystem {

    private MyGame myGame;
    private World physicsWorld;
    private List<Cosmos> cosmosList;
    private float x;
    private boolean scored;
    private Stage stage;

    public SolarSystem(MyGame myGame, World physicsWorld, Stage stage, float x) {
        this.myGame = myGame;
        this.physicsWorld = physicsWorld;
        this.stage = stage;
        this.x = x;
        this.scored = false;
        initialize();
    }

    private void initialize() {
        cosmosList = new ArrayList<Cosmos>(5);
        Random random = new Random();
        Texture texture = myGame.assets.manager.get(Assets.enemy, Texture.class);
        int index = random.nextInt(5);
        for (int i = 0; i < 3; i++) {
            if (i != index) {
                Cosmos cosmos = new Cosmos(myGame, physicsWorld, stage,
                        texture,
                        x, 10, i / 1.7f + 2, i % 2 == 0);
                //i * 5 + 2
                cosmosList.add(cosmos);
            }
        }

        //Random random = new Random();
        float giftPositionX = x - 3 + random.nextFloat() * 6;
        float giftPositionY = 2 + random.nextFloat() * 6;

        Gift gift = new Gift(myGame, physicsWorld, myGame.assets.manager.get(Assets.gift, Texture.class), giftPositionX, giftPositionY, 0.5f, 0.5f);
        stage.addActor(gift);

        for (Cosmos e : cosmosList) {
            stage.addActor(e);
        }
    }

    public float getX() {
        return x;
    }

    public float getWidth() {
        return cosmosList.get(0).getWidth();
    }

    public boolean getScored() {
        return scored;
    }

    public void score() {
        scored = true;
    }

}
