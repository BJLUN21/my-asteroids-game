package dk.sdu.mmmi.cbse.common.data;


import java.io.Serializable;
import java.util.UUID;

public class Entity implements Serializable {
	private final UUID ID = UUID.randomUUID();

	private double[] polygonCoordinates;
	private double x;
	private double y;
	private double rotation;
	private float radius;
	private int life;

	private boolean isAsteroid = false;
	private boolean isSplit = false;
	private boolean isPlayer = false;
	private boolean isEnemy = false;
	private boolean isBullet = false;
	private boolean isFriendly = false;


	public String getID() {
		return ID.toString();
	}

	public double[] getPolygonCoordinates() {
		return polygonCoordinates;
	}

	public void setPolygonCoordinates(double... coordinates ) {
		this.polygonCoordinates = coordinates;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getRotation() {
		return rotation;
	}

	public void setRotation(double rotation) {
		this.rotation = rotation;
	}

	public float getRadius() {
		return radius;
	}

	public void setRadius(float r) {
		this.radius = r;
	}

	public int getLife() {
		return this.life;
	}

	public void setLife(int life) {
		this.life = life;
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
}
