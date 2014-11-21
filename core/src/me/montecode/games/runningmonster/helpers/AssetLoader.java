package me.montecode.games.runningmonster.helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import me.montecode.games.runningmonster.utils.Constants;

public class AssetLoader {

	public static Texture bg, ground;
	public static TextureRegion wall, foxHit, groundTextureRegion;
	public static TextureRegion[] foxRunFrames, foxDodgeFrames, foxFlyFrames, rockRollFrames,
	woodRollFrames, rocketFlyFrames, droidFlyFrames;
	public static Animation foxRunAnimation, foxDodgeAnimation, foxFlyAnimation, rocketFlyAnimation,
	rockRollAnimation, woodRollAnimation, droidFlyAnimation;
	public static TextureAtlas atlas = new TextureAtlas(Constants.CHARACTERS_ATLAS_PATH);
	
	public static void load() {

		bg = new Texture(Gdx.files.internal("background.png"));
		ground = new Texture(Gdx.files.internal("ground.png"));
		
		groundTextureRegion = new TextureRegion(ground);
		
		wall = atlas.findRegion("wall");
		foxHit = atlas.findRegion("fox_hit");
		
		
		
		TextureRegion[] foxRunFrames = new TextureRegion[Constants.RUNNER_RUNNING_REGION_NAMES.length];
        TextureRegion[] foxDodgeFrames = new TextureRegion[Constants.RUNNER_DODGING_REGION_NAMES.length];
        TextureRegion[] foxFlyFrames = new TextureRegion[Constants.RUNNER_JUMPING_REGION_NAMES.length];
        TextureRegion[] rockRollFrames = new TextureRegion[Constants.RUNNING_SMALL_ENEMY_REGION_NAMES.length];
        TextureRegion[] rocketFlyFrames = new TextureRegion[Constants.FLYING_WIDE_ENEMY_REGION_NAMES.length];
        TextureRegion[] woodRollFrames = new TextureRegion[Constants.RUNNING_WIDE_ENEMY_REGION_NAMES.length];
        TextureRegion[] droidFlyFrames = new TextureRegion[Constants.FLYING_SMALL_ENEMY_REGION_NAMES.length];
        
        for (int i = 0; i < Constants.RUNNER_RUNNING_REGION_NAMES.length; i++) {
            String path = Constants.RUNNER_RUNNING_REGION_NAMES[i];
            foxRunFrames[i] = atlas.findRegion(path);
        }
        for (int i = 0; i < Constants.RUNNER_DODGING_REGION_NAMES.length; i++) {
            String path = Constants.RUNNER_DODGING_REGION_NAMES[i];
            foxDodgeFrames[i] = atlas.findRegion(path);
        }
        for (int i = 0; i < Constants.RUNNER_JUMPING_REGION_NAMES.length; i++) {
            String path = Constants.RUNNER_JUMPING_REGION_NAMES[i];
            foxFlyFrames[i] = atlas.findRegion(path);
        }
        for (int i = 0; i < Constants.RUNNING_SMALL_ENEMY_REGION_NAMES.length; i++) {
            String path = Constants.RUNNING_SMALL_ENEMY_REGION_NAMES[i];
            rockRollFrames[i] = atlas.findRegion(path);
        }
        for (int i = 0; i < Constants.FLYING_WIDE_ENEMY_REGION_NAMES.length; i++) {
            String path = Constants.FLYING_WIDE_ENEMY_REGION_NAMES[i];
            rocketFlyFrames[i] = atlas.findRegion(path);
        }
        for (int i = 0; i < Constants.FLYING_SMALL_ENEMY_REGION_NAMES.length; i++) {
            String path = Constants.FLYING_SMALL_ENEMY_REGION_NAMES[i];
            droidFlyFrames[i] = atlas.findRegion(path);
        }
        for (int i = 0; i < Constants.RUNNING_WIDE_ENEMY_REGION_NAMES.length; i++) {
            String path = Constants.RUNNING_WIDE_ENEMY_REGION_NAMES[i];
            woodRollFrames[i] = atlas.findRegion(path);
        }
        
        
        foxRunAnimation = new Animation(0.1f, foxRunFrames);
        //foxRunAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

        
        foxDodgeAnimation = new Animation(0.1f, foxDodgeFrames);        
        foxFlyAnimation = new Animation(0.1f, foxFlyFrames);        
        rockRollAnimation = new Animation(0.1f, rockRollFrames);       
        rocketFlyAnimation = new Animation(0.1f, rocketFlyFrames);       
        woodRollAnimation = new Animation(0.1f, woodRollFrames);       
        droidFlyAnimation = new Animation(0.1f, droidFlyFrames);
        
    }
	
	public static void dispose(){
		bg.dispose();
		ground.dispose();
		wall.getTexture().dispose();
		foxHit.getTexture().dispose();
	}
	
}
