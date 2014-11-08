package me.montecode.games.runningmonster.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import me.montecode.games.runningmonster.RunningMonsterGame;
import me.montecode.games.runningmonster.utils.Constants;

/**
 * Created by stevyhacker on 1.11.14..
 */
public class AboutGameScreen implements Screen, InputProcessor {

    private RunningMonsterGame game;
    private static final int VIEWPORT_WIDTH = Constants.APP_WIDTH;
    private static final int VIEWPORT_HEIGHT = Constants.APP_HEIGHT;
    private OrthographicCamera camera;
    private SpriteBatch batcher;
    private BitmapFont font;
    private TextureRegion about;
    

    public AboutGameScreen(RunningMonsterGame game) {
        this.game = game;
        setupCamera();
        font = new BitmapFont();
        batcher = new SpriteBatch();
        Gdx.input.setCatchBackKey(true);
        Gdx.input.setInputProcessor(this);
        about = new TextureRegion(new Texture(Gdx.files.internal("about.png")));

    }

    public void draw() {
        GL20 gl = Gdx.gl;
        Gdx.gl.glClearColor(243, 236, 205, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();


        batcher.begin();
        
        batcher.draw(about, 0, 0, Constants.APP_WIDTH, Constants.APP_HEIGHT);
        
        batcher.end();
    }


    @Override
    public void render(float delta) {
//        update();
        draw();
    }

    private void update() {

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

    private void setupCamera() {
        camera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f);
        camera.update();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {

        if (keycode == Input.Keys.BACK) {
            game.setScreen(new StartGameScreen(game));
        }

        return true;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
