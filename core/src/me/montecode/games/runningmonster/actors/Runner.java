package me.montecode.games.runningmonster.actors;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.World;

import me.montecode.games.runningmonster.box2d.RunnerUserData;
import me.montecode.games.runningmonster.helpers.AssetLoader;
import me.montecode.games.runningmonster.utils.Constants;

public class Runner extends GameActor {

    private boolean jumping;
    private boolean dodging;
    private boolean hit;
    private float stateTime;

	Filter filter = new Filter();

    public Runner(Body body) {
        super(body);

        stateTime = 0f;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);

        float x = 2f; //screenRectangle.x - (screenRectangle.width * 0.1f);
        float y = 30f;
        float y1 = screenRectangle.y;
        float x1 = screenRectangle.x - (screenRectangle.width * 0.1f);
        float width = screenRectangle.width * 1.2f;

        if (dodging) {
            stateTime += Gdx.graphics.getDeltaTime();
            batch.draw(AssetLoader.foxDodgeAnimation.getKeyFrame(stateTime, true), x, y - 2f, width, screenRectangle.height * 3f / 4f);
        } else if (hit) {
            // When he's hit we also want to apply rotation if the body has been rotated
            batch.draw(AssetLoader.foxHit, x1, y1, width * 0.5f, screenRectangle.height * 0.5f, width, screenRectangle.height, 1f,
                    1f, (float) Math.toDegrees(body.getAngle()));
        } else if (jumping) {
            stateTime += Gdx.graphics.getDeltaTime();
            batch.draw(AssetLoader.foxFlyAnimation.getKeyFrame(stateTime, true), x, y1, width, screenRectangle.height);
        }
        else {
            // Running
            stateTime += Gdx.graphics.getDeltaTime();
            batch.draw(AssetLoader.foxRunAnimation.getKeyFrame(stateTime, true), x, y, width, screenRectangle.height);
        }
        
        

    }


    @Override
    public RunnerUserData getUserData() {
        return (RunnerUserData) userData;
    }

    public void jump() {
        if (!jumping && !dodging && !hit) {
            body.applyLinearImpulse(getUserData().getJumpingLinearImpulse(), body.getWorldCenter(), true);
            jumping = true;
        }
    }

    public void landed() {
        jumping = false;
    }

    public void dodge() {
        if (!jumping && !hit) {
			filter.categoryBits = 2;
			filter.maskBits = 4;
        	body.getFixtureList().get(0).setFilterData(filter);
        	
            dodging = true;
        }
    }

    public void stopDodge() {
        dodging = false;
        if (!hit) {
        	filter.categoryBits = 1;
			filter.maskBits = 1;
			body.getFixtureList().get(0).setFilterData(filter);
        }
    }

    public boolean isDodging() {
        return dodging;
    }

    public void hit() {
        body.applyAngularImpulse(getUserData().getHitAngularImpulse(), true);
        body.applyLinearImpulse(getUserData().getJumpingLinearImpulse(), body.getWorldCenter(), true);
        hit = true;
    }

    public boolean isHit() {
        return hit;
    }

}
