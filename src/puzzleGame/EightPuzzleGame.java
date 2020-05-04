package puzzleGame;

import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;


public abstract class EightPuzzleGame {
	//declaration of data fields needed to store final values to be printed
	public static ArrayList<String>pathToGoal;
	public static int costOfPath=0;
	public static int nodesExpanded=0;
	public static int searchDepth=0;
	public static double runningTime=0;	
	public static int zeroIndex;
	public static char lastMov;
	public static Node finalNode;
	//function to check achivment of final goal
	public Boolean checkGoal(Node state,ArrayList <Integer>winingStete ){
		if(state.gameBoard.equals(winingStete)){
			searchDepth=state.depth;
			costOfPath=state.depth;			
			return true;
		}
		return false;
	}
	//function to replace the place of zero with another number according to movement direction
	public Node replace(Node state,int index1,int index2,char lastMove){
		Node newNode = new Node(state);
		newNode.depth=state.depth+1;
		int temp=newNode.gameBoard.get(index1);
		newNode.gameBoard.set(index1, newNode.gameBoard.get(index2));
		newNode.gameBoard.set(index2, temp);
		newNode.zeroPlace=newNode.gameBoard.indexOf(0);
		newNode.lastMovment=lastMove;
		return newNode;
	}
	//function to search presence of child node in frontier or explored lists
	public boolean contains(Queue<Node>searchQueue,Node newChildNode){
		for(Node i:searchQueue){
			if(i.gameBoard.equals(newChildNode.gameBoard))
				return true;
		}
		return false;
	}
	//function to search presence of child node in frontier list of DFS (stack)
	public boolean contains(Stack<Node>stack,Node newChildNode){
		for(Node i:stack){
			if(i.gameBoard.equals(newChildNode.gameBoard))
				return true;
		}
		return false;
	}
	/*L:left
	  R:right
	  U:up
	  D:down
	 */
	//movement to set next movements pirorites according to last movement direction
	public char[] movementDirections(){
		char op[]=null;
		switch(zeroIndex){
			case 0:
				if(lastMov=='L')
					op=new char[]{'D'};
				else if(lastMov=='U')
					op=new char[]{'R'};
				else
					op=new char[]{'D','R'};
				
			break;
			case 1:
				if(lastMov=='U')
					op=new char[]{'L','R'};
				else if(lastMov=='R')
					op=new char[]{'D','R'};
				else if(lastMov=='L')
					op=new char[]{'D','L'};
				else
					op=new char[]{'D','L','R'};
			break;
			case 2:
				if(lastMov=='R')
					op=new char[]{'D'};
				else if(lastMov=='U')
					op=new char[]{'L'};
				else
					op=new char[]{'D','L'};
			break;
			case 3:
				if(lastMov=='L')
					op=new char[]{'U','D'};
				else if(lastMov=='U')
					op=new char[]{'U','R'};
				else if(lastMov=='D')
					op=new char[]{'D','R'};
				else
					op=new char[]{'U','D','R'};
			break;
			case 4:
				if(lastMov=='R')
					op=new char[]{'U','D','R'};
				else if(lastMov=='L')
					op=new char[]{'U','D','L'};
				else if(lastMov=='U')
					op=new char[]{'U','L','R'};
				else if(lastMov=='D')
					op=new char[]{'D','L','R'};
				else
					op=new char[]{'U','D','L','R'};
			break;
			case 5:
				if(lastMov=='R')
					op=new char[]{'U','D'};
				else if(lastMov=='U')
					op=new char[]{'U','L'};
				else if(lastMov=='D')
					op=new char[]{'D','L'};
				else
					op=new char[]{'U','D','L'};
					
			break;
			case 6:
				if(lastMov=='D')
					op=new char[]{'R'};
				else if(lastMov=='L')
					op=new char[]{'U'};
				else
					op=new char[]{'U','R'};
			break;
			case 7:
				if(lastMov=='R')
					op=new char[]{'U','R'};
				else if(lastMov=='L')
					op=new char[]{'U','L'};
				else if(lastMov=='D')
					op=new char[]{'L','R'};
				else
					op=new char[]{'U','L','R'};
			break;
			case 8:
				if(lastMov=='R')
					op=new char[]{'U'};
				else if(lastMov=='D')
					op=new char[]{'L'};
				else
					op=new char[]{'U','L'};
			break;
		}
		return op;
	}
	
}
