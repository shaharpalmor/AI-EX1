package ex1AI;

public class Solution {
	
	private int nodes;
	private String wayBack;
	int cost;
	
	public Solution(int nodes, String wayBack, int cost) {
		super();
		this.nodes = nodes;
		this.wayBack = wayBack;
		this.cost = cost;
	}
	
	public int getNodes() {
		return nodes;
	}
	public void setNodes(int nodes) {
		this.nodes = nodes;
	}
	public String getWayBack() {
		return wayBack;
	}
	public void setWayBack(String wayBack) {
		this.wayBack = wayBack;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	
	
}
