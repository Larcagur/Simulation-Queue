package gui.elements;

import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;

public class Control extends HBox{
	
	private Slider sliderSimulatedTime;
	
	public Control(double width, double height){
		this.setWidth(width);
		this.setHeight(height);
		this.sliderSimulatedTime = new Slider();
		this.sliderSimulatedTime.setShowTickLabels(true);
		
		this.getChildren().add(sliderSimulatedTime);
	}
	
	public void setSliderMinValue(double min){
		sliderSimulatedTime.setMin(min);
	}
	public void setSliderMaxValue(double max){
		sliderSimulatedTime.setMax(max);
	}
	public void setSliderStepSize(double stepsize){
		sliderSimulatedTime.setMajorTickUnit(stepsize);
	}
}
