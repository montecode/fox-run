package me.montecode.games.runningmonster.actors;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

import me.montecode.games.runningmonster.box2d.EnemyUserData;
import me.montecode.games.runningmonster.utils.Constants;

public class Enemy extends GameActor{

    private Animation animation;
    private float stateTime;
    static float speed = 0;

    public Enemy(Body body){

        super(body);

        TextureAtlas textureAtlas = new TextureAtlas(Constants.CHARACTERS_ATLAS_PATH);
        TextureRegion[] runningFrames = new TextureRegion[getUserData().getTextureRegions().length];
        for (int i = 0; i < getUserData().getTextureRegions().length; i++) {
            String path = getUserData().getTextureRegions()[i];
            runningFrames[i] = textureAtlas.findRegion(path);
        }
        animation = new Animation(0.1f, runningFrames);
        stateTime = 0f;
    }

    @Override
    public EnemyUserData getUserData(){
        return (EnemyUserData) userData;
    }

    @Override
    public void act(float delta){
        super.act(delta);
//        Gdx.app.log("SPEED", String.valueOf(speed));
        if(speed < Constants.RUNNER_MAX_SPEED + 2) {
            speed += delta / 45;
        }
        // Ne može ovo ovako da se mijenja, nekad ti na sred skoka promjeni brzinu pa neka ga sad isključeno
//        else{
//        	speed = 0;
//        }
        float x = getUserData().getLinearVelocity().x;
        if(x != 0) x -= speed;
        float y = getUserData().getLinearVelocity().y;
        body.setLinearVelocity(x, y);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        stateTime += Gdx.graphics.getDeltaTime();
        batch.draw(animation.getKeyFrame(stateTime, true), (screenRectangle.x - (screenRectangle.width * 0.1f)),
                screenRectangle.y, screenRectangle.width * 1.2f, screenRectangle.height * 1.1f);
    }

    public static float getSpeed(){
        return speed;
    }

    public static void resetSpeed(){
        speed = 0;
    }
    
    public void setDefaultLinearVelocity(){
    	getUserData().setLinearVelocity(Constants.ENEMY_LINEAR_VELOCITY);
    }

    public void setLinearVelocity(Vector2 v){
        getUserData().setLinearVelocity(v);
    }
}
