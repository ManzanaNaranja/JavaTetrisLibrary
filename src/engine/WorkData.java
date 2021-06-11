package engine;

public class WorkData {
	public final int WORK_TIME;
	public int timeleft;
	
	public WorkData(int ms) {
		this(ms, 0);
	}
	
	public WorkData(int ms, int timeToExecution) {
		this.WORK_TIME = ms;
		this.timeleft = timeToExecution;
	}
}
