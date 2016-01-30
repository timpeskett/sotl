package net.thirteen.sotl.screens;

import net.thirteen.sotl.Main;
import net.thirteen.sotl.screens.LevelScreen;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class DeathScreen implements Screen {

	private Main game;
	private OrthographicCamera titleCam;
	private GlyphLayout layout;
	private float screenTimer;
	private int twoSecondInterval;
	private float scoreTime;

	public DeathScreen(Main game, float scoreTime) {
		this.game = game;
		layout = new GlyphLayout();
		screenTimer = 0;
		twoSecondInterval = 0;
		this.scoreTime = scoreTime;

		titleCam = new OrthographicCamera();
		titleCam.setToOrtho(false, Main.WIDTH, Main.HEIGHT);
	}

	@Override
	public void show() {}

	@Override
	public void render(float delta) {
		update(delta);

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		game.batch.begin();
		game.batch.draw(Main.manager.get("deathScreenBG.png", Texture.class), 0, 0);
		game.font.draw(game.batch, layout, (Main.WIDTH - layout.width) / 2, (Main.HEIGHT + layout.height) / 2);
		game.batch.end();
	}

	public void update(float delta) {

		handleInput(delta);

		screenTimer += delta;
		if(screenTimer >= 2) {
			screenTimer = 0;
			twoSecondInterval++;
		}

		switch(twoSecondInterval) {
			case 0:
				game.font.setColor(1, 1, 1, screenTimer / 2);
				break;
			case 1:
				game.font.setColor(1, 1, 1, 1);
				break;
			default:
				break;
		}

		layout.setText(game.font, "You escaped the ritual for " + (int)scoreTime + " seconds");

	}

	public void handleInput(float delta) {
		if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
			dispose();
			System.exit(0);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.ENTER) || (Gdx.input.isTouched())) {
			game.reset();
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