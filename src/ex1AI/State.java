package ex1AI;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;;

public class State {
	
	public char parent;
	public State cameFrom;
	public int size;
	public Node[][] currentState;
	public int[] arrayOfNums;
	public List<State> succesors = new ArrayList<>();
	public int depth;

	
	public State(int[] arrayOfNums, char parent, int size, State state) {
		this.parent = parent;
		this.size = size;
		this.cameFrom = state;
		this.arrayOfNums = arrayOfNums;
		this.currentState = new Node[size][size];
	}
	
	/**
	 * con't for IDS Algorythm
	 * @param arrayOfNums is the array that represents the state
	 * @param parent
	 * @param size
	 * @param state
	 * @param depth
	 */
	public State(int[] arrayOfNums, char parent, int size, State state,int depth) {
		this.parent = parent;
		this.size = size;
		this.cameFrom = state;
		this.arrayOfNums = arrayOfNums;
		this.currentState = new Node[size][size];
		this.depth = depth;
	}
	
	@Override
	/**
	 * checks if the both states that are the same have the same address
	 */
	public int hashCode() {
		return Arrays.hashCode(arrayOfNums);
	}
	
	@Override
	public boolean equals(Object other) {
		if(other instanceof State) {
			return Arrays.equals(this.arrayOfNums, ((State) other).arrayOfNums);
		}
		return false;
	}
	
	public int[] getArrayOfNums() {
		return arrayOfNums;
	}

	public void setArrayOfNums(int[] arrayOfNums) {
		this.arrayOfNums = arrayOfNums;
	}

	/**
	 * makes the state as a matrix to understand where is the zero.
	 */
	public void makeCurrentState() {
		int len = this.arrayOfNums.length;
		int k = 0;

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (k < len) {
					this.currentState[i][j] = new Node(i, j, this.arrayOfNums[k]);
					k++;
				}
			}
		}
	}

	/**
	 * finds the node that is the zero cell to help finding the successors.
	 * @return the zero node.
	 */
	public Node findZeroCell() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (this.currentState[i][j].getVal() == 0) {
					return this.currentState[i][j];
				}
			}
		}
		return null;
	}


	/**
	 * make four states according to the zero node.
	 */
	public void MakeSuccesors() {
		succesors.clear();
		makeCurrentState();
		Node node = findZeroCell();
		Node other;
		int[] newArray;
		
		// check up
		if (node.x - 1 >= 0) {
			other = new Node(node.x - 1, node.y, this.currentState[node.x - 1][node.y].getVal());
			newArray = matrixToArray(node, other);
			succesors.add(new State(newArray, 'D', size, this,this.depth+1));
		}

		// check down
		if (node.x + 1 <= size - 1) {
			other = new Node(node.x + 1, node.y, this.currentState[node.x + 1][node.y].getVal());
			newArray = matrixToArray(node, other);
			succesors.add(new State(newArray, 'U', size, this,this.depth+1));
		}
		// check left
		if (node.y - 1 >= 0) {
			other = new Node(node.x, node.y - 1, this.currentState[node.x][node.y - 1].getVal());
			newArray = matrixToArray(node, other);
			succesors.add(new State(newArray, 'R', size, this,this.depth+1));
		}
		// check right
		if (node.y + 1 <= size - 1) {
			other = new Node(node.x, node.y + 1, this.currentState[node.x][node.y + 1].getVal());
			newArray = matrixToArray(node, other);
			succesors.add(new State(newArray, 'L', size, this,this.depth+1));
		}

	}


	/**
	 * makes the matrix as an array again after switching with the successor that it gets.
	 * @param efes is the zero node
	 * @param other is the successor
	 * @return the new state - successor
	 */
	public int[] matrixToArray(Node efes, Node other) {
		int[] newArray = new int[this.arrayOfNums.length];
		int k = 0;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (k < newArray.length) {
					if (this.currentState[i][j].val == efes.val) {
						newArray[k] = other.getVal();
					} else if (this.currentState[i][j].val == other.val) {
						newArray[k] = efes.getVal();
					} else {
						newArray[k] = this.currentState[i][j].getVal();
					}
					k++;
				}
			}
		}
		return newArray;
	}

	public List<State> getSuccesors() {
		return succesors;
	}

	public void setSuccesors(List<State> succesors) {
		this.succesors = succesors;
	}

	public char getParent() {
		return parent;
	}

	public void setParent(char parent) {
		this.parent = parent;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

}
