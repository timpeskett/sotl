package net.thirteen.sotl.screens;

import net.thirteen.sotl.Main;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class TitleScreen implements Screen {

	private Main game;
	private OrthographicCamera titleCam;
	private GlyphLayout title, start;
	private float screenTimer, flashTimer;
	private boolean ready;

	public TitleScreen(Main game) {
		this.game = game;
		title = new GlyphLayout();
		start = new GlyphLayout();

		screenTimer = 0;
		flashTimer =  0;

		game.font.getData().setScale(2, 2);

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
		game.font.draw(game.batch, title, (Main.WIDTH - title.width) / 2, (Main.HEIGHT + title.height) * 0.75f);
		if(screenTimer >= 4)
			game.fontB.draw(game.batch, start, (Main.WIDTH - start.width) / 2, (Main.HEIGHT + start.height) * 0.1f);
		game.batch.end();
	}

	public void update(float delta) {

		handleInput(delta);

		screenTimer = screenTimer >= 4 ? 4 : screenTimer + delta;

		flashTimer = flashTimer >= 1 ? 0 : flashTimer + delta;

		game.font.setColor(0, 0, 0, screenTimer / 4);
		game.fontB.setColor(1, 1, 1, flashTimer);
		title.setText(game.font, "Silence of the Lamb");
		start.setText(game.fontB, "click / press enter to begin");

	}

	public void handleInput(float delta) {
		if(screenTimer > 0.2 && (Gdx.input.isTouched() || 
		   Gdx.input.isKeyPressed(Input.Keys.ENTER))) {
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