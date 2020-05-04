package controller;

import java.util.Stack;


import puzzleGame.EightPuzzleGame;
import puzzleGame.Node;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class GameController extends MainController{
	@FXML
	public VBox gamePane;
	@FXML
	public GridPane gameArray;
	@FXML
	public ImageView positionZero;
	@FXML
	public ImageView positionOne;
	@FXML
	public ImageView positionTwo;
	@FXML
	public ImageView positionThree;
	@FXML
	public ImageView positionFour;
	@FXML
	public ImageView positionFive;
	@FXML
	public ImageView positionSix;
	@FXML
	public ImageView positionSeven;
	@FXML
	public ImageView positionEight;
	@FXML
	public Button start;
	@FXML
	public TextField pathToGoalField;
	@FXML
	public TextField costOfPathField;
	@FXML
	public TextField nodesExpandedField;
	@FXML
	public TextField searchDepthField;
	@FXML
	public TextField runningTimeFiled;
	@FXML
	public Button nextStepButton;
	@FXML
	public void initialize(){
			gameArray.setGridLinesVisible(true);
			setPlayArrayValues();
	}
	//start button on mouse released,starts puzzle solving
	@FXML
	public void startSolving(){
		switch(pressedButton){
		case 0:callBFS(inputArrayList);
				runningDone=true;
			break;
		case 1:callDFS(inputArrayList);
				runningDone=true;
			break;
		case 2:
			callastarManhattan(inputArrayList);
				runningDone=true;
			break;
		case 3:
			callastarEuclidean(inputArrayList);
				runningDone=true;
			break;
	  }
		if(runningDone){
			start.setDisable(true);
			start.setText("Done");
			pathToGoalField.setText(pathToGoalField.getText()+EightPuzzleGame.pathToGoal.toString());
			costOfPathField.setText(costOfPathField.getText()+EightPuzzleGame.costOfPath);
			nodesExpandedField.setText(nodesExpandedField.getText()+EightPuzzleGame.nodesExpanded);
			searchDepthField.setText(searchDepthField.getText()+EightPuzzleGame.searchDepth);
			runningTimeFiled.setText(runningTimeFiled.getText()+EightPuzzleGame.runningTime+" sec");
			nextStepButton.setDisable(false);
		}

	}
	//start button on mouse pressed,wait until solving is finished
	@FXML
	public void waitMode(){
		start.setDisable(true);
		start.setText("Please wait");

	}
	private Node current;
	private int flag=0;
	Stack <Node>nextStepToGoal = new Stack<Node>();
	//Next step button set on action.show next step of solving puzzle,starting from initail state
	@FXML
	public void showNextSolveStep(){
				inputArray="";
				if(flag==0){
					current=EightPuzzleGame.finalNode;
					while(current.parent!=null){
						nextStepToGoal.push(current);
						current=current.parent;
					}
					flag=1;
				}
				if(flag==1){
					String temp = nextStepToGoal.pop().gameBoard.toString();
					for(int i=1;i<temp.length()-1;i++){
						if(temp.charAt(i)!=' ')
							inputArray+=temp.charAt(i);
					}
					setPlayArrayValues();
					printOutputToConsole();
					if(nextStepToGoal.isEmpty())
						nextStepButton.setDisable(true);
			}
	}
	//private function to print output on console
	private void printOutputToConsole(){	
		for(int i=0;i<inputArray.length();i=i+2){
			if(i==4||i==10||i==16)
				System.out.println(inputArray.charAt(i));
			else
				System.out.print(inputArray.charAt(i)+"\t");
		}
		System.out.println("------------------");
		
	}
	//private method to Set GUI array values
	private void setPlayArrayValues(){
		  for(int i=0;i<inputArray.length();i=i+2){
			  inputArrayList.add(Integer.parseInt(inputArray.charAt(i)+""));
			switch(i){
				case 0:positionZero.setImage(new Image("numbers/"+inputArray.charAt(i)+".png"));
				break;
				case 2:positionOne.setImage(new Image("numbers/"+inputArray.charAt(i)+".png"));
				break;
				case 4:positionTwo.setImage(new Image("numbers/"+inputArray.charAt(i)+".png"));
				break;
				case 6:positionThree.setImage(new Image("numbers/"+inputArray.charAt(i)+".png"));
				break;
				case 8:positionFour.setImage(new Image("numbers/"+inputArray.charAt(i)+".png"));
				break;
				case 10:positionFive.setImage(new Image("numbers/"+inputArray.charAt(i)+".png"));
				break;
				case 12:positionSix.setImage(new Image("numbers/"+inputArray.charAt(i)+".png"));
				break;
				case 14:positionSeven.setImage(new Image("numbers/"+inputArray.charAt(i)+".png"));
				break;
				case 16:positionEight.setImage(new Image("numbers/"+inputArray.charAt(i)+".png"));
				}
		}
	}
}
