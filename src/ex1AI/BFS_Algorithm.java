package ex1AI;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class BFS_Algorithm extends Algo {
	private Queue<State> queue;
	private HashSet<State> closeList;
	private int[] initialState;
	private String[] goalState;
	int size;

	public BFS_Algorithm(int[] initial, String[] goal,int size) {
		this.goalState = goal;
		this.size = size;
		this.initialState = initial;
		this.queue = new LinkedList<State>();
		this.closeList = new HashSet<>();
	}

	@Override
	public Solution search() {
        int numberOfNodes = 0;
		String backTrace;
		State initial = new State(initialState, '0', this.size, null);
		this.queue.add(initial);
		while (!this.queue.isEmpty()) {
			State current = this.queue.poll();
			numberOfNodes+=1;
			if (this.compareStates(current.getArrayOfNums())) {
				backTrace = wayTo(current);
				//return new Solution(this.closeList.size(),backTrace,0);
				return new Solution(numberOfNodes,backTrace,0);
			}
			this.closeList.add(current);
			current.MakeSuccesors();
			for (State s: current.succesors){
				if(!this.closeList.contains(s))
					this.queue.add(s);
			}
		}
		System.out.println("wrong 2");
		return null;	
	}

	/**
	 * compare the specific state of one of the successor with the goal state to 
	 * see if we made it to the goal. 
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
