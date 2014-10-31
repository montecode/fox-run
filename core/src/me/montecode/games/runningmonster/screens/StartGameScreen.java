package me.montecode.games.runningmonster.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import me.montecode.games.runningmonster.RunningMonsterGame;
import me.montecode.games.runningmonster.utils.Constants;

/**
 * Created by stevyhacker on 31.10.14..
 */
public class StartGameScreen implements Screen {
    private static final int VIEWPORT_WIDTH = Constants.APP_WIDTH;
    private static final int VIEWPORT_HEIGHT = Constants.APP_HEIGHT;
    private final SpriteBatch batcher;
    private final Rectangle playBounds;
    private OrthographicCamera camera;
    Vector3 touchPoint;
    RunningMonsterGame game;
    private BitmapFont font;
    private ShapeRenderer shapeRenderer;

    public StartGameScreen(RunningMonsterGame game) {
        this.game = game;
        setupCamera();
        batcher = new SpriteBatch();
        playBounds = new Rectangle(200, 320, 300, 50);
        touchPoint = new Vector3();
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(camera.combined);
        font = new BitmapFont();

    }

    public void update() {
        if (Gdx.input.justTouched()) {
            camera.unproject(touchPoint.set(Gdx.input.getX(), Gdx.input.getY(), 0));

            if (playBounds.contains(touchPoint.x, touchPoint.y)) {
                game.setScreen(new MainGameScreen());
            }
        }
    }

    public void draw() {
        GL20 gl = Gdx.gl;
        Gdx.gl.glClearColor(243, 236, 205, 1);
        gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.update();
        batcher.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(43 / 255.0f, 19 / 255.0f, 55 / 255.0f, 1);
        shapeRenderer.rect(playBounds.getX(), playBounds.getY(), playBounds.width, playBounds.height);
        shapeRenderer.end();

        batcher.begin();
        font.setColor(Color.BLACK);
        font.setScale(1.5f);
        font.draw(batcher," P l a y ",playBounds.getX()+playBounds.getWidth()/3,playBounds.getY()+playBounds.getHeight()/2);
        batcher.end();
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


}
