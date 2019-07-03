package pavel.todorov.space.odyssey;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import pavel.todorov.space.odyssey.assets.Assets;
import pavel.todorov.space.odyssey.screen.SplashScreen;
public class MyGame extends Game {

	public enum GAME_STATE{
		PLAYING,
		MENU
	}

	public static float WIDTH = 2520; //pixels
	public static float HEIGHT = 4160;

	public static float WORLD_HEIGHT = 20; // the unit is meters

	public Assets assets;
	public GAME_STATE gameState;
	public int highscore;
	public int mostGifts;
	private Preferences preferences;

	@Override
	public void create () {
		this.assets = new Assets();
		this.setScreen(new SplashScreen(this));
		this.preferences = Gdx.app.getPreferences("highscorePreferences");
		if(preferences.contains("highscore")) {
			this.highscore = preferences.getInteger("highscore");
		} else {
			updateHighscore(0);
			this.highscore = 0;
		}

		if(preferences.contains("mostGifts")) {
			//System.out.println("bbbbbbbbbbbb  " + preferences.getInteger("mostGifts"));
			this.mostGifts = preferences.getInteger("mostGifts");
		} else {
			//System.out.println("aaaaaaaaaaaaaaaaa");
			updateMostGifts(0);
			this.mostGifts = 0;
		}
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		super.dispose();
		this.assets.dispose();
	}

	public void updateHighscore(int newHighscore){
		preferences.putInteger("highscore",newHighscore);
		preferences.flush();
	}

	public void updateMostGifts(int mostGifts){
		System.out.println(mostGifts);
		preferences.putInteger("mostGifts",mostGifts);
		preferences.flush();
	}
}
/*public class MyGame extends Game {

	public enum GAME_STATE{
		PLAYING,
		MENU
	}

	public static float WIDTH = 2520; //pixels
	public static float HEIGHT = 4160;

	public static float WORLD_HEIGHT = 20; // the unit is meters

	public Assets assets;
	public GAME_STATE gameState;
	public int highscore;
	public int mostGifts;
	private Preferences preferences;

	@Override
	public void create () {
		this.assets = new Assets();
		this.setScreen(new SplashScreen(this));
		this.preferences = Gdx.app.getPreferences("highscorePreferences");

		//this.preferences.putInteger("mostGifts", 0);

		if(preferences.contains("highscore")) {
			this.highscore = preferences.getInteger("highscore");
		} else {
			updateHighscore(0);
			this.highscore = 0;
		}

		if(preferences.contains("mostGifts")) {
			this.highscore = preferences.getInteger("mostGifts");
		} else {
			updateMostGifts(0);
			this.mostGifts = 0;
		}
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		super.dispose();
		this.assets.dispose();
	}

	public void updateHighscore(int newHighscore){
		System.out.println("h  " + highscore);
		preferences.putInteger("highscore",newHighscore);
		preferences.flush();
	}

	public void updateMostGifts(int mostGifts){
		System.out.println(mostGifts);
		preferences.putInteger("mostGifts",mostGifts);
		preferences.flush();
	}
}
*/