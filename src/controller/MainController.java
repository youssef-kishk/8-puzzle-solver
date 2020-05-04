package controller;

import java.util.ArrayList;



import testGame.TestGame;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public abstract class MainController extends TestGame{
	public static Stage primaryStage;
	@FXML
	public Pane mainPane;	
	public static String inputArray="";
	public static ArrayList <Integer>inputArrayList= new ArrayList<Integer>();
	public static int pressedButton;
	public static boolean runningDone=false;

}
