package com.aishwarya.synchronization.constructs;

public class MutexExamples {
	
	private static int counter = 0;
	private static final Object lock = new Object();
	
	public static void runExperiment(String experimentName , Runnable task) {
		counter = 0 ;
		Thread thread1= new Thread(task);
		Thread thread2= new Thread(task);
		
		thread1.start();
		thread2.start();
		try {
			thread1.join();
			thread2.join();
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		System.out.println("Final counter value " + experimentName + ": " + counter + "\n");
		
	}
	
	 public static void incrementCounterWithMutex() {
		 for(int i = 0 ;i < 100 ;i++) {
			 synchronized (lock) {
				 int temp = counter;
				 try {
						Thread.sleep(1); // Sleep for 1 millisecond
					}catch(InterruptedException e){
						e.printStackTrace();
					}
				 counter = temp + 1;
			 }
		 }
	 }
	 
	 public static void incrementCounterNoMutex() {
	        for (int i = 0; i < 100; i++) {
	            int temp = counter;
	            try {
	                Thread.sleep(1); // Sleep for 1 millisecond
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            counter = temp + 1;
	        }
	    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		runExperiment("With Mutex Experiment", MutexExamples::incrementCounterWithMutex);
        runExperiment("No Mutex Experiment", MutexExamples::incrementCounterNoMutex);

	}

}
