package dev.hust.funnyfarm.entities.creatures.animals;

import dev.hust.funnyfarm.Handler;
import dev.hust.funnyfarm.entities.creatures.FoodType;
import dev.hust.funnyfarm.gfx.Animation;
import dev.hust.funnyfarm.gfx.Assets;
import dev.hust.funnyfarm.tiles.EnvironmentType;


public class Fish extends Animal implements Swimmable {

	private static EnvironmentType[] environments = {EnvironmentType.WATER_ENVIRONMENT};
	public EnvironmentType[] getEnvironments() {
		return environments;
	}
	
	
	public Fish(Handler handler, float x, float y) {
		super(handler, x, y, DEFAULT_CREATURE_WIDTH, DEFAULT_CREATURE_HEIGHT);
		
		setBounds(0,0,64,64);
		
		// Body update info
		setHealthLostPerTick(0.01);
		setFoodLostPerTick(0.015);
		setWaterLostPerTick(0.005);
		
		//Animations
		Animation animDown = new Animation(500, Assets.fish_down);
		Animation animUp = new Animation(500, Assets.fish_up);
		Animation animLeft = new Animation(500, Assets.fish_left);
		Animation animRight = new Animation(500, Assets.fish_right);
		Animation animSleep = new Animation(500, Assets.fish_sleep);
		Animation animDead = new Animation(500, Assets.fish_dead);
		
		super.setAnimations(animDown, animUp, animLeft, animRight, animSleep, animDead);
		
		setFoodType(new FoodType("fishfood"));
	}
	

	@Override
	public void tick() {
		
		// This method should be at first in tick(): updateBodyStatus();
		updateBodyStatus();
		
		if (!isLiving()) return;
		
		sleep();
		
		//Animations
		swim();
		
	}
	
	public void swim() {
		if (!isSleeping()) {
			//Animations
			getAnimDown().tick();
			getAnimUp().tick();
			getAnimRight().tick();
			getAnimLeft().tick();
			
			//Movement
			getMove();
			move();
		}
	}


	@Override
	public long getSleepTime() {
		return 500;
	}


	@Override
	public long getTimeBetweenSleeps() {
		return 2000;
	}
	

}
