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
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

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
    private Rectangle linkRect;
    private ShapeRenderer shapeRenderer;
    private int firstTouch;

    public AboutGameScreen(RunningMonsterGame game) {
        this.game = game;
        setupCamera();
        batcher = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        Gdx.input.setCatchBackKey(true);
        Gdx.input.setInputProcessor(this);
        about = new TextureRegion(new Texture(Gdx.files.internal("about.png")));

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator((Gdx.files.internal("RobotoCondensed-Regular.ttf")));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.kerning = true;
        fontParameter.size = (int) (20 * Gdx.graphics.getDensity());
        Gdx.app.log("density", "display density : " + String.valueOf(Gdx.graphics.getDensity()));
        linkRect = new Rectangle(VIEWPORT_WIDTH / 2, VIEWPORT_HEIGHT / 4, 200, 200);
        font = generator.generateFont(fontParameter);
        font.setColor(Color.WHITE);
        generator.dispose();
        firstTouch = 0;

    }

    public void draw() {
        GL20 gl = Gdx.gl;
        Gdx.gl.glClearColor(243, 236, 205, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();


        batcher.setProjectionMatrix(camera.combined);

        batcher.begin();


        batcher.draw(about, 0, 0, VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        font.draw(batcher, "www.montecode.me", VIEWPORT_WIDTH / 2, VIEWPORT_HEIGHT / 4);


        batcher.end();

//        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
//        shapeRenderer.setColor(Color.WHITE);
//        shapeRenderer.rect(VIEWPORT_WIDTH / 2 - 165, VIEWPORT_HEIGHT / 2 - 150, 330, 300);
//        shapeRenderer.end();

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
        firstTouch++;
        if (linkRect.contains(screenX, screenY) && firstTouch > 1) {
            Gdx.net.openURI("http://montecode.me");
        }

        return true;
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
