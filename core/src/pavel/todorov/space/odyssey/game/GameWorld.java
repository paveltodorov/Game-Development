package pavel.todorov.space.odyssey.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import pavel.todorov.space.odyssey.MyGame;
import pavel.todorov.space.odyssey.assets.Assets;
import pavel.todorov.space.odyssey.listener.B2dContactListener;
import pavel.todorov.space.odyssey.model.Player;
import pavel.todorov.space.odyssey.model.SolarSystem;
import pavel.todorov.space.odyssey.screen.MenuScreen;

import java.util.ArrayList;
import java.util.List;

//import com.badlogic.gdx;

public class GameWorld {

    private MyGame myGame;
    private World physicsWorld;
    private Player player;
    private Stage stage;
    private List<SolarSystem> solarSystems;
    private float worldWidth;
    private int score;

    //private SpriteBatch batch;
    //private Box2DDebugRenderer debugRenderer;

    public GameWorld(MyGame myGame) {
        this.myGame = myGame;
        this.physicsWorld = new World(new Vector2(0, -9.8f), false);
        this.player = new Player(myGame, this.physicsWorld, myGame.assets.manager.get(Assets.player, Texture.class),
                0.25f, MyGame.WORLD_HEIGHT / 2, 1, 1);

        this.physicsWorld.setContactListener(new B2dContactListener(this.player));

        float ratio = (float) Gdx.graphics.getHeight() / (float) Gdx.graphics.getWidth();
        this.worldWidth = MyGame.WORLD_HEIGHT / ratio;
        this.stage = new Stage(new StretchViewport(worldWidth, MyGame.WORLD_HEIGHT));

        this.stage.addActor(player);

        this.initWalls();

        this.score = 0;
    }

    public void render() {
        this.stage.draw();
        physicsWorld.step(Gdx.graphics.getDeltaTime(), 6, 2);
    }

    public void update() {
        this.stage.act();
        if (!this.player.getTransition()) {
            this.stage.getCamera().position.x = player.getX() + 5;
        }
        if (Gdx.input.justTouched() || Gdx.input.isKeyJustPressed(39)) {
            this.player.jump();
        }

        //the key j is for the high jump
        if (Gdx.input.isKeyJustPressed(38)) {
            this.player.highJump();
        }



        this.regenerateWall();
        this.updateScore();
        if (myGame.gameState == MyGame.GAME_STATE.MENU) {
            if (myGame.highscore < score) {
                myGame.highscore = score;
                myGame.updateHighscore(score);
            }
            myGame.setScreen(new MenuScreen(myGame));
        }
    }

    public int getPlayerLifes() {
        return player.getLifes();
    }

    private void initWalls() {
        solarSystems = new ArrayList<SolarSystem>(8);
        SolarSystem first = new SolarSystem(myGame, physicsWorld, stage, 15);
        solarSystems.add(first);
        for (int i = 1; i < 8; i++) {
            SolarSystem solarSystem = new SolarSystem(myGame, physicsWorld, stage, solarSystems.get(i - 1).getX() + 15);
            solarSystems.add(solarSystem);
        }
    }

    private void regenerateWall() {
        if (solarSystems.get(0).getX() < stage.getCamera().position.x - worldWidth / 2) {
            solarSystems.remove(0);
        }
        if (solarSystems.size() == 7) {
            SolarSystem solarSystem = new SolarSystem(myGame, physicsWorld, stage, solarSystems.get(solarSystems.size() - 1).getX() + 15);
            solarSystems.add(solarSystem);
        }
    }

    private void updateScore() {
        if (player.getX() > solarSystems.get(0).getX() && !solarSystems.get(0).getScored()) {
            solarSystems.get(0).score();
            this.score++;
        }
    }

    public int getScore() {
        return this.score;
    }

    public int getGiftsTaken() {
        return player.getGiftsTaken();
    }
}
