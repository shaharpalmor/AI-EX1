package ex1AI;

import java.util.HashSet;
import java.util.Stack;

public class IDS_Algorithm extends Algo {
	private int[] initialState;
	private String[] goalState;
	private int size;
	// private HashSet<State> openList;
	private Stack<State> iterationList;
	private HashSet<State> closeList;

	public IDS_Algorithm(int[] initial, String[] goal, int size) {
		this.initialState = initial;
		this.goalState = goal;
		this.size = size;
		this.iterationList = new Stack<>();// stack for the dfs
		this.closeList = new HashSet<>();
	}

	/**
	 * here doing the dfs up until the current limit.
	 * 
	 * @param limit       is the depth of the specupic iteration
	 * @param numOfVertex is the total amount of certexes that were developed.
	 * @return the right soultion.
	 */
	// public Solution limitSolver(int limit, int numOfVertex) {
	public Solution limitSolver(int limit) {
		int numOfVertex = 0;
		String backTrace;
		State initial = new State(initialState, '0', this.size, null, 0);
		// developing the zero level
		numOfVertex += 1;
		this.iterationList.push(initial);

		while (!this.iterationList.isEmpty()) {

			State current = this.iterationList.pop();
			if (this.compareStates(current.getArrayOfNums())) {
				backTrace = wayTo(current);
				// adding the goal state to the vertexes that were developed
				numOfVertex += 1;
				// current.depth+=1;
				// return new Solution(this.closeList.size(), backTrace, current.depth);
				return new Solution(numOfVertex, backTrace, current.depth);
			}
			if (!this.closeList.contains(current))
				this.closeList.add(current);
			if (current.getDepth() < limit) {
				numOfVertex += 1;
				current.MakeSuccesors();
				for (State s : current.succesors) {
					this.iterationList.push(s);
				}
			}
		}
		return null;
	}

	@Override
	/**
	 * calls the limit solver each time by the proper limit.
	 */
	public Solution search() {
		// int numOfVertex = 0;
		Solution solver = null;
		int iteration = 0;

		while (solver == null) {
			// solver = limitSolver(iteration, numOfVertex);
			solver = limitSolver(iteration);
			iteration++;
		}
		return solver;
	}

	/**
	 * compare the specific state of one of the successor with the goal state to see
	 * if we made it to the goal.
	 * 
	 * @param currentState is the current state
	 * @return true or false.
	 */
	public boolean compareStates(int[] currentState) {
		int[] goal = new int[currentState.length];
		for (int i = 0; i < currentState.length; i++) {
			goal[i] = Integer.parseInt(this.goalState[i]);
		}
		for (int i = 0; i < currentState.length; i++) {
			if (currentState[i] != goal[i]) {
				return false;
			}
		}
		return true;

	}

}
