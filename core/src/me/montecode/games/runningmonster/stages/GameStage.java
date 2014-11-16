package me.montecode.games.runningmonster.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScalingViewport;

import me.montecode.games.runningmonster.RunningMonsterGame;
import me.montecode.games.runningmonster.actors.Background;
import me.montecode.games.runningmonster.actors.Enemy;
import me.montecode.games.runningmonster.actors.Ground;
import me.montecode.games.runningmonster.actors.Runner;
import me.montecode.games.runningmonster.box2d.EnemyUserData;
import me.montecode.games.runningmonster.enums.EnemyType;
import me.montecode.games.runningmonster.screens.StartGameScreen;
import me.montecode.games.runningmonster.utils.BodyUtils;
import me.montecode.games.runningmonster.utils.Constants;
import me.montecode.games.runningmonster.utils.WorldUtils;


public class GameStage extends Stage implements ContactListener {
    private static final int VIEWPORT_WIDTH = Constants.APP_WIDTH;
    private static final int VIEWPORT_HEIGHT = Constants.APP_HEIGHT;
    private  RunningMonsterGame game;

    private World world;
    private Array<Body> bodies;
    private Ground ground;
    private Runner runner;
    private Runner runner2;
    private Background background;
    float runTime;


    private final float TIME_STEP = 1 / 300f;
    private float accumulator = 0f;

    private OrthographicCamera camera;
    private Box2DDebugRenderer renderer;

    private Rectangle screenRightSide;
    private Rectangle screenLeftSide;

    private Vector3 touchPoint;
    private boolean scrollEnabled;
    
    private Array<Enemy> enemies;
    //private Enemy[] enemies = new Enemy[]{};

