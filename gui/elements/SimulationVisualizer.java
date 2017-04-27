package gui.elements;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

public class SimulationVisualizer extends GridPane{
	
	private HBox qsContainer;
	private List<VisualQueue> queues;
	private List<VisualServer> server;
	private HBox csContainer;
	private Control control;
	private Statistics statistics;
	
	public SimulationVisualizer(double width, double height){
		this.queues = new ArrayList<VisualQueue>();
		this.server = new ArrayList<VisualServer>();
		this.control = new Control(0.0, 0.0);
		this.statistics = new Statistics();
		this.qsContainer = new HBox();
		this.csContainer = new HBox();
		
		/*
		VisualServer s = new VisualServer(width, height);
		this.add(s, 1, 1);
		Button b = new Button("change state");
		b.setOnAction( e -> {
			s.switchServerState();
		});
		this.add(b, 1, 2);
		*/
		
		VisualQueue q = new VisualQueue(width, height);
		this.add(q, 1, 1);
		q.setTextFont(new Font("Times New Roman", 40.0));
		q.setQueueSize(100L);
		
		
	}
}
