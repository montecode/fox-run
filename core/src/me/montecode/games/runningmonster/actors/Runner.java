package me.montecode.games.runningmonster.actors;


import com.badlogic.gdx.physics.box2d.Body;
import me.montecode.games.runningmonster.box2d.RunnerUserData;

public class Runner extends GameActor{

    private boolean jumping;
    private boolean dodging;
    private boolean hit;

    public Runner(Body body){
        super(body);
    }

    @Override
    public RunnerUserData getUserData(){
        return (RunnerUserData) userData;
    }

    public void jump(){
        if(!(jumping || dodging || hit)){
            body.applyLinearImpulse(getUserData().getJumpingLinearImpulse(), body.getWorldCenter(), true);
            jumping = true;
        }
    }

    public void landed(){
        jumping = false;
    }

    public void dodge(){
        if(!jumping || hit){
            body.setTransform(getUserData().getDodgePosition(), getUserData().getDodgeAngle());
            dodging = true;
        }
    }

    public void stopDodge(){
        dodging = false;
        if(!hit) {
            body.setTransform(getUserData().getRunningPosition(), 0f);
        }
    }

    public boolean isDodging(){
        return dodging;
    }

    public void hit(){
        body.applyAngularImpulse(getUserData().getHitAngularImpulse(), true);
        hit = true;
    }

    public boolean isHit(){
        return hit;
    }

}
