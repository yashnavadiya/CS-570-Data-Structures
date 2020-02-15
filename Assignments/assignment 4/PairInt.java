package Maze;

public class PairInt {

	// Data Fields
	private int x;
	private int y;

	// Constructors
	public PairInt(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// Class Methods
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean equals(Object p) {
		if (p instanceof PairInt) {
			PairInt temp = (PairInt) p;
			return (this.x == temp.getX() && this.y == temp.getY());
		} else {
			return false;
		}
	}

	public String toString() {
		return "(" + this.x + ", " + this.y + ")";
	}

	public PairInt copy() {
		return new PairInt(this.x, this.y);
	}

}
