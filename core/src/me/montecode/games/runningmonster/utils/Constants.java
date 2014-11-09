package me.montecode.games.runningmonster.utils;


import com.badlogic.gdx.math.Vector2;

public class Constants {
    public static final int APP_WIDTH = 800;
    public static final int APP_HEIGHT = 480;
    public static final float WORLD_TO_SCREEN = 32;

    public static final Vector2 WORLD_GRAVITY = new Vector2(0, -10);

    public static final float GROUND_X = 0;
    public static final float GROUND_Y = 0;
    public static final float GROUND_WIDTH = 25f;
    public static final float GROUND_HEIGHT = 2f;
    public static final float GROUND_DENSITY = 0f;

    public static final float RUNNER_X = 2;
    public static final float RUNNER_Y = GROUND_Y + GROUND_HEIGHT;
    public static final float RUNNER_WIDTH = 2f;
    public static final float RUNNER_HEIGHT = 2f;
    public static final float RUNNER_GRAVITY_SCALE = 3f;
    public static float RUNNER_DENSITY = 0.5f;
    public static final float RUNNER_DODGE_X = 2f;
    public static final float RUNNER_DODGE_Y = RUNNER_Y - 2f;
    public static final Vector2 RUNNER_JUMPING_LINEAR_IMPULSE = new Vector2(0, 26f);
    public static final float RUNNER_HIT_ANGULAR_IMPULSE = 40f;
    public static final float RUNNER_MAX_SPEED = 10f;

    public static final float ENEMY_X = 30f;
    public static final float ENEMY_DENSITY = RUNNER_DENSITY;
    public static final float RUNNING_SHORT_ENEMY_Y = 1.5f;
    public static final float RUNNING_LONG_ENEMY_Y = 2f;
    public static final float FLYING_ENEMY_Y = 3.5f;
    public static final Vector2 ENEMY_LINEAR_VELOCITY = new Vector2(-10f, 0);

    public static final String BACKGROUND_IMAGE_PATH = "background.png";
    public static final String GROUND_IMAGE_PATH = "ground.png";

    public static final String CHARACTERS_ATLAS_PATH = "characters.txt";
    public static final String[] RUNNER_RUNNING_REGION_NAMES = new String[] {"fox_run1", "fox_run2", "fox_run3", "fox_run4", "fox_run5"};
    public static final String[] RUNNER_DODGING_REGION_NAMES = new String[] {"fox_dodge1", "fox_dodge2"};
    public static final String RUNNER_HIT_REGION_NAME = "fox_hit";
    public static final String[] RUNNER_JUMPING_REGION_NAMES = new String[] {"fox_fly1", "fox_fly2"};

    public static final String[] RUNNING_SMALL_ENEMY_REGION_NAMES = new String[] {"rock_roll1", "rock_roll2"};
    public static final String[] RUNNING_LONG_ENEMY_REGION_NAMES = new String[] {"wall"};
    public static final String[] RUNNING_BIG_ENEMY_REGION_NAMES = new String[] {"wall"};
    public static final String[] RUNNING_WIDE_ENEMY_REGION_NAMES = new String[] {"wood_roll1", "wood_roll2"};
    public static final String[] FLYING_SMALL_ENEMY_REGION_NAMES = new String[] {"droid_fly1", "droid_fly1", "droid_fly2", "droid_fly2"};
    public static final String[] FLYING_WIDE_ENEMY_REGION_NAMES = new String[] {"rocket_fly1", "rocket_fly2"};
}
