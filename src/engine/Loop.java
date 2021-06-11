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
	
	protected void addJob(Job job, int period) {
		worker.addJob(job, period);
	}
	
	protected void addJob(Job job, int period, int msToExecution) {
		worker.addJob(job, period, msToExecution);
	}
	
	protected void removeJob(Job job) {
		worker.removeJob(job);
	}
	
	
	protected void stop() {
		running = false;
	}
}
