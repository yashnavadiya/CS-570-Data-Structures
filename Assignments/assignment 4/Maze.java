package Maze;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Class that solves maze problems with backtracking.
 * 
 * @author Koffman and Wolfgang
 **/
public class Maze implements GridColors {

	/** The maze */
	private TwoDimGrid maze;

	public Maze(TwoDimGrid m) {
		maze = m;
	}

	/** Wrapper method. */
	public boolean findMazePath() {

		return findMazePath(0, 0); // (0, 0) is the start point.
	}

	/**
	 * Attempts to find a path through point (x, y).
	 * 
	 * @pre Possible path cells are in BACKGROUND color; barrier cells are in
	 *      ABNORMAL color.
	 * @post If a path is found, all cells on it are set to the PATH color; all
	 *       cells that were visited but are not on the path are in the TEMPORARY
	 *       color.
	 * @param x The x-coordinate of current point
	 * @param y The y-coordinate of current point
	 * @return If a path through (x, y) is found, true; otherwise, false
	 */
	public boolean findMazePath(int x, int y) {
		// COMPLETE HERE FOR PROBLEM 1
		if (x >= 0 && y >= 0 && x < maze.getNCols() && y < maze.getNRows() && maze.getColor(x, y) == NON_BACKGROUND) {
			if (x == (maze.getNCols() - 1) && y == (maze.getNRows() - 1)) {
				maze.recolor(x, y, PATH);
				return true;
			} else {
				maze.recolor(x, y, PATH);
				if (findMazePath(x - 1, y) || findMazePath(x, y + 1) || findMazePath(x + 1, y)
						|| findMazePath(x, y - 1)) {
					return true;
				} else {
					maze.recolor(x, y, TEMPORARY);
					return false;
				}
			}
		} else {
			return false;
		}
	}

	// ADD METHOD FOR PROBLEM 2 HERE
	public void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace) {
		if (y > maze.getNRows() - 1 || x > maze.getNCols() - 1 || x < 0 || y < 0 || maze.getColor(x, y) == BACKGROUND
				|| maze.getColor(x, y) == TEMPORARY) {
			return;
		} else if (y == maze.getNRows() - 1 && x == maze.getNCols() - 1) {
			PairInt tmp = new PairInt(x, y);
			trace.push(tmp);
			ArrayList<PairInt> temporary = new ArrayList<PairInt>();
			temporary.addAll(trace);
			result.add(temporary);
			trace.pop();
			maze.recolor(x, y, NON_BACKGROUND);
			return;
		} else {
			PairInt tmp = new PairInt(x, y);
			trace.push(tmp);
			maze.recolor(x, y, TEMPORARY);
			this.findMazePathStackBased(x + 1, y, result, trace);
			this.findMazePathStackBased(x - 1, y, result, trace);
			this.findMazePathStackBased(x, y + 1, result, trace);
			this.findMazePathStackBased(x, y - 1, result, trace);
			maze.recolor(x, y, NON_BACKGROUND);
			trace.pop();
			return;
		}
	}

	public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x, int y) {
		ArrayList<ArrayList<PairInt>> result = new ArrayList<>();
		Stack<PairInt> trace = new Stack<>();
		findMazePathStackBased(0, 0, result, trace);
		if (result.size() == 0) {
			ArrayList<PairInt> temporary = new ArrayList<PairInt>();
			result.add(temporary);
		}
		return result;
	}

	// ADD METHOD FOR PROBLEM 3 HERE
	public ArrayList<PairInt> findMazePathMin(int x, int y) {
		ArrayList<ArrayList<PairInt>> result = new ArrayList<ArrayList<PairInt>>();
		result = findAllMazePaths(x, y);
		int array[] = new int[result.size()];
		for (int i = 0; i < result.size(); i++) {
			array[i] = result.get(i).size();
		}
		int min = array[0];
		int shortestpath = 0;
		for (int i = 1; i < array.length; i++) {
			if (array[i] < min) {
				min = array[i];
				shortestpath = i;
			}
		}
		return result.get(shortestpath);
	}

	/* <exercise chapter="5" section="6" type="programming" number="2"> */
	public void resetTemp() {
		maze.recolor(TEMPORARY, BACKGROUND);
	}
	/* </exercise> */

	/* <exercise chapter="5" section="6" type="programming" number="3"> */
	public void restore() {
		resetTemp();
		maze.recolor(PATH, BACKGROUND);
		maze.recolor(NON_BACKGROUND, BACKGROUND);
	}
	/* </exercise> */
}
/* </listing> */
