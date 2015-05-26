package multithreading;

public class TestInterruptThread {
	
	public static void main(String[] args) {
		Thread thread1 = new Thread(new Runnable1());
		thread1.start();
	}
	
	private static class Runnable1 implements Runnable {

		@Override
		public void run() {
			System.out.println("Thread 1");
			System.out.println("Thread 1 goin to sleep");
			Thread thread = new Thread(new Runnable2(Thread.currentThread()));
			/*thread.start();
			for (int i = 0; i < 100; i++) {
				System.out.println(i);
			}*/
			System.out.println("Interrupted Status"+Thread.currentThread().isInterrupted());
			System.out.println("Static Interrupted Status"+Thread.interrupted());
			System.out.println("Static Interrupted Status"+Thread.interrupted());
			/*try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				System.out.println("Interrupted");
				System.out.println("Interrupted Status"+Thread.interrupted());
			}*/
		}
		
	}
	
	private static class Runnable2 implements Runnable {
		
		private Thread thread;

		public Runnable2(Thread thread) {
			this.thread = thread;
			thread.interrupt();
		}
		
		@Override
		public void run() {
			System.out.println("Thread 2");
			thread.interrupt();
			/*System.out.println("Thread 1 goin to sleep");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				System.out.println("Interrupted");
				System.out.println("Interrupted Status"+Thread.interrupted());
			}*/
		}
		
	}
	
}
