package net.thirteen.sotl.screens;

import net.thirteen.sotl.Main;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Gdx;
import net.thirteen.sotl.scenes.GameHud;
import net.thirteen.sotl.levels.Level;
import com.badlogic.gdx.math.Rectangle;
import net.thirteen.sotl.actors.Hero;
import com.badlogic.gdx.Input;
import net.thirteen.sotl.actors.Direction;

public class LevelScreen implements Screen {

	private Main game;
	private OrthographicCamera levelCam;
	private GameHud hud;
	private Level level;

	private Hero hero;

	public LevelScreen(Main game) {
		this.game = game;
		hud = new GameHud(game.batch);
		level = new Level(Main.MAP_WIDTH, Main.MAP_HEIGHT, 1, new Rectangle(0, 0, Main.WIDTH, Main.HEIGHT));

		//hero = game.world.getHero();

		hero = new Hero(level, 10 * Main.TILE_SIZE, 7 * Main.TILE_SIZE);

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
			hero.setPosition( hero.getX(), hero.getY() 
				+ hero.getSpeed() * Gdx.graphics.getDeltaTime());

		}
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN) 
			|| Gdx.input.isKeyPressed(Input.Keys.S)) {

			hero.setDirection(Direction.DOWN);
			hero.setPosition( hero.getX(), hero.getY() 
				- hero.getSpeed() * Gdx.graphics.getDeltaTime());

		}
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT) 
			|| Gdx.input.isKeyPressed(Input.Keys.A)) {

			hero.setDirection(Direction.LEFT);
			hero.setPosition( hero.getX() - hero.getSpeed() 
				* Gdx.graphics.getDeltaTime(), hero.getY());

		}
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) 
			|| Gdx.input.isKeyPressed(Input.Keys.D)) {

			hero.setDirection(Direction.RIGHT);
			hero.setPosition( hero.getX() + hero.getSpeed() 
				* Gdx.graphics.getDeltaTime(), hero.getY());
				

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