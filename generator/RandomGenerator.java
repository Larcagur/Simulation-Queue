package generator;

import java.util.Random;

public abstract class RandomGenerator {
	
	private Random rand; 
	private int seedRenewalRate;
	private int currentRateIndex;
	
	protected double mean;
	protected double lambda;
	protected double variance;
	protected double median;	
	
	public RandomGenerator(double mean, long fixedseed){
		this(mean, -1);
		this.rand = new Random(fixedseed);
	}
	
	public RandomGenerator(double mean, int seedRenewalRate){
		if(mean == 0.0){
			throw new IllegalArgumentException("MEAN-Parameter can not be 0.0");
		}
		this.mean = mean;
		this.seedRenewalRate = seedRenewalRate;
		this.rand = new Random(System.currentTimeMillis());
	}
	
	public abstract double getMean();
	public abstract double getLambda();
	public abstract double getVariance();
	public abstract double getMedian();
	
	protected abstract double getFx(double x);
	protected abstract double nextFxInverse();
	protected double generateNextDouble(){
		if((seedRenewalRate > 0) && (currentRateIndex++ >= seedRenewalRate)){
			this.rand = new Random(System.currentTimeMillis());
			currentRateIndex = 0;
		}
		return rand.nextDouble();
	}
}
