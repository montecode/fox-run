package me.montecode.games.runningmonster.utils;


import com.badlogic.gdx.physics.box2d.Body;

import me.montecode.games.runningmonster.box2d.UserData;
import me.montecode.games.runningmonster.enums.UserDataType;

public class BodyUtils {

    public static boolean bodyIsRunner(Body body){
        UserData userData = (UserData) body.getUserData();

        return userData != null && userData.getUserDataType() == UserDataType.RUNNER;
    }

    public static boolean bodyIsGround(Body body){
        UserData userData = (UserData) body.getUserData();
        return userData != null && userData.getUserDataType() == UserDataType.GROUND;
    }

}
