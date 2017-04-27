package gui.elements;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class VisualServer extends HBox{
	
	public enum ServerState{
		PROCESSING(Color.RED), IDLE(Color.GREEN);
		private Color color;
		ServerState(Color color){
			this.color = color;
		}
		public Color getColor(){ return this.color; }
	}
	
	private Canvas canvas;
	private GraphicsContext gc;
	private ServerState serverState;
	
	public VisualServer(double width, double height){
		this.setWidth(width);
		this.setHeight(height);
		this.canvas = new Canvas();
		this.canvas.setWidth(width);
		this.canvas.setHeight(height);
		this.getChildren().add(canvas);
		gc = this.canvas.getGraphicsContext2D();
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(2.0);
		setServerState(ServerState.IDLE);
	}
	
	public void setServerState(ServerState state){
		if(this.serverState != state){
			this.serverState = state;
			this.gc.setFill(state.getColor());
			drawServer();
		}
	}
	
	public void switchServerState(){
		switch(this.serverState){
		case IDLE:
			setServerState(ServerState.PROCESSING);
			break;
		case PROCESSING:
			setServerState(ServerState.IDLE);
			break;
		}
	}
	
	public ServerState getServerState(){
		return this.serverState;
	}
	
	private void drawServer(){
		gc.fillOval(0.0, 0.0, canvas.getWidth()/2, canvas.getHeight()/2);
		gc.strokeOval(0.0, 0.0, canvas.getWidth()/2, canvas.getHeight()/2);
	}
}
