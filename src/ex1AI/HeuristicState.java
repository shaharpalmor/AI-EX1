package ex1AI;

public class HeuristicState extends State {
	
	private int gValue;
	private int hValue;
	private int fValue;
	private int[][] matrix;

	public HeuristicState(int[] arrayOfNums, char parent, int size, State state) {
		super(arrayOfNums, parent, size, state);
		this.hValue = 0;
		this.fValue = 0;
		this.gValue = 0;
		this.matrix = new int[size][size];
	}

	/**
	 * this function calculates the heuristic function - H using the Manhattan
	 * also known as H2. it checks if its not the goal state and return the total amount of
	 * distances of the cells that are not in the right place.
	 */
	public void calcHeuristic() {
		makeCurrentState();
		initGoalMatrix();// the goal matrix
		int correctX;
		int correctY;

		for (int i = 0; i < super.size; i++) {
			for (int j = 0; j < super.size; j++) {
				int value = this.currentState[i][j].getVal();
				if (value != matrix[i][j] && value != 0) {
					correctX = (value - 1) / size;
					correctY = (value - 1) % size;
				} else {
					continue;
				}
				hValue += (Math.abs(i - correctX) + Math.abs(j - correctY));
			}
		}

	}

	/**
	 * makes the goal matrix to compare each cell to calaculate the h function.
	 */
	public void initGoalMatrix() {
		int numbers = size * size;
		int k = 1;
		for (int i = 0; i < super.size; i++) {
			for (int j = 0; j < super.size; j++) {
				if (k <= numbers) {
					matrix[i][j] = k;
					k++;
				}
			}
		}
		matrix[size - 1][size - 1] = 0;

	}

	/**
	 * calculates the g function and the f total function.
	 */
	public void setFValue() {
		calcHeuristic();

		if (this.cameFrom != null) {
			this.gValue = ((HeuristicState) this.cameFrom).gValue + 1;
			this.fValue = this.hValue + this.gValue;
		} else {
			this.gValue = 0;
		}
	}

	public int getfValue() {
		return fValue;
	}

	public void setfValue(int fValue) {
		this.fValue = fValue;
	}

}
