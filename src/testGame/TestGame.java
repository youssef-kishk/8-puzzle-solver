package testGame;


import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import puzzleGame.EightPuzzleGame;
import controller.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import dfs.DFS;
import astar.AstarEuclidien;
import astar.AstarManhatten;
import bfs.BFS;

public class TestGame extends Application{
	public static void main(String[] args) {
		/*
		 
		->	1,2,5,3,4,0,6,7,8	<-
		->	6,1,8,4,0,2,7,3,5	<-
		->	8,6,4,2,1,3,5,7,0	<-
		->	8,6,7,2,5,4,3,0,1	<-
		->	7,2,4,5,0,6,8,3,1	<-
		
		 */
		Application.launch(args);
		System.exit(0);

	}
	
	@Override
	public void start(Stage s) throws Exception {
		MainController.primaryStage= new Stage();
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("WelcomePage.fxml"));
		MainController.primaryStage.setScene(new Scene(root,510,300));
		MainController.primaryStage.setTitle("Eight Puzzle Game");
		MainController.primaryStage.setResizable(false);
		MainController.primaryStage.show();		

	}
	private static String solvingWay="";
	public static void callBFS(ArrayList <Integer> input){
		solvingWay="BFS";
		Long time=System.currentTimeMillis();
		BFS testBFS = new BFS(new ArrayList <Integer>(input));
		EightPuzzleGame.runningTime=((double)(System.currentTimeMillis()-time)/1000);
		try {
			writeToOutputFile();
		} catch (FileNotFoundException e) {
		}
	}
	
	public static void callDFS(ArrayList <Integer> input){
		solvingWay="DFS";
		Long time=System.currentTimeMillis();
		DFS testDFS = new DFS(new ArrayList <Integer>(input));
		EightPuzzleGame.runningTime=((double)(System.currentTimeMillis()-time)/1000);
		try {
			writeToOutputFile();
		} catch (FileNotFoundException e) {
		}
	}
	public static void callastarManhattan(ArrayList <Integer> input){
		solvingWay="A* Manhattan";
		Long time=System.currentTimeMillis();
		AstarManhatten testastar = new AstarManhatten(new ArrayList <Integer>(input));
		EightPuzzleGame.runningTime=((double)(System.currentTimeMillis()-time)/1000);
		try {
			writeToOutputFile();
		} catch (FileNotFoundException e) {
		}
	}
	public static void callastarEuclidean(ArrayList <Integer> input){
		solvingWay="A* Euclidean";
		Long time=System.currentTimeMillis();
		AstarEuclidien testastar = new AstarEuclidien(new ArrayList <Integer>(input));
		EightPuzzleGame.runningTime=((double)(System.currentTimeMillis()-time)/1000);
		try {
			writeToOutputFile();
		} catch (FileNotFoundException e) {
		}
	}
	private static void writeToOutputFile() throws FileNotFoundException{
		PrintWriter p = new PrintWriter("output.txt");
		p.println("Solving algorithm: "+solvingWay);
		p.println("path_to_goal: "+EightPuzzleGame.pathToGoal.toString());
		p.println("cost_of_path: "+EightPuzzleGame.costOfPath);
		p.println("nodes_expanded: "+EightPuzzleGame.nodesExpanded);
		p.println("search_depth: "+EightPuzzleGame.searchDepth);
		p.println("running_time: "+EightPuzzleGame.runningTime);
		p.close();
	}

}
