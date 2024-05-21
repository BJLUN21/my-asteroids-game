package dk.sdu.mmmi.cbse.enemysystem;

import dk.sdu.mmmi.cbse.common.data.Entity;

public class Enemy extends Entity {

	private double timeToReload = 0.1;
	private double lastFireTime = 0;

	public Enemy() {
		setIsEnemy(true);
		setIsFriendly(false);
	}

	public boolean isReadyToFire() {
		long currentTime = System.currentTimeMillis();
		double elapsedTimeSinceLastShot = (currentTime - getLastFireTime()) / 1000.0; // Convert milliseconds to seconds
		return elapsedTimeSinceLastShot >= getTimeToReload();
	}

	public double getTimeToReload() {
		return timeToReload;
	}

	public void setTimeToReload(double cooldown) {
		this.timeToReload = cooldown;
	}

	public double getLastFireTime() {
		return lastFireTime;
	}

	public void setLastFireTime(double time) {
		this.lastFireTime = time;
	}
}
