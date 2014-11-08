package me.montecode.games.runningmonster.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.text.DecimalFormat;

import me.montecode.games.runningmonster.utils.Constants;

public class Background extends Actor {

    private final TextureRegion textureRegion;
    private Rectangle textureRegionBounds1;
    private Rectangle textureRegionBounds2;
    private int speed = 100;
    private BitmapFont font;
    float runTime;
    float lastRunTime;
    DecimalFormat decimalFormat = new DecimalFormat("###.##");
    private boolean scrollEnabled;
    private static Preferences prefs;

    public Background() {
        runTime = 0;
        lastRunTime = 0;
        scrollEnabled = true;
        textureRegion = new TextureRegion(new Texture(Gdx.files.internal(Constants.BACKGROUND_IMAGE_PATH)));
        textureRegionBounds1 = new Rectangle(0 - Constants.APP_WIDTH / 2, 0, Constants.APP_WIDTH, Constants.APP_HEIGHT);
        textureRegionBounds2 = new Rectangle(Constants.APP_WIDTH / 2, 0, Constants.APP_WIDTH, Constants.APP_HEIGHT);


        FreeTypeFontGenerator generator = new FreeTypeFontGenerator((Gdx.files.internal("RobotoCondensed-Regular.ttf")));
        FreeTypeFontGenerator.FreeTypeFontParameter fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        fontParameter.kerning = true;
        fontParameter.size = (int) (20 * Gdx.graphics.getDensity());
        font = generator.generateFont(fontParameter);
        font.setColor(Color.BLACK);
        generator.dispose();

        prefs = Gdx.app.getPreferences("RunningMonster");

        // Provide default high score of 0
        if (!prefs.contains("highScore")) {
            prefs.putFloat("highScore", 0);
        }
    }

    @Override
    public void act(float delta) {
        if (scrollEnabled)
            runTime += delta * 2;
        if (leftBoundsReached(delta)) {
            resetBounds();
        } else {
            updateXBounds(-delta);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        super.draw(batch, parentAlpha);
        batch.draw(textureRegion, textureRegionBounds1.x, textureRegionBounds1.y, Constants.APP_WIDTH,
                Constants.APP_HEIGHT);
        batch.draw(textureRegion, textureRegionBounds2.x, textureRegionBounds2.y, Constants.APP_WIDTH,
                Constants.APP_HEIGHT);
        font.setColor(Color.BLACK);
//  Display FPS font.draw(batch, "FPS:" + Gdx.graphics.getFramesPerSecond(),600 , 450);// Gdx.graphics.getWidth() / 1.5f, Gdx.graphics.getHeight()/2);
        if (runTime > lastRunTime + 0.2) {
            lastRunTime = runTime;
            if (scrollEnabled) {
                font.draw(batch, String.valueOf(decimalFormat.format(runTime)) + " m", 600, 450);
//                font.draw(batch, String.valueOf(decimalFormat.format(Enemy.getSpeed())) + " speed", 300, 450);
            } else {
                checkScore(runTime);
//                font.setScale(2);
                font.setColor(Color.BLACK);
                font.draw(batch, String.valueOf("Your highest score: " + decimalFormat.format(getHighScore())) + " m", 250, 375);
                font.draw(batch, String.valueOf("Your score: " + decimalFormat.format(runTime)) + " m", 250, 300);

            }
        } else {
            if (scrollEnabled) {
                font.draw(batch, String.valueOf(decimalFormat.format(runTime)) + " m", 600, 450);
            } else {
                checkScore(runTime);
//                font.setScale(2);
                font.setColor(Color.BLACK);
                font.draw(batch, String.valueOf("Your highest score: " + decimalFormat.format(getHighScore())) + " m", 250, 375);
                font.draw(batch, String.valueOf("Your current score: " + decimalFormat.format(runTime)) + " m", 250, 300);

            }
        }

//        Gdx.app.log("SCREENDIMENSIONS", " height " + String.valueOf(Gdx.graphics.getHeight()) + " width " + String.valueOf(Gdx.graphics.getWidth()) );

    }

    private boolean leftBoundsReached(float delta) {
        return (textureRegionBounds2.x - (delta * speed)) <= 0;
    }

    private void updateXBounds(float delta) {
        if (scrollEnabled) {
            textureRegionBounds1.x += delta * speed;
            textureRegionBounds2.x += delta * speed;
        }

    }

    private void resetBounds() {
        textureRegionBounds1 = textureRegionBounds2;
        textureRegionBounds2 = new Rectangle(Constants.APP_WIDTH, 0, Constants.APP_WIDTH, Constants.APP_HEIGHT);
    }

    public void setScrollDisabled(boolean scrollEnabled) {
        this.scrollEnabled = scrollEnabled;
    }

    // Receives an integer and maps it to the String highScore in prefs
    public static void setHighScore(float val) {
        prefs.putFloat("highScore", val);
        prefs.flush();
    }

    // Retrieves the current high score
    public static float getHighScore() {
        return prefs.getFloat("highScore");
    }

    public static void checkScore(float score){
        if (score > getHighScore()) {
            setHighScore(score);
        }
    }
}