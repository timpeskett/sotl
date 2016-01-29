package net.thirteen.sotl.screens;

import net.thirteen.sotl.Main;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class TitleScreen implements Screen {

	private Main game;
	private OrthographicCamera titleCam;

	public TitleScreen(Main game) {
		this.game = game;

		titleCam = new OrthographicCamera();
		titleCam.setToOrtho(false, Main.WIDTH, Main.HEIGHT);
	}

	@Override
	public void show() {}

	@Override
	public void render(float delta) {
		update(delta);

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		game.batch.begin();
		game.batch.draw(Main.manager.get("titleScreenBG.png", Texture.class), 0, 0);
		game.batch.end();
	}

	public void update(float delta) {

		handleInput(delta);

	}

	public void handleInput(float delta) {
		if(Gdx.input.isTouched() || 
		   Gdx.input.isKeyPressed(Input.Keys.ENTER)) {
			game.setScreen(new LevelScreen(game));
			dispose();
		}
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