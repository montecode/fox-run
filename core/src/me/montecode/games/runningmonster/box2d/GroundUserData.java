package me.montecode.games.runningmonster.box2d;

import me.montecode.games.runningmonster.enums.UserDataType;

public class GroundUserData extends UserData {

    public GroundUserData(float width, float height){
        super(width, height);
        userDataType = UserDataType.GROUND;
    }

}
