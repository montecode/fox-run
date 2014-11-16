package me.montecode.games.runningmonster.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import me.montecode.games.runningmonster.RunningMonsterGame;
import me.montecode.games.runningmonster.utils.Constants;

/**
 * Created by stevyhacker on 31.10.14..
 */
public class StartGameScreen implements Screen,InputProcessor {
    private static final int VIEWPORT_WIDTH = Constants.APP_WIDTH;
    private static final int VIEWPORT_HEIGHT = Constants.APP_HEIGHT;
    private final SpriteBatch batcher;
    private final Rectangle playBounds;
    private final Rectangle aboutBounds;
    private OrthographicCamera camera;
    Vector3 touchPoint;
    RunningMonsterGame game;
    private ShapeRenderer shapeRenderer;

    TextureRegion textureRegion;
    TextureRegion playBtn;
    TextureRegion aboutBtn;

    public StartGameScreen(RunningMonsterGame game) {
        this.game = game;
        setupCamera();
        batcher = new SpriteBatch();
        playBounds = new Rectangle(Constants.APP_WIDTH / 2, Constants.APP_HEIGHT / 2, 200, 75);
        aboutBounds = new Rectangle(Constants.APP_WIDTH / 2, Constants.APP_HEIGHT / 3.5f, 200, 75);
        touchPoint = new Vector3();
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);
        Gdx.input.setCatchBackKey(true);
        Gdx.input.setInputProcessor(this);
        textureRegion = new TextureRegion(new Texture(Gdx.files.internal("menu.png")));
        playBtn = new TextureRegion(new Texture(Gdx.files.internal("play_btn.png")));
        aboutBtn = new TextureRegion(new Texture(Gdx.files.internal("about_btn.png")));


    }

    public void update() {
        if (Gdx.input.justTouched()) {
            camera.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (playBounds.contains(touchPoint.x, touchPoint.y)) {
                game.setScreen(new MainGameScreen(game));        
            }

            if (aboutBounds.contains(touchPoint.x, touchPoint.y)) {
                game.setScreen(new AboutGameScreen(game));
            }
        }
    }

    public void draw() {

        GL20 gl = Gdx.gl;
        Gdx.gl.glClearColor(243, 236, 205, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batcher.setProjectionMatrix(camera.combined);


        batcher.begin();
        batcher.draw(textureRegion, 0, 0, Constants.APP_WIDTH, Constants.APP_HEIGHT);
        batcher.draw(playBtn, playBounds.getX(), playBounds.getY(), playBtn.getRegionWidth(), playBtn.getRegionHeight());
        batcher.draw(aboutBtn, aboutBounds.getX(), aboutBounds.getY(), aboutBtn.getRegionWidth(), aboutBtn.getRegionHeight());
        batcher.end();

//        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
//        shapeRenderer.setColor(Color.WHITE);
//        shapeRenderer.rect(playBounds.getX(), playBounds.getY(), playBounds.width, playBounds.height);
//        shapeRenderer.rect(aboutBounds.getX(), aboutBounds.getY(), aboutBounds.width, aboutBounds.height);
//        shapeRenderer.end();
    }

    @Override
    public void render(float delta) {
        update();
        draw();
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
            Gdx.app.exit();
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
