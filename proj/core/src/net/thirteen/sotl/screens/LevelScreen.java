package net.thirteen.sotl.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Input;

import net.thirteen.sotl.Main;
import net.thirteen.sotl.World;
import net.thirteen.sotl.actors.Hero;
import net.thirteen.sotl.actors.Enemy;
import net.thirteen.sotl.actors.Direction;
import net.thirteen.sotl.scenes.GameHud;
import net.thirteen.sotl.levels.Level;

public class LevelScreen implements Screen {

	private Main game;
	private OrthographicCamera levelCam;
	private GameHud hud;
	private Level level;

	private Hero hero;

	public LevelScreen(Main game) {
		this.game = game;
		hud = new GameHud(game.batch);

		level = game.world.getCurrentLevel();
		hero = game.world.getHero();

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
		hero.draw(game.batch);
        for(Enemy e : level.getEnemies()) {
            e.draw(game.batch);
        }

		game.batch.end();

		game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
		hud.stage.draw();
	}

	public void update(float delta) {
		handleInput(delta);
		hud.update(delta);
	}

	public void handleInput(float delta) {

		if(Gdx.input.isKeyPressed(Input.Keys.UP) 
			|| Gdx.input.isKeyPressed(Input.Keys.W)) {

			hero.setDirection(Direction.UP);
            hero.move(0, Gdx.graphics.getDeltaTime());
		}
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN) 
			|| Gdx.input.isKeyPressed(Input.Keys.S)) {

			hero.setDirection(Direction.DOWN);
            hero.move(0, -Gdx.graphics.getDeltaTime());

		}
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT) 
			|| Gdx.input.isKeyPressed(Input.Keys.A)) {

			hero.setDirection(Direction.LEFT);
            hero.move(-Gdx.graphics.getDeltaTime(), 0);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) 
			|| Gdx.input.isKeyPressed(Input.Keys.D)) {

			hero.setDirection(Direction.RIGHT);
            hero.move(Gdx.graphics.getDeltaTime(), 0);
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