    public GameStage(RunningMonsterGame game) {
        super(new ScalingViewport(Scaling.stretch, VIEWPORT_WIDTH, VIEWPORT_HEIGHT,
                new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT)));
        this.game = game;
        setUpWorld();
        renderer = new Box2DDebugRenderer();
        bodies = new Array<Body>(world.getBodyCount());
        setupCamera();
        setupTouchControlAreas();
    }

    private void resetGame() {
        setUpWorld();
        renderer = new Box2DDebugRenderer();
        setupCamera();
        Enemy.resetSpeed();
    }


    private void setUpWorld() {
        scrollEnabled = true;
        world = WorldUtils.createWorld();
        world.setContactListener(this);
        setUpBackground();
        setUpGround();
        setUpRunner();
        createEnemies();
        setRandomEnemy();
    }

    private void setUpGround() {
        ground = new Ground(WorldUtils.createGround(world));
        addActor(ground);
    }


    private void setUpRunner() {
        runner = new Runner(WorldUtils.createRunner(world)[1]);
        //runner2 = new Runner(WorldUtils.createRunner(world)[1]);
        addActor(runner);
        //addActor(runner2);
    }

    private void setupCamera() {
        camera = new OrthographicCamera(VIEWPORT_WIDTH / 10, VIEWPORT_HEIGHT / 10);
        camera.position.set(camera.viewportWidth / 4, camera.viewportHeight / 4, 0f);
        camera.update();
    }

    private void setupTouchControlAreas() {
        touchPoint = new Vector3();
        screenLeftSide = new Rectangle(0, 0, getCamera().viewportWidth / 2, getCamera().viewportHeight);
        screenRightSide = new Rectangle(getCamera().viewportWidth / 2, 0, getCamera().viewportWidth / 2,
                getCamera().viewportHeight);
        Gdx.input.setInputProcessor(this);
    }


    @Override
    public void act(float delta) {
        super.act(delta);
        runTime += delta;
        world.getBodies(bodies);

        for (Body body : bodies) {
        	update(body);
//        	Gdx.app.log("GameStage", body.getLinearVelocity().toString());
        }

        accumulator += delta;

        while (accumulator >= delta) {
            world.step(TIME_STEP, 6, 2);
            accumulator -= TIME_STEP;
        }
        

    }

    private void update(Body body) {
    	
    	if (runner.isHit()) {
            stopScrolling();
        } else {
            if (!BodyUtils.bodyInBounds(body) && BodyUtils.bodyIsEnemy(body)) {
            	
            	EnemyUserData enemyUserData = (EnemyUserData) body.getUserData();
                enemyUserData.setLinearVelocity(new Vector2(0, 0));
                
                body.setTransform(new Vector2(Constants.ENEMY_X, body.getPosition().y), 0f);
                
                if (BodyUtils.bodyIsEnemy(body) && !runner.isHit()) {
                	
                	setRandomEnemy();
                	
                }
       
            }

        }

    }

    private void stopScrolling() {
        scrollEnabled = false;
        background.setScrollDisabled(false);
    }

    private void createEnemies() {
        Enemy fs = new Enemy(WorldUtils.createEnemy(world, EnemyType.FLYING_SMALL));
        Enemy fw = new Enemy(WorldUtils.createEnemy(world, EnemyType.FLYING_WIDE));
        Enemy rb = new Enemy(WorldUtils.createEnemy(world, EnemyType.RUNNING_BIG));
        Enemy rl = new Enemy(WorldUtils.createEnemy(world, EnemyType.RUNNING_LONG));
        Enemy rs = new Enemy(WorldUtils.createEnemy(world, EnemyType.RUNNING_SMALL));
        Enemy rw = new Enemy(WorldUtils.createEnemy(world, EnemyType.RUNNING_WIDE));
        addActor(fs);
        addActor(fw);
        addActor(rb);
        addActor(rl);
        addActor(rs);
        addActor(rw);
        
        enemies = new Array<Enemy>(6);
        enemies.addAll(fs, fw, rb, rl, rs, rw);
    }
   
    
    public void setRandomEnemy(){
    	
    	//get random enemy from array and set its linear velocity
    	
    	//enemies.random().setDefaultLinearVelocity();
    	Enemy currentEnemy = enemies.random();
        currentEnemy.setLinearVelocity(new Vector2(Constants.ENEMY_LINEAR_VELOCITY.x - currentEnemy.getSpeed() * 10, 0));
    	currentEnemy.setDefaultLinearVelocity();
    }

    @Override
    public void draw() {
        super.draw();
        renderer.render(world, camera.combined);
    }

    @Override
    public boolean keyUp(int keycode) {

        if (keycode == Input.Keys.BACK) {
            game.setScreen(new StartGameScreen(game));
        }

        return true;
    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        translateScreenToWorldCoordinates(x, y);

        if (rightSideTouched(touchPoint.x, touchPoint.y)) {
            runner.jump();
            if (!scrollEnabled) {
                resetGame();
            }
        } else if (leftSideTouched(touchPoint.x, touchPoint.y)) {
            runner.dodge();
            if (!scrollEnabled) {
                resetGame();
            }
        }

        return super.touchDown(x, y, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (runner.isDodging()) {
            runner.stopDodge();
        }
        return super.touchUp(screenX, screenY, pointer, button);
    }


    private boolean rightSideTouched(float x, float y) {
        return screenRightSide.contains(x, y);
    }

    private boolean leftSideTouched(float x, float y) {
        return screenLeftSide.contains(x, y);
    }

    private void translateScreenToWorldCoordinates(int x, int y) {
        getCamera().unproject(touchPoint.set(x, y, 0));
    }

    @Override
    public void beginContact(Contact contact) {

        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();

        if ((BodyUtils.bodyIsRunner(a) && BodyUtils.bodyIsEnemy(b)) ||
                (BodyUtils.bodyIsEnemy(a) && BodyUtils.bodyIsRunner(b))) {
            runner.hit();
        } else if ((BodyUtils.bodyIsRunner(a) && BodyUtils.bodyIsGround(b)) ||
                (BodyUtils.bodyIsGround(a) && BodyUtils.bodyIsRunner(b)) && !runner.isHit()) {
            runner.landed();
        }

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    private void setUpBackground() {
        background = new Background();
        addActor(background);
    }

}
