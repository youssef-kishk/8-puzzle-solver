package astar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

import puzzleGame.EightPuzzleGame;
import puzzleGame.MinPriorityQueue;
import puzzleGame.Node;

public class AstarManhatten extends EightPuzzleGame {
	private Node node;     
	ArrayList<Integer> winingState = new ArrayList<Integer>();
	public AstarManhatten(ArrayList <Integer>input){
		int temp=0;
		winingState.addAll(Arrays.asList(new Integer[]{0,1,2,3,4,5,6,7,8}));
		pathToGoal= new ArrayList<String>();
		node = new Node(input);
		game();
	}

	int flag=0;
    private Comparator<Node> comparator = new Heuristic();
	MinPriorityQueue<Node> frontier;
    Queue<Node>expanded;
	private void game(){
		Node newChildNode=null;
		char[] movement;
		Node state=null;
		 int hFunction;
		frontier = new MinPriorityQueue<Node>(comparator);
		expanded = new LinkedList<Node>();
		frontier.insert(node);
		node.parent=null;

		while(!frontier.isEmpty()){
			state = frontier.delMin();
			expanded.add(state);
			if(checkGoal(state,winingState)==true){
				finalNode=state;
				return;	
			}
			zeroIndex= state.zeroPlace;
			lastMov=state.lastMovment;
				movement=movementDirections();
				for(int i=0;i<movement.length;i++){
					if(movement[i]=='U')
						newChildNode=replace(state,zeroIndex,zeroIndex-3,movement[i]);
					else if(movement[i]=='D')
						newChildNode=replace(state,zeroIndex,zeroIndex+3,movement[i]);
					else if(movement[i]=='L')
						newChildNode=replace(state,zeroIndex,zeroIndex-1,movement[i]);
					else if(movement[i]=='R')
						newChildNode=replace(state,zeroIndex,zeroIndex+1,movement[i]);
					if(!contains(expanded,newChildNode)&&!IsInFrontier(newChildNode)){
							frontier.insert(newChildNode);
							newChildNode.parent=state;
							state.childs.add(newChildNode);
					}
					else if(IsInFrontier(newChildNode)){
						newChildNode.parent=state;
						frontier.insert(newChildNode);
					}
				}
			
			nodesExpanded++;
			if(!state.childs.isEmpty()){
				for (Node e : state.childs){
					for (Node f: frontier){
						if(e.gameBoard.equals(f.gameBoard) && e.totalCostMenhaten < f.totalCostMenhaten){
							state.childs.remove(f);
						}
						
					}
				}
			}
			
			if(frontier.min().depth>state.depth){
				if(flag==0){
					pathToGoal.add(frontier.min().lastMovment+"");
					flag=1;
				}
				else
					pathToGoal.add(state.lastMovment+"");
			}
			
		}
	
	}
	
	public boolean IsInFrontier(Node state) {

		if(!frontier.isEmpty()){
			
			for(Node e:frontier){
				if(e.gameBoard.equals(state.gameBoard))
						return true;
			}
			
		}
		
		return false;
		
	}
	private class Heuristic implements Comparator<Node>{

		@Override
		public int compare(Node n1, Node n2) {
			int x = this.computeManhatten(n1) + n1.depth;
			int y = this.computeManhatten(n2) + n2.depth;
		    n1.totalCostMenhaten=x;
		    n2.totalCostMenhaten=y;

			if ( this.computeManhatten(n1)+ n1.depth > this.computeManhatten(n2)+n2.depth )
				return 1;
			else if ( this.computeManhatten(n1)+ n1.depth < this.computeManhatten(n2)+n2.depth )
				return -1;
			return 0;
		}
		public int computeManhatten(Node state){
			int manhatten = 0;
            int statePositionX;
            int statePositionY;
            int boardPositionX;
            int boardPositionY;
            int hCoordinates= 0;
            for(int i = 0; i<= 8;i++){
            	if(i==0)
            		continue;
                    statePositionX = setHorizontalPosition(state.gameBoard.indexOf(i));
                    statePositionY= setVerticalPosition(state.gameBoard.indexOf(i));
                    boardPositionX = setHorizontalPosition(winingState.indexOf(i));
                    boardPositionY= setVerticalPosition(winingState.indexOf(i));
                    
                    hCoordinates = Math.abs(statePositionX - boardPositionX)+Math.abs(statePositionY - boardPositionY);
                        manhatten+= hCoordinates;
            }
            return manhatten;
        }
		private int  setHorizontalPosition(int statePositionX){
	        if(statePositionX==0||statePositionX==1||statePositionX==2)
	        	statePositionX=0;
	        else if(statePositionX==3||statePositionX==4||statePositionX==5)
	        	statePositionX=1;
	        else if(statePositionX==6||statePositionX==7||statePositionX==8)
	        	statePositionX=2;
	        return statePositionX;
		}
		private int  setVerticalPosition(int statePositionY){
	        if(statePositionY==0||statePositionY==3||statePositionY==6)
	        	statePositionY=0;
	        else if(statePositionY==1||statePositionY==4||statePositionY==7)
	        	statePositionY=1;
	        else if(statePositionY==2||statePositionY==5||statePositionY==8)
	        	statePositionY=2;
	        return statePositionY;
		}
	}

}
