package pavel.todorov.space.odyssey.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.FillViewport;
import pavel.todorov.space.odyssey.MyGame;

public class SplashScreen implements Screen {
    private MyGame myGame;
    private OrthographicCamera camera;
    private SpriteBatch batch;
    private Texture texture;
    private Image splashImage;
    private Stage stage;

    public SplashScreen(MyGame myGame){
        this.myGame = myGame;
        this.texture = new Texture("loading/space.jpg");
        this.texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        this.batch = new SpriteBatch();
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, MyGame.WIDTH, MyGame.HEIGHT);
        this.stage = new Stage(new FillViewport(MyGame.WIDTH, MyGame.HEIGHT));
        this.camera.update();
        this.splashImage = new Image(texture);
        this.stage.addActor(splashImage);
        this.splashImage.setPosition(0,0);
    }

    @Override
    public void show() {
        this.splashImage.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(0.0f), Actions.delay(0.0f), Actions.run(new Runnable() {
            @Override
            public void run() {
                myGame.assets.load();
                while(!myGame.assets.manager.update())
                {

                }

                myGame.setScreen(new MenuScreen(myGame));
            }
        })));


    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(255, 255, 255, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();
        batch.draw(texture, 0, 0);
        batch.end();
        stage.act();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        stage.dispose();
    }
}