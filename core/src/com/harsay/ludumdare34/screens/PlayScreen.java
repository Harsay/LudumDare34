package com.harsay.ludumdare34.screens;

import java.util.Random;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;
import com.harsay.ludumdare34.levels.Level;

public class PlayScreen extends GameScreen {
	
	Level level;
		
	Random rand = new Random();
	
	public boolean debug = false;
	
	public PlayScreen(Game game) {
		super(game);
		level = new Level(this, game);
	}
	
	public void update(float delta) {
		super.update(delta);
		level.update(delta);
		if(Gdx.input.isKeyJustPressed(Keys.F9)) {
			debug = !debug;
		}

		

			cam.position.lerp(new Vector3(level.player.getCenterX(), level.player.getCenterY(), 0), 10.0f*delta);

			//if(Gdx.input.isKeyJustPressed(Keys.P)) shakeTime = 3.0f;

			float camMinX = cam.viewportWidth/2;
			float camMaxX = level.getWidth() - camMinX;
			float camMinY = cam.viewportHeight/2;
			float camMaxY = level.getHeight() - camMinY;
			
			if(cam.position.x < camMinX) cam.position.x = camMinX;
			else if(cam.position.x > camMaxX) cam.position.x = camMaxX;
			if(cam.position.y < camMinY) cam.position.y = camMinY;
			else if(cam.position.y > camMaxY) cam.position.y = camMaxY;
			
			cameraShake(delta);

	
	}
	
	public void render(float delta) {
		super.render(delta);
		level.render(sb, sr);
		
		sr.begin(ShapeType.Line);
		if(debug)level.debugRender(sr);
		sr.end();
		
	}
	
	public void cameraShake(float delta) {
		if(shakeTime > 0) {
			shakeTime -= delta;
			cam.position.lerp(new Vector3(cam.position.x+rand.nextFloat()*shakeStrength-shakeStrength/2, cam.position.y+rand.nextFloat()*shakeStrength-shakeStrength/2, 0), 10.0f*delta);
			if(shakeTime <=0) shakeTime = 0;
		}
	}

}
