package controller;


import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
public class WelcomePageController extends MainController{
	@FXML
	public VBox welcomePagePane;
	@FXML
	public ImageView image;
	@FXML
	public HBox buttonsPane;
	@FXML
	public Button BFS;
	@FXML
	public Button DFS;
	@FXML
	public Button ASTARManh;	
	@FXML
	public Button ASTAREucli;
	@FXML
	public TextField input;
	//BFS button on mouse pressed
	@FXML 
	public void BFSButtonPressed(){
		pressedButton=0;
		MainController.primaryStage.setTitle("BFS");
	}
	//DFS button on mouse pressed
	@FXML 
	public void DFSButtonPressed(){
		pressedButton=1;
		MainController.primaryStage.setTitle("DFS");
	}
	//AStar button on mouse pressed
	@FXML 
	public void AstarManhButtonPressed(){
		pressedButton=2;
		MainController.primaryStage.setTitle("Astar Manhattan");
	}
	@FXML 
	public void AstarEuclideanButtonPressed(){
		pressedButton=3;
		MainController.primaryStage.setTitle("Astar Euclidean");
	}
	@FXML
	public void initialize(){
		runningDone=false;
		image.setImage(new Image("image.png"));
	}
	//BFS,DFS,ASTAR button set on action
	@FXML 
	public void buttonAction() throws IOException{
		inputArray = input.getText();
		if(inputArray.matches("[0-8],+[0-8],+[0-8],+[0-8],+[0-8],+[0-8],+[0-8],+[0-8],+[0-8]")){
			if(inputArray.contains('0'+"")&&inputArray.contains('1'+"")&&inputArray.contains('2'+"")
					&&inputArray.contains('3'+"")&&inputArray.contains('4'+"")&&inputArray.contains('5'+"")
					&&inputArray.contains('6'+"")&&inputArray.contains('7'+"")&&inputArray.contains('8'+"")){
			mainPane=FXMLLoader.load(getClass().getResource("/GamePage.fxml"));
			MainController.primaryStage.setScene(new Scene(mainPane,472,545));
			}	
			else
				input.clear();
		}
		else
			input.clear(); 
	}


	

}
