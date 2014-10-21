package me.montecode.games.runningmonster.actors;

import com.badlogic.gdx.physics.box2d.Body;

import me.montecode.games.runningmonster.box2d.GroundUserData;

public class Ground extends GameActor{
    public Ground(Body body){
        super(body);
    }

    @Override
    public GroundUserData getUserData(){
        return (GroundUserData) userData;
    }
}
