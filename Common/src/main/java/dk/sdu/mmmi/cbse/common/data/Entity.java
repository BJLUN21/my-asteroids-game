package dk.sdu.mmmi.cbse.common.data;


import java.io.Serializable;
import java.util.UUID;

public class Entity implements Serializable {
	private final UUID ID = UUID.randomUUID();

	private double[] polygonCoordinates;
	private double x;
	private double y;
	private double rotation;
	private int life;

	private float[] shapeX = new float[4];
	private float[] shapeY = new float[4];
	private float radius;

	private boolean isPlayer = false;
	private boolean isEnemy = false;
	private boolean isAsteroid = false;
	private boolean isSplit = false;
	private boolean isBullet = false;
	private boolean isFriendly = false;

	public float getRadius() {
		return radius;
	}

	public void setRadius(float r) {
		this.radius = r;
	}

	public String getID() {
		return ID.toString();
	}
	
	public boolean getIsPlayer() {
		return isPlayer;
	}
	
	public void setIsPlayer(boolean isPlayer) {
		this.isPlayer = isPlayer;
	}

	public boolean getIsEnemy() {
		return isEnemy;
	}

	public void setIsEnemy(boolean isEnemy) {
		this.isEnemy = isEnemy;
	}

	public boolean getIsAsteroid() {
		return isAsteroid;
	}

	public void setIsAsteroid(boolean isAsteroid) {
		this.isAsteroid = isAsteroid;
	}

	public boolean getIsSplit() {
		return isSplit;
	}

	public void setIsSplit(boolean isSplit) {
		this.isSplit = isSplit;
	}

	public boolean getIsBullet() {
		return isBullet;
	}

	public void setIsBullet(boolean isBullet) {
		this.isBullet = isBullet;
	}

	public boolean getIsFriendly() {
		return isFriendly;
	}

	public void setIsFriendly(boolean isFriendly) {
		this.isFriendly = isFriendly;
	}


	public void setPolygonCoordinates(double... coordinates ) {
		this.polygonCoordinates = coordinates;
	}

	public double[] getPolygonCoordinates() {
		return polygonCoordinates;
	}


	public void setX(double x) {
		this.x =x;
	}

	public double getX() {
		return x;
	}


	public void setY(double y) {
		this.y = y;
	}

	public double getY() {
		return y;
	}

	public void setRotation(double rotation) {
		this.rotation = rotation;
	}

	public double getRotation() {
		return rotation;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public int getLife() {
		return this.life;
	}

	// bullet stuff
	private double cooldownTime = 0.2;
	private double lastShotTime = 0;

	public double getCoolDownTime() {
		return cooldownTime;
	}

	public void setCoolDownTime(double cooldownTime) {
		this.cooldownTime = cooldownTime;
	}

	public double getLastShotTime() {
		return lastShotTime;
	}

	public void setLastShotTime(double lastShotTime) {
		this.lastShotTime = lastShotTime;
	}
}
