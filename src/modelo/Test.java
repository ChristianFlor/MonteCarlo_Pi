package modelo;

import java.io.Serializable;

public class Test implements Serializable {

	private String id;
	private int seed;
	private int points;

	
	public Test(String id, int seed, int points) {
		super();
		this.id = id;
		this.seed = seed;
		this.points = points;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getSeed() {
		return seed;
	}

	public void setSeed(int seed) {
		this.seed = seed;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

}
