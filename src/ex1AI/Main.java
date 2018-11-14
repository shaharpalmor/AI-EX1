package ex1AI;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		
		String num1, num2, num3;
		try {

			File f = new File("C:\\Users\\Owner\\git\\repository\\ex1AI\\input3.txt");
			BufferedReader b = new BufferedReader(new FileReader(f));

			String readLine = "";

			num1 = b.readLine(); // algo
			num2 = b.readLine(); // board
			num3 = b.readLine(); // num-list
			String[] arrayOfString = num3.split("-");
			String[] goalState = makeGoalState(arrayOfString.length);
			int boardSize = Integer.parseInt(num2);
			int size = arrayOfString.length;
			int[] arrayOfNum = new int[size];
			for (int i = 0; i < size; i++) {
				arrayOfNum[i] = Integer.parseInt(arrayOfString[i]);
			}

			switch (num1) {
			case "1":
				Algo ids = new IDS_Algorithm(arrayOfNum, goalState, boardSize);
				Solution solution0 = ids.search();
				ids.output(solution0);
				//System.out.println(solution0.cost);
				//System.out.println(solution0.getWayBack());
				//System.out.println(solution0.getNodes());
				break;
			case "2":
				Algo bfs = new BFS_Algorithm(arrayOfNum, goalState, boardSize);
				Solution solution1 = bfs.search();
				bfs.output(solution1);
				//System.out.println(solution1.cost);
				//System.out.println(solution1.getWayBack());
				//System.out.println(solution1.getNodes());
				break;
			case "3":
				Algo aStar= new AStar_Algorithm(arrayOfNum,goalState,boardSize);
				Solution solution2 = aStar.search();
				aStar.output(solution2);
				//System.out.println(solution2.cost);
				//System.out.println(solution2.getWayBack());
				//System.out.println(solution2.getNodes());
				break;
			default:

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String[] makeGoalState(int length) {
		String[] goal = new String[length];
		for (Integer i = 1; i <= length; i++) {
			if (i == length) {
				goal[i - 1] = "0";
				break;
			}
			goal[i - 1] = i.toString();
		}
		return goal;

	}

}
