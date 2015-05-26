package multithreading;

import java.util.Scanner;

public class TestProducerConsumer {
	public static void main(String[] args) {
		Queue queue = new Queue();
		new Thread(new Producer(queue)).start();
		new Thread(new Consumer(queue)).start();
		
	}
	
	public static class Producer implements Runnable {
		
		private Queue queue;
		
		public Producer(Queue queue) {
			this.queue = queue;
		}
		
		@Override
		public void run() {
			Scanner scanner = new Scanner(System.in);
			boolean ch = true;
			do {
				synchronized (queue) {
					System.out.println("Enter input for producer");
					String nextLine = scanner.nextLine();
					queue.msg = nextLine;
					try {
						queue.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("Do u want to continue");
					ch = scanner.nextBoolean();
				}
			} while (ch);
			queue.isFinished = true;
		}
		
	}
	
	public static class Consumer implements Runnable {
		
		private Queue queue;
		
		public Consumer(Queue queue) {
			this.queue = queue;
		}
		
		@Override
		public void run() {
			while (!queue.isFinished) {
				System.out.println("In Consumer..");
				synchronized (queue) {
					System.out.println("Msg is"+queue.msg);
					queue.notify();
				}
			}
		}
		
	}
	
	public static class Queue {
		private String msg;
		private boolean isFinished = false;
	}
	
	
	
}
