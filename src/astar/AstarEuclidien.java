
package astar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

import puzzleGame.EightPuzzleGame;
import puzzleGame.MinPriorityQueue;
import puzzleGame.Node;

public class AstarEuclidien extends EightPuzzleGame {
	private Node node;     
	ArrayList<Integer> winingState = new ArrayList<Integer>();
	public AstarEuclidien(ArrayList <Integer>input){
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
					if(!contains(expanded,newChildNode)&&!IsFrontier(newChildNode)){
							frontier.insert(newChildNode);
							newChildNode.parent=state;
							state.childs.add(newChildNode);
					}
					else if(IsFrontier(newChildNode)){
						newChildNode.parent=state;
						frontier.insert(newChildNode);
					}
				}
			
			nodesExpanded++;
			if(!state.childs.isEmpty()){
				for (Node e : state.childs){
					for (Node f: frontier){
						if(e.gameBoard.equals(f.gameBoard) && e.totalCostEuclidien < f.totalCostEuclidien){
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
	
	public boolean IsFrontier(Node state) {

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
			double x = this.computeEuclidien(n1) + n1.depth;
			double y = this.computeEuclidien(n2) + n2.depth;
		    n1.totalCostEuclidien=x;
		    n2.totalCostEuclidien=y;

			if ( this.computeEuclidien(n1)+ n1.depth > this.computeEuclidien(n2)+n2.depth )
				return 1;
			else if ( this.computeEuclidien(n1)+ n1.depth < this.computeEuclidien(n2)+n2.depth )
				return -1;
			return 0;
		}
		public double computeEuclidien(Node state){
			double euiclidien = 0;
            int statePositionX;
            int statePositionY;
            int boardPositionX;
            int boardPositionY;
            double hCoordinates= 0;
            for(int i = 0; i<= 8;i++){
            	if(i==0)
            		continue;
                    statePositionX = setHorizontalPosition(state.gameBoard.indexOf(i));
                    statePositionY= setVerticalPosition(state.gameBoard.indexOf(i));
                    boardPositionX = setHorizontalPosition(winingState.indexOf(i));
                    boardPositionY= setVerticalPosition(winingState.indexOf(i));
                    
                    hCoordinates =Math.sqrt(Math.pow(statePositionX-boardPositionX, 2)+Math.pow(statePositionY-boardPositionY, 2));
                    euiclidien+= hCoordinates;
            }
            return euiclidien;
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
