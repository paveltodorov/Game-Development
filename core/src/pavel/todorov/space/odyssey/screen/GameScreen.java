package pavel.todorov.space.odyssey.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import pavel.todorov.space.odyssey.MyGame;
import pavel.todorov.space.odyssey.assets.Assets;
import pavel.todorov.space.odyssey.game.GameWorld;

public class GameScreen implements Screen {

    private MyGame myGame;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private GameWorld gameWorld;
    private Texture background;
    private BitmapFont font;

    public GameScreen(MyGame myGame) {
        this.myGame = myGame;
    }

    @Override
    public void show() {
        this.batch = new SpriteBatch();
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, MyGame.WIDTH, MyGame.HEIGHT);
        this.gameWorld = new GameWorld(this.myGame);
        this.background = myGame.assets.manager.get(Assets.background, Texture.class);
        this.font = new BitmapFont();

        this.font.getData().scale(8);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(55 / 255f, 51 / 255f, 102 / 255f, 1); // 	0, 51, 102
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(background, 0, 0);
        batch.end();
        gameWorld.render();
        batch.begin();
        drawScore(gameWorld.getScore(), batch);
        drawLifes(gameWorld.getPlayerLifes(), batch);
        drawGifts(gameWorld.getGiftsTaken(), batch);
        batch.end();

        gameWorld.update();
        camera.update();
    }

    private void drawScore(int score, SpriteBatch batch) {

        GlyphLayout glyphLayout = new GlyphLayout();
        String item = "Score: " + score;
        glyphLayout.setText(font, item);
        float w = glyphLayout.width;
        //font.draw(batch, glyphLayout, camera.position.x - w/2, MyGame.HEIGHT - 50);
        font.draw(batch, glyphLayout, camera.position.x - 2 * w, MyGame.HEIGHT - 50);
    }

    private void drawLifes(int lifes, SpriteBatch batch) {
        GlyphLayout glyphLayout = new GlyphLayout();
        String item1 = "Lifes: " + lifes;
        glyphLayout.setText(font, item1);
        float w = glyphLayout.width;
        font.draw(batch, glyphLayout, camera.position.x + w * 1, MyGame.HEIGHT - 50);
    }

    private void drawGifts(int gifts, SpriteBatch batch) {
        GlyphLayout glyphLayout = new GlyphLayout();
        String item1 = "Gifts: " + gifts;
        glyphLayout.setText(font, item1);
        float w = glyphLayout.width;
        font.draw(batch, glyphLayout, camera.position.x + w * 1, MyGame.HEIGHT - 200);
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
    }
}
