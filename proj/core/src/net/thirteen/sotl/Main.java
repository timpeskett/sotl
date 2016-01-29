package net.thirteen.sotl;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.assets.AssetManager;
import net.thirteen.sotl.screens.TitleScreen;

public class Main extends Game {

	public SpriteBatch batch;
	public AssetManager manager;

	public static final int TILE_SIZE = 32;
	public static final int MAP_WIDTH = 20;
	public static final int MAP_HEIGHT = 14;
	public static final int WIDTH = MAP_WIDTH * TILE_SIZE;
	public static final int HEIGHT = MAP_HEIGHT * TILE_SIZE;
	
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		manager = new AssetManager();

		setScreen(new TitleScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}

	@Override public void dispose() {
		batch.dispose();
		manager.dispose();
	}
}
