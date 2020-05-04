package puzzleGame;

import java.util.ArrayList;


public class Node{
	public Node parent;
	public char lastMovment;
	public ArrayList <Integer> gameBoard;
	public int zeroPlace;
	public int depth;
	//in Astar algorithm only
	public int totalCostMenhaten;
	public double totalCostEuclidien;
	public ArrayList <Node>childs = new ArrayList<Node>();
	@SuppressWarnings({ "rawtypes", "unchecked" })
	//initial constructor for creating root only
	public Node(ArrayList input){
		gameBoard=new ArrayList<Integer>();
		gameBoard.addAll(input);
		zeroPlace = gameBoard.indexOf(0);	
		lastMovment='F';
		depth=0;
		}
	public Node(Node state){
		gameBoard=new ArrayList<Integer>();
		gameBoard.addAll(state.gameBoard);
		zeroPlace = state.zeroPlace;
	}
}
