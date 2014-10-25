package me.montecode.games.runningmonster.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.text.DecimalFormat;

import me.montecode.games.runningmonster.utils.Constants;

public class Background extends Actor {

    private final TextureRegion textureRegion;
    private Rectangle textureRegionBounds1;
    private Rectangle textureRegionBounds2;
    private int speed = 100;
    private BitmapFont font = new BitmapFont();
    float runTime;
    float lastRunTime;
    DecimalFormat decimalFormat = new DecimalFormat("###.##");

    public Background() {
        textureRegion = new TextureRegion(new Texture(Gdx.files.internal(Constants.BACKGROUND_IMAGE_PATH)));
        textureRegionBounds1 = new Rectangle(0 - Constants.APP_WIDTH / 2, 0, Constants.APP_WIDTH, Constants.APP_HEIGHT);
        textureRegionBounds2 = new Rectangle(Constants.APP_WIDTH / 2, 0, Constants.APP_WIDTH, Constants.APP_HEIGHT);
    }

    @Override
    public void act(float delta) {
        runTime += delta*2;
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
        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();
        font.setColor(Color.BLACK);
        font.draw(batch, "FPS:" + Gdx.graphics.getFramesPerSecond(),600 , 450);// Gdx.graphics.getWidth() / 1.5f, Gdx.graphics.getHeight()/2);
       if(runTime>lastRunTime+0.2){
           lastRunTime = runTime;
           font.draw(batch,String.valueOf(decimalFormat.format(runTime)) + " m", 500,450);// Gdx.graphics.getWidth()/6, Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/4);
       }
      else{
           font.draw(batch,String.valueOf(decimalFormat.format(lastRunTime)) + " m", 500,450);// Gdx.graphics.getWidth()/6, Gdx.graphics.getHeight()-Gdx.graphics.getHeight()/4);
       }

//        Gdx.app.log("SCREENDIMENSIONS", " height " + String.valueOf(Gdx.graphics.getHeight()) + " width " + String.valueOf(Gdx.graphics.getWidth()) );

    }

    private boolean leftBoundsReached(float delta) {
        return (textureRegionBounds2.x - (delta * speed)) <= 0;
    }

    private void updateXBounds(float delta) {
        textureRegionBounds1.x += delta * speed;
        textureRegionBounds2.x += delta * speed;
    }

    private void resetBounds() {
        textureRegionBounds1 = textureRegionBounds2;
        textureRegionBounds2 = new Rectangle(Constants.APP_WIDTH, 0, Constants.APP_WIDTH, Constants.APP_HEIGHT);
    }

}