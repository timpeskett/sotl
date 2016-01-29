package net.thirteen.sotl.screens;

import net.thirteen.sotl.Main;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Gdx;
import net.thirteen.sotl.scenes.GameHud;
import net.thirteen.sotl.levels.Level;
import com.badlogic.gdx.math.Rectangle;

public class LevelScreen implements Screen {

	private Main game;
	private OrthographicCamera levelCam;
	private GameHud hud;
	private Level level;

	public LevelScreen(Main game) {
		this.game = game;
		hud = new GameHud(game.batch);
		level = new Level(Main.MAP_WIDTH, Main.MAP_HEIGHT, 1, new Rectangle(0, 0, Main.WIDTH, Main.HEIGHT));

		levelCam = new OrthographicCamera();
		levelCam.setToOrtho(false, Main.WIDTH, Main.HEIGHT);
	}

	@Override
	public void show() {}

	@Override
	public void render(float delta) {
		update(delta);

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		game.batch.setProjectionMatrix(levelCam.combined);
		game.batch.begin();
		for(int ii = 0; ii < Main.MAP_WIDTH; ii++) {
			for(int jj = 0; jj < Main.MAP_HEIGHT; jj++) {
				game.batch.draw(level.getTile(ii, jj).getTexture(), ii * Main.TILE_SIZE, jj * Main.TILE_SIZE);
			}
		}

		game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
		hud.stage.draw();
	}

	public void update(float delta) {
		hud.update(delta);
	}


	@Override
	public void resize(int width, int height) {}

	@Override
	public void pause() {}

	@Override
	public void resume() {}

	@Override
	public void hide() {}

	@Override
	public void dispose() {

	}

}