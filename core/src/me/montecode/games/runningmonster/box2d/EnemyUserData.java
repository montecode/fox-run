package me.montecode.games.runningmonster.box2d;


import com.badlogic.gdx.math.Vector2;

import me.montecode.games.runningmonster.enums.UserDataType;
import me.montecode.games.runningmonster.utils.Constants;

public class EnemyUserData extends UserData{

    private Vector2 linearVelocity;
    private String[] textureRegions;

    public EnemyUserData(float width, float height, String[] textureRegions){
        super(width, height);
        userDataType = UserDataType.ENEMY;
        linearVelocity = new Vector2(0, 0);
        this.textureRegions = textureRegions;
    }

    public void setLinearVelocity(Vector2 linearVelocity){
        this.linearVelocity = linearVelocity;
    }

    public Vector2 getLinearVelocity(){
        return linearVelocity;
    }

    public String[] getTextureRegions(){
        return textureRegions;
    }
}
