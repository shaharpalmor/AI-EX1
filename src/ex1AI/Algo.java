package ex1AI;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public abstract class Algo {
	private int[] board;
	private String[] goal;
	private int xSol;
	private String way;

	/**
	 * abstract method that each of the algorithms implements.
	 * @return the soultion including the cost and the way back.
	 */
	public abstract Solution search();

	/**
	 * remake the way to understand what brought to the goal state.
	 * @param goal is the string of the way back.
	 * @return the back trace string.
	 */
	public String wayTo(State goal) {
		State current = goal;
		way = "";

		while (current.cameFrom != null) {
			if (current.parent != '0') {
				way += current.parent;
				current = current.cameFrom;
			}
		}

		return opposite(way);
	}

	/**
	 * the function way back use it to reverse the way back, to get the right way.
	 * @param backTrace is the reverse string to reverse.
	 * @return the right way.
	 */
	private String opposite(String backTrace) {
		String s = "";
		char[] b = new char[backTrace.length()];

		for (int j = 0; j < backTrace.length(); j++) {
			b[j] = backTrace.charAt(j);
		}
		for (int i = backTrace.length() - 1; i >= 0; i--) {
			s += b[i];
		}
		return s.toString();
	}

	/**
	 * write rhe solution to the output file.
	 */
	public void output(Solution solve) throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter("myOutput.txt", "UTF-8");
		writer.print(solve.getCost()+ " " + solve.getWayBack() + " "+ solve.getNodes() );
		//writer.print(xSol + " " + way + " ");
		writer.close();
	}

}
