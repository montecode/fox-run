package me.montecode.games.runningmonster.box2d;

import me.montecode.games.runningmonster.enums.UserDataType;

public abstract class UserData {

    protected UserDataType userDataType;

    public UserData(){

    }

    public UserDataType getUserDataType(){
        return userDataType;
    }

}
