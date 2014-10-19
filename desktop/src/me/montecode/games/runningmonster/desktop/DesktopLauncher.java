package me.montecode.games.runningmonster.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import me.montecode.games.runningmonster.RunningMonsterGame;
import me.montecode.games.runningmonster.utils.Constants;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.height = Constants.APP_HEIGHT;
        config.width= Constants.APP_WIDTH;
		new LwjglApplication(new RunningMonsterGame(), config);
	}
}
