package net.thirteen.sotl.scenes;

import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import net.thirteen.sotl.Main;

public class GameHud implements Disposable {

	public Stage stage;
	private Viewport viewport;

	private float timeCount;
	private Label timerLabel;

	public GameHud(SpriteBatch batch) {

		timeCount = 0;

		viewport = new FitViewport(Main.WIDTH, Main.HEIGHT, new OrthographicCamera());
		stage = new Stage(viewport, batch);

		Table table = new Table();
		table.top();
		table.setFillParent(true);

		timerLabel = new Label(String.format("%d", (int)(timeCount*100)), new Label.LabelStyle(new BitmapFont(), Color.BLACK));

		table.add(timerLabel).expandX().padTop(10);

		stage.addActor(table);

	}

	public void update(float delta) {

		timeCount += delta;

		timerLabel.setText(String.format("%d", (int)(timeCount*100)));

	}

	public float getTimeCount(){
		return timeCount;
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

}