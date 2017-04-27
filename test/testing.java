package test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Worker extends Thread{
	private List<String> s;
	public Worker(List<String> s){ this.s= s; } 
	public void run(){
		System.out.println(Thread.currentThread().getName() + " before");
		synchronized (s) {
			for(int i = 0; i < 10000000; i++){
				Math.random();
			}
		}
		System.out.println(Thread.currentThread().getName() + " after");
	}
}

public class testing {
	
	private static List<String> s;
	
	public static final List<String> t(){
		return s;
	}
	
	public static void main(String[] args) throws IOException {
		
		s = new ArrayList<>();
		s.add("one");
		List<String> l = t();
		l.add("");
		System.out.println(s.size());
		System.out.println(l.size());
		/*
		List<String> r = new ArrayList<>();
		Worker w1 = new Worker(r);
		Worker w2 = new Worker(r);
		w1.start();
		w2.start();
		
		try(FileWriter fw = new FileWriter(new File("/home/larca/Desktop/sample.txt"))){
			fw.write("x;data;dataInverse");
			double lambda = 0.001;
			Random r = new Random(System.currentTimeMillis());
			for(int i = 0; i < 1000; i++){
				double x = r.nextDouble();
				double FX = 1 - Math.pow(Math.E, -1*lambda*x);				
				double FXInverse = -1*(1.0/lambda)*Math.log(1 - x);
				fw.write("\r\n" + x + ";" + FX + ";" + FXInverse);
			}
		}
		*/
	}
}
