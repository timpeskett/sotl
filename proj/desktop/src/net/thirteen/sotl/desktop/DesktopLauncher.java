package net.thirteen.sotl.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import net.thirteen.sotl.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Silence of the Lamb";
		config.width = Main.WIDTH;
		config.height = Main.HEIGHT;
		new LwjglApplication(new Main(), config);
	}
}
