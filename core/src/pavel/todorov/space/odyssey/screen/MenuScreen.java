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


public class MenuScreen implements Screen {

    private MyGame myGame;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Texture menuBackground;
    private BitmapFont font;

    public MenuScreen(MyGame myGame){
        this.myGame = myGame;
    }

    @Override
    public void show() {
        this.batch = new SpriteBatch();
        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, MyGame.WIDTH, MyGame.HEIGHT);
        //this.camera.position.set(-0)
        this.camera.update();
        this.menuBackground = myGame.assets.manager.get(Assets.menu_background, Texture.class);
        this.font = new BitmapFont();

        this.font.getData().scale(8);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0/255f, 51/255f, 102/255f, 1); // 	0, 51, 102
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.setProjectionMatrix(camera.combined);
        camera.update();
        batch.begin();
        batch.draw(menuBackground,0,0);
        drawHighScore(myGame.highscore,batch);
       // drawMostGifts(myGame.mostGifts,batch);
        batch.end();

        if(Gdx.input.justTouched()){
            myGame.gameState = MyGame.GAME_STATE.PLAYING;
            myGame.setScreen(new GameScreen(myGame));
        }
    }

    private void drawHighScore(int score,SpriteBatch batch){

        GlyphLayout glyphLayout = new GlyphLayout();
        String item = "Highscore: " + score;
        glyphLayout.setText(font,item);
        float w = glyphLayout.width;
        font.draw(batch, glyphLayout, camera.position.x - w/2 + 20, 410);
    }

    private void drawMostGifts(int gifts,SpriteBatch batch){

        GlyphLayout glyphLayout = new GlyphLayout();
        String item = "Most gifts in one game: " + gifts;
        glyphLayout.setText(font,item);
        float w = glyphLayout.width;
        font.draw(batch, glyphLayout, camera.position.x - w/2 , 230);
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

    }
}
