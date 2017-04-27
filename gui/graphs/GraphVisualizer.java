package gui.graphs;

import java.util.List;

import org.apache.commons.math3.distribution.ExponentialDistribution;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public abstract class GraphVisualizer extends GridPane{

	protected ExponentialDistribution ed;
	protected final double stepSize = 0.01;
	
	protected final int numberOfXMarks = 6;
	protected final double halfMarkWidth = 10.0;
	protected final double offsetX = 25.0;
	protected final double offsetY = 25.0;
	protected final double offsetTextY = 5.0;
	protected final double offsetTextX = 10.0;
	protected double maxPixelX;
	protected double maxPixelY;
	
	protected Canvas canvas;
	protected GraphicsContext gc;
	
	protected Label lblGraphDesc;
	protected Label lblYAxis;
	protected Label lblXAxis;
	
	protected Label lblLambdaValue;
	protected Label lblMaxXValue;
	protected Slider sliderLambda;
	protected double lambdaValue; // value of lambda rounded to 2 digits
	protected double lambdaValueInverse; // value of inverse to lambda
	protected Slider sliderMaxXValue;
	protected double maxXValue;
	
	protected Button btnStartDrawing;
	
	public GraphVisualizer(double width, double height, String graphCaption){
		// set width and height of this component and other settings
		this.setWidth(width + 50);
		this.setHeight(height + 50);
		this.setPadding(new Insets(5.0, 5.0, 5.0, 5.0));
		// set the max pixel for the graphs and leave space for the axis etc. 
		maxPixelX = width - (2*offsetX);
		maxPixelY = height - (2*offsetY);
		
		// create new canvas
		this.canvas = new Canvas(width, height);
		this.gc = canvas.getGraphicsContext2D();
		this.gc.setStroke(Color.RED);
		this.add(this.canvas, 1, 1);
		
		// creating labels...
		this.lblGraphDesc = new Label(graphCaption);
		this.lblGraphDesc.setStyle("-fx-underline: true;");
		this.lblYAxis = new Label("Y-Axis");
		this.lblYAxis.setRotate(270.0);
		this.lblXAxis = new Label("X-Axis");
		this.lblLambdaValue = new Label("");
		this.lblLambdaValue.setMinWidth(50.0);
		this.lblMaxXValue = new Label("");
		this.lblMaxXValue.setMinWidth(50.0);
		this.add(lblGraphDesc, 0, 0, 3, 1);
		this.add(lblYAxis, 0, 1, 1, 1);
		this.add(lblXAxis, 0, 2, 3, 1);
		this.add(lblLambdaValue, 0, 3, 1, 1);
		this.add(lblMaxXValue, 0, 4, 1, 1);
		
		// some positioning...
		setHalignment(this.canvas, HPos.CENTER);
		setHalignment(this.lblGraphDesc, HPos.CENTER);
		setHalignment(this.lblXAxis, HPos.CENTER);
		setHalignment(this.lblYAxis, HPos.CENTER);
		
		// creating control elements...
		this.sliderLambda = new Slider(0.001, 0.1, 0.001);
		this.sliderLambda.setPrefWidth(width - 100.0);
		this.sliderLambda.valueProperty().addListener( e -> {
			lambdaValue = ((double)Math.round(sliderLambda.getValue()*1000)/1000.0);
			lambdaValueInverse = 1.0/lambdaValue;
			lblLambdaValue.setText("" + lambdaValue);
		});
		this.add(sliderLambda, 1, 3, 3, 1);
		
		this.sliderMaxXValue = new Slider(5, 3000, 5);
		this.sliderMaxXValue.setPrefWidth(width - 100.0);
		this.add(sliderMaxXValue, 1, 4, 3, 1);
		
		this.btnStartDrawing = new Button("Draw Graph");
		this.btnStartDrawing.setPrefWidth(100.0);
		this.add(this.btnStartDrawing, 1, 5);
	}
	
	public ExponentialDistribution getExponentialDistr(){
		return this.ed;
	}
	public void setExponentialDistr(ExponentialDistribution newEd){
		this.ed = newEd;
		drawGraph(true);
	}
	
	protected abstract void drawGraph(boolean clearExistingGraphs);
	protected abstract void drawPoints(List<Point> points);
	protected abstract void drawAxis(double maxXValue);
	protected void drawAxis(){
		gc.setLineWidth(2.0);
		gc.setStroke(Color.BLACK);
		double yRatio = (maxPixelY-offsetY)/5;
		
		// vertical axis
		gc.strokeLine(offsetX, offsetY, offsetX, maxPixelY);
		gc.strokeLine(offsetX, maxPixelY - (yRatio*1), offsetX + halfMarkWidth, maxPixelY - (yRatio*1));
		gc.strokeLine(maxPixelX  + offsetX - halfMarkWidth, maxPixelY - (yRatio*1), maxPixelX + offsetX, maxPixelY - (yRatio*1));
		gc.strokeLine(offsetX, maxPixelY - (yRatio*2), offsetX + halfMarkWidth, maxPixelY - (yRatio*2));
		gc.strokeLine(maxPixelX  + offsetX - halfMarkWidth, maxPixelY - (yRatio*2), maxPixelX + offsetX, maxPixelY - (yRatio*2));
		gc.strokeLine(offsetX, maxPixelY - (yRatio*3), offsetX + halfMarkWidth, maxPixelY - (yRatio*3));
		gc.strokeLine(maxPixelX  + offsetX - halfMarkWidth, maxPixelY - (yRatio*3), maxPixelX + offsetX, maxPixelY - (yRatio*3));
		gc.strokeLine(offsetX, maxPixelY - (yRatio*4), offsetX + halfMarkWidth, maxPixelY - (yRatio*4));
		gc.strokeLine(maxPixelX  + offsetX - halfMarkWidth, maxPixelY - (yRatio*4), maxPixelX + offsetX, maxPixelY - (yRatio*4));
		gc.strokeLine(offsetX, maxPixelY - (yRatio*5), offsetX + halfMarkWidth, maxPixelY - (yRatio*5));
		gc.strokeLine(maxPixelX  + offsetX - halfMarkWidth, maxPixelY - (yRatio*5), maxPixelX + offsetX, maxPixelY - (yRatio*5));
		gc.setLineWidth(0.5);
		gc.strokeLine(offsetX + halfMarkWidth, maxPixelY - (yRatio*5), maxPixelX + offsetX, maxPixelY - (yRatio*5));
		gc.setLineWidth(2.0);
		
		// horizontal axis
		gc.strokeLine(offsetX, maxPixelY, maxPixelX + offsetX, maxPixelY);
		for(int i = 1; i <= numberOfXMarks; ++i){
			double xPos = offsetX + ((maxPixelX/numberOfXMarks)*i);
			gc.strokeLine(xPos, maxPixelY, xPos, maxPixelY - halfMarkWidth);
			gc.strokeLine(xPos, offsetY, xPos, offsetY + halfMarkWidth);
			if(i==numberOfXMarks){
				gc.setLineWidth(0.5);
				gc.strokeLine(xPos, maxPixelY, xPos, offsetY);
				gc.setLineWidth(2.0);
			}
		}
	}
}
