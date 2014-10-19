package me.montecode.games.runningmonster;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import me.montecode.games.runningmonster.screens.MainGameScreen;

public class RunningMonsterGame extends Game {

	@Override
	public void create () {

        setScreen(new MainGameScreen());

	}

}
