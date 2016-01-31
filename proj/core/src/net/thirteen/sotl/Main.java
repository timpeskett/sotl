package net.thirteen.sotl;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;

import net.thirteen.sotl.screens.IntroScreen;
import net.thirteen.sotl.screens.TitleScreen;
import net.thirteen.sotl.World;
import net.thirteen.sotl.levels.LevelMaker;
import net.thirteen.sotl.screens.LevelScreen;
import net.thirteen.sotl.tiles.MapTileFactory;

public class Main extends Game {

	public SpriteBatch batch;
    public World world;
	public static AssetManager manager;
	public BitmapFont font;
	public BitmapFont fontB;

	public static final int TILE_SIZE = 32;
	public static final int MAP_WIDTH = 20;
	public static final int MAP_HEIGHT = 14;
	public static final int WIDTH = MAP_WIDTH * TILE_SIZE;
	public static final int HEIGHT = MAP_HEIGHT * TILE_SIZE;
	
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		fontB = new BitmapFont();
		manager = new AssetManager();
        for(String fName : MapTileFactory.getTileNames()) {
            manager.load(fName, Texture.class);
        }
		manager.load("enemyrun.png", Texture.class);
		manager.load("spear_enemyrun.png", Texture.class);
		manager.load("sheeprun.png", Texture.class);
		manager.load("titleScreenBG.png", Texture.class);
		manager.load("deathScreenBG.png", Texture.class);
		manager.load("oi.mp3", Sound.class);
		manager.load("bleet.mp3", Sound.class);
		manager.load("bgMusic.mp3", Music.class);
		manager.finishLoading();

		Music bgm = manager.get("bgMusic.mp3", Music.class);
		bgm.setLooping(true);
		bgm.play();

        world = new World(MAP_WIDTH, MAP_HEIGHT,
                          new Rectangle(0, 0, Main.WIDTH, Main.HEIGHT));

		setScreen(new IntroScreen(this));

	}

	public void reset(){
		font = new BitmapFont();

        world = new World(MAP_WIDTH, MAP_HEIGHT,
                          new Rectangle(0, 0, Main.WIDTH, Main.HEIGHT));

		setScreen(new TitleScreen(this));
	}

	@Override
	public void render () {
		super.render();

		//ESC quits
		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			dispose();
			System.exit(0);
		}
	}

	@Override public void dispose() {
		batch.dispose();
		manager.dispose();
		font.dispose();
		fontB.dispose();
		world.dispose();
	}
}
