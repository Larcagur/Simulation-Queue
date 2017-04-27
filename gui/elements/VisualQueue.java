package gui.elements;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class VisualQueue extends HBox{
	
	private Canvas canvas;
	private GraphicsContext gc;
	
	public VisualQueue(double width, double height){
		this.setWidth(width);
		this.setHeight(height);
		this.canvas = new Canvas();
		this.canvas.setWidth(width);
		this.canvas.setHeight(height);
		this.getChildren().add(canvas);
		gc = this.canvas.getGraphicsContext2D();
		gc.setStroke(Color.BLACK);
		gc.setLineWidth(2.0);
		drawQueue();
	}
	
	public void drawQueue(){
		gc.setLineWidth(3.0);
		gc.strokeLine(1.0, 1.0, canvas.getWidth() - 1, 1.0);
		gc.strokeLine(canvas.getWidth() - 1, 1.0, canvas.getWidth() - 1, canvas.getHeight() - 1);
		gc.strokeLine(canvas.getWidth() - 1, canvas.getHeight() - 1, 1.0, canvas.getHeight() - 1);
		gc.setLineWidth(2.0);
		double xPos1 = canvas.getWidth()/10*9;
		double xPos2 = canvas.getWidth()/10*8;
		gc.strokeLine(xPos1, 1.0, xPos1, canvas.getHeight() - 1);
		gc.strokeLine(xPos2, 1.0, xPos2, canvas.getHeight() - 1);
	}
	
	public void setTextFont(Font font){
		gc.setFont(font);
	}
	public void setQueueSize(long queueSize){
		gc.fillText("" + queueSize, canvas.getWidth()/2, canvas.getHeight()/2);
	}
}
