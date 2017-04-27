package gui;

import gui.elements.SimulationVisualizer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class QueueSimulationGUI extends Application {
	
	public static void main(String[] args){
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		/*
		List<Customer> customer = new ArrayList<>();
		QueueSimulation s = new QueueSimulation(1000.0, 100.0, 100, 2, customer, new StopCriteriaTimeperiod(7*24*60*60));
		s.start();
		*/
		
		GridPane root = new GridPane();
		//root.getChildren().add(graphV);
		root.setPrefWidth(500.0);
		root.setPrefHeight(500.0);		
		
		SimulationVisualizer visualizer = new SimulationVisualizer(500.0, 500.0);
		root.add(visualizer, 1, 1);
		
		Scene scene = new Scene(root);
		//stage.setResizable(false);
		stage.setTitle("Simulation eines Insel-Callshops");
		stage.setScene(scene);
		
		stage.show();
	}
}
