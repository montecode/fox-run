package me.montecode.games.runningmonster.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.JointDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef;

import me.montecode.games.runningmonster.box2d.EnemyUserData;
import me.montecode.games.runningmonster.box2d.GroundUserData;
import me.montecode.games.runningmonster.box2d.RunnerUserData;
import me.montecode.games.runningmonster.enums.EnemyType;


public class WorldUtils {

    public static World createWorld() {
        return new World(Constants.WORLD_GRAVITY, true);
    }

    public static Body createGround(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(new Vector2(Constants.GROUND_X, Constants.GROUND_Y));
        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Constants.GROUND_WIDTH / 2, Constants.GROUND_HEIGHT / 2);
        body.createFixture(shape, Constants.GROUND_DENSITY);


        body.setUserData(new GroundUserData(Constants.GROUND_WIDTH, Constants.GROUND_HEIGHT));
        shape.dispose();
        return body;
    }

    public static Body[] createRunner(World world) {
    	
        BodyDef topBodyDef = new BodyDef();
        BodyDef botBodyDef = new BodyDef();
        JointDef jointDef = new DistanceJointDef();
        FixtureDef fixtureDef = new FixtureDef();
              
        topBodyDef.type = BodyDef.BodyType.DynamicBody;
        botBodyDef.type = BodyDef.BodyType.DynamicBody;
        
        topBodyDef.position.set(new Vector2(Constants.RUNNER_X / 2, Constants.RUNNER_Y + Constants.RUNNER_HEIGHT));
        botBodyDef.position.set(new Vector2(Constants.RUNNER_X / 2, Constants.RUNNER_Y));
        
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(Constants.RUNNER_WIDTH / 2, Constants.RUNNER_HEIGHT / 4);
        
        
        Body topBody = world.createBody(topBodyDef);
        Body botBody = world.createBody(botBodyDef);
        Body bodies[] = new Body[2];
        
        topBody.setGravityScale(Constants.RUNNER_GRAVITY_SCALE);
        topBody.resetMassData();
        topBody.setUserData(new RunnerUserData(Constants.RUNNER_WIDTH, Constants.RUNNER_HEIGHT));
        
        botBody.setGravityScale(Constants.RUNNER_GRAVITY_SCALE);
        botBody.createFixture(shape, Constants.RUNNER_DENSITY);
        botBody.resetMassData();
        botBody.setUserData(new RunnerUserData(Constants.RUNNER_WIDTH, Constants.RUNNER_HEIGHT));
        
        fixtureDef.shape = shape;
        topBody.createFixture(fixtureDef);
  
        
        jointDef.bodyA = topBody;
        jointDef.bodyB = botBody;
        jointDef.collideConnected = true;
        Joint joint = world.createJoint(jointDef);
        
        bodies[0] = botBody;
        bodies[1] = topBody;
        
        shape.dispose();
        return bodies;
    }

    public static Body createEnemy(World world, EnemyType enemyType) {
        //EnemyType enemyType = RandomUtils.getRandomEnemyType();
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(new Vector2(enemyType.getX(), enemyType.getY()));
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(enemyType.getWidth() / 2, enemyType.getHeight() / 2);
        Body body = world.createBody(bodyDef);
        body.createFixture(shape, enemyType.getDensity());
        body.resetMassData();
        EnemyUserData userData = new EnemyUserData(enemyType.getWidth(), enemyType.getHeight(), enemyType.getRegions(), enemyType.name());
        body.setUserData(userData);
        shape.dispose();
        return body;
    }

}
