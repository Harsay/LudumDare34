package com.harsay.ludumdare34.entities;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.harsay.ludumdare34.Gfx;

public class Enemy extends Entity {
	
	Random rand = new Random();
	
	public Animation walk, run, runHead;
	public float animTime = 0.0f;
	
	float time = 0f;
	float velX = 0;
	float velY = 0;
	
	public int animFace = RIGHT;

	public Enemy(float x, float y) {
		super(x, y, 16, 16, Gfx.frames.get(7));
		walk = new Animation(0.2f, Gfx.frames.get(7), Gfx.frames.get(8), Gfx.frames.get(9), Gfx.frames.get(10));
		run = new Animation(0.1f, Gfx.frames.get(11), Gfx.frames.get(12), Gfx.frames.get(13));
		runHead = new Animation(0.1f, Gfx.frames.get(14), Gfx.frames.get(15), Gfx.frames.get(16));
	}
	
	public void update(float delta) {
		super.update(delta);
		
		if(time <= 0) {
			time = rand.nextFloat()*10f;
			velX = rand.nextInt(400)-200;
			velY = rand.nextInt(400)-200;
		}
		
		time -= delta;
		
		x += velX*delta;
		y += velY*delta;
		
		if(velX < 0) animFace = LEFT;
		else animFace = RIGHT;
		
		Animation curAnim;
		if(Math.abs(velX + velY) >= 500) curAnim = runHead;
		else if(Math.abs(velX + velY) >= 100) curAnim = run;
		else curAnim = walk;
		
		animTime += delta;
		currentTexture = curAnim.getKeyFrame(animTime, true);
	}
	
	public void render(SpriteBatch sb) {
		// i kno
		collisionCircle.set(x + width/2, y + height/2, width/4);
		//
		sb.draw(currentTexture, animFace == LEFT ? x+width : x, y, animFace == LEFT ? -width : width, height);
	}

}
