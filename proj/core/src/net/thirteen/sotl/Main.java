package net.thirteen.sotl;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import net.thirteen.sotl.screens.IntroScreen;
import net.thirteen.sotl.screens.TitleScreen;
import net.thirteen.sotl.World;
import net.thirteen.sotl.levels.LevelMaker;

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
		manager.load("grass.png", Texture.class);
		manager.load("sand.png", Texture.class);
		manager.load("wall.png", Texture.class);
		manager.load("hero.png", Texture.class);
		manager.load("door.png", Texture.class);
		manager.load("enemy.png", Texture.class);
		manager.load("titleScreenBG.png", Texture.class);
		manager.load("sheeprun.png", Texture.class);
		manager.finishLoading();

        world = new World(MAP_WIDTH, MAP_HEIGHT,
                          new Rectangle(0, 0, Main.WIDTH, Main.HEIGHT));

		setScreen(new IntroScreen(this));

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
