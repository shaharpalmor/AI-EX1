package ex1AI;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

public class AStar_Algorithm extends Algo {
	
	private PriorityQueue<HeuristicState> pQueue;
	private HashSet<State> closeList;
	private int[] initialState;
	private String[] goalState;
	private int size;
	private Comparator<HeuristicState> HeuristicComp;

	public AStar_Algorithm(int[] initial, String[] goal, int size) {
		this.goalState = goal;
		this.size = size;
		this.initialState = initial;
		this.closeList = new HashSet<>();
		this.HeuristicComp = new Comparator<HeuristicState>() {
			@Override
			public int compare(HeuristicState state1, HeuristicState state2) {
				return (state1.getfValue() - state2.getfValue());
			}
		};
		this.pQueue = new PriorityQueue<HeuristicState>(HeuristicComp);
	}

	@Override
	public Solution search() {
		int nodes = 1;
		String backTrace;
		HeuristicState initial = new HeuristicState(this.initialState, '0', this.size, null);
		pQueue.add(initial);

		while (!pQueue.isEmpty()) {
			HeuristicState current = pQueue.poll();
			if (this.compareStates(current.getArrayOfNums())) {
				backTrace = wayTo(current);
				int len = 1*backTrace.length();
				//return new Solution(this.closeList.size(), backTrace, current.getfValue());
				return new Solution(nodes, backTrace, len);
			}
			this.closeList.add(current);
			current.MakeSuccesors();
			for (State s : current.succesors) {
				nodes+=1;
				HeuristicState hState = new HeuristicState(s.arrayOfNums, s.parent, s.size, current);
				hState.setFValue();
				//if (!this.closeList.contains(hState)) {
					this.pQueue.add(hState);
				//}
			}
		}
		System.out.println("wrong 1");
		return null;
	}

	
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
