package pavel.todorov.space.odyssey.assets;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class Assets {

    public AssetManager manager;

    public static String badlogic = "img/badlogic.jpg";
    public static String redBall = "ball/redball.png";
    public static String player = "space/spaceship.png";
    public static String gift = "enemy/enemy.png";
    public static String enemy = "ball/w6.png";
    public static String background = "space/space.jpg";
    public static String menu_background = "menu/menu_background.png";
    public static String star = "ball/w6.png";

    public Assets(){
        manager = new AssetManager();
    }

    public void load(){
        manager.load(badlogic, Texture.class);
        manager.load(player, Texture.class);
        manager.load(enemy, Texture.class);
        manager.load(background, Texture.class);
        manager.load(menu_background, Texture.class);
        manager.load(star, Texture.class);
        manager.load(gift, Texture.class);
        //Sound sound = Gdx.audio.newSound(Gdx.files.internal("data/mysound.mp3"));
    }

    public void dispose(){
        manager.dispose();
    }
}
