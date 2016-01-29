package net.thirteen.sotl;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Input;

import net.thirteen.sotl.screens.TitleScreen;
import net.thirteen.sotl.World;

public class Main extends Game {

	public SpriteBatch batch;
    public World world;
	public static AssetManager manager;

	public static final int TILE_SIZE = 32;
	public static final int MAP_WIDTH = 20;
	public static final int MAP_HEIGHT = 14;
	public static final int WIDTH = MAP_WIDTH * TILE_SIZE;
	public static final int HEIGHT = MAP_HEIGHT * TILE_SIZE;
	
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		manager = new AssetManager();
		manager.load("grass.png", Texture.class);
		manager.load("wall.png", Texture.class);
		manager.load("hero.png", Texture.class);
		manager.load("door.png", Texture.class);
		manager.load("enemy.png", Texture.class);
		manager.load("titleScreenBG.png", Texture.class);
		manager.finishLoading();
		setScreen(new TitleScreen(this));

        world = new World();
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
	}
}
