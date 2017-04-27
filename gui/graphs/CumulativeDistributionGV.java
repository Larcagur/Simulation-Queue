package gui.graphs;

import java.util.List;

import org.apache.commons.math3.distribution.ExponentialDistribution;

import javafx.scene.paint.Color;


public class CumulativeDistributionGV extends GraphVisualizer{
	
	public CumulativeDistributionGV(double width, double height){
		super(width, height, "Cumulative Distribution");
		
		// attach event handler
		this.btnStartDrawing.setOnAction( e -> {
			ed = new ExponentialDistribution(this.lambdaValueInverse);
			drawGraph(true);
			
		});
		this.sliderMaxXValue.valueProperty().addListener( e -> {
			maxXValue = ((double)Math.round(sliderMaxXValue.getValue()*100)/100.0);
			lblMaxXValue.setText("" + maxXValue);
		});
		
		// trigger some events for initialization
		this.sliderLambda.valueProperty().setValue(1.0);
		this.sliderMaxXValue.valueProperty().setValue(5.0);
		
		// draw the graphs basic grid 
		this.drawAxis();
	}
	
	@Override
	public void drawGraph(boolean clearExistingGraph){
		
		if(clearExistingGraph){
			gc.clearRect(0.0, 0.0, canvas.getWidth(), canvas.getHeight());
			drawAxis(maxXValue);
		}
		
		double factorX = maxPixelX/maxXValue;
		double factorY = maxPixelY - offsetY;
		gc.setStroke(Color.RED);
		gc.setLineWidth(1.0);
		for(double x = 0; x <= maxXValue; x+=stepSize){
			double x2 = x + stepSize;
			gc.strokeLine(offsetX + (x*factorX), maxPixelY - (ed.cumulativeProbability(x)*factorY), 
					offsetX + (x2*factorX), maxPixelY - (ed.cumulativeProbability(x2)*factorY));
		}
	}
	
	@Override
	public void drawPoints(List<Point> points){
		double factorX = maxPixelX/maxXValue;
		double factorY = maxPixelY-offsetY;
		for(Point p : points){
			gc.fillRect(offsetX + (p.x*factorX), maxPixelY - (p.y*factorY), 1, 1);
			// would be more faster:
			//	pw.setColor(p.x*factorX, p.y*factorY, Color.BLUE);
		}
	}
	
	@Override
	protected void drawAxis(double maxXValue){
		super.drawAxis();

		double yRatio = (maxPixelY-offsetY)/5;
		
		// vertical axis - text
		gc.fillText("1.0", 0.0, offsetY + offsetTextY);
		gc.fillText("0.8", 0.0, maxPixelY - (yRatio*4) + offsetTextY);
		gc.fillText("0.6", 0.0, maxPixelY - (yRatio*3) + offsetTextY);
		gc.fillText("0.4", 0.0, maxPixelY - (yRatio*2) + offsetTextY);
		gc.fillText("0.2", 0.0, maxPixelY - (yRatio*1) + offsetTextY);
		gc.fillText("0.0", 0.0, maxPixelY + offsetTextY*2);
		
		// horizontal axis - text
		gc.strokeLine(offsetX, maxPixelY, maxPixelX + offsetX, maxPixelY);
		for(int i = 1; i <= numberOfXMarks; ++i){
			double xPos = offsetX + ((maxPixelX/numberOfXMarks)*i);
			gc.fillText("" + ((double)(Math.round(((maxXValue/numberOfXMarks)*i)*100)))/100, xPos - offsetTextX, maxPixelY + (offsetY/2));
		}
	}
}
