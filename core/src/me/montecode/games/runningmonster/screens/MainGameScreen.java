package me.montecode.games.runningmonster.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by pc on 18.10.2014.
 */
public class MainGameScreen implements Screen {

    SpriteBatch batch = new SpriteBatch();
    Texture pelican;

    @Override
    public void render(float delta) {

        pelican = new Texture("pelican.jpg");
        batch.begin();
        batch.draw(pelican,0,0);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
