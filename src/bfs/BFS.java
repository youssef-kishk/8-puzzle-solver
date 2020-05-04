package bfs;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import puzzleGame.EightPuzzleGame;
import puzzleGame.Node;

public class BFS extends EightPuzzleGame{
	private Node node;
	public BFS(ArrayList<Integer> input){
		int temp=0;
		//create path to goal object to store path
		pathToGoal= new ArrayList<String>();
		node = new Node(input);
		game();
	}
	
	private int flag=0;
	//frontier and expanded lists
	Queue<Node>frontier;
	Queue<Node>expanded;
	private void game(){
		Node newChildNode=null;
		//final list for the test of winning state
		ArrayList<Integer> winingStete = new ArrayList<Integer>();
		winingStete.addAll(Arrays.asList(new Integer[]{0,1,2,3,4,5,6,7,8}));
		char[] movement;
		Node state=null;
		frontier = new LinkedList<Node>();
		expanded = new LinkedList<Node>();
		//add root to frontier list
		frontier.add(node);
		node.parent=null;
		while(!frontier.isEmpty()){
			state = frontier.poll();
			expanded.add(state);
			//check achieving goal
			if(checkGoal(state,winingStete)==true){
				finalNode=state;
				return;	
			}
			//set value of zero index and last movement
			zeroIndex= state.zeroPlace;
			lastMov=state.lastMovment;
			movement=movementDirections();
			//determine movement direction with a priority (up,down,left,right)
			for(int i=0;i<movement.length;i++){
				if(movement[i]=='U')
					newChildNode=replace(state,zeroIndex,zeroIndex-3,movement[i]);
				else if(movement[i]=='D')
					newChildNode=replace(state,zeroIndex,zeroIndex+3,movement[i]);
				else if(movement[i]=='L')
					newChildNode=replace(state,zeroIndex,zeroIndex-1,movement[i]);
				else if(movement[i]=='R')
					newChildNode=replace(state,zeroIndex,zeroIndex+1,movement[i]);
				if(!contains(expanded,newChildNode)&&!contains(frontier,newChildNode)){
						newChildNode.parent=state;
						frontier.add(newChildNode);
				}
			}	
			nodesExpanded++;
			//add last movement to path to goal list
			if(frontier.peek().depth>state.depth){
				if(flag==0){
					pathToGoal.add(frontier.peek().lastMovment+"");
					flag=1;
				}
				else
					pathToGoal.add(state.lastMovment+"");
			}
		}
	
	}
}
