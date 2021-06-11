package engine;

public abstract class Loop {
	public static final int MS_PER_UPDATE = 17;
	public boolean running = false;
	private Worker worker = new Worker();
	
	
	
	protected void start() {
		running = true;
		long previous = System.currentTimeMillis();
		while(running) {
			long current = System.currentTimeMillis();
			long elapsed = current - previous;
			previous = current;
			worker.update((int)elapsed);
		}
	}
	
	protected void addJob(Job job, int ms) {
		worker.addJob(job, ms);
	}
	
	
	protected void stop() {
		running = false;
	}
}
