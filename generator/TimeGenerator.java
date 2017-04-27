package generator;

import java.util.concurrent.TimeUnit;

public class TimeGenerator extends RandomGenerator{
	
	private TimeUnit timeUnit;
	
	public TimeGenerator(TimeUnit timeUnit, double mean, int seedRenewalRate){
		super(mean, seedRenewalRate);
		this.timeUnit = timeUnit;
		this.lambda = 1.0/mean;
		this.variance = 1.0/(mean*mean);
		this.median = lambda * Math.log(2);
	}
	
	public TimeUnit getUsedTimeunit(){
		return this.timeUnit;
	}
	
	public double generateNextTimespan(){
		return nextFxInverse();
	}
	
	@Override
	protected double getFx(double x){
		return (1 - Math.pow(Math.E, -1*getLambda()*x));	
	}
	@Override
	protected double nextFxInverse(){
		double u = super.generateNextDouble();
		return (-1*(getMean())*Math.log(u));
	}
	
	@Override
	public double getMean(){
		return this.mean;
	}
	@Override
	public double getLambda(){
		return this.lambda;
	}
	@Override
	public double getVariance(){
		return this.variance;
	}
	@Override
	public double getMedian(){
		return this.median;
	}
}
