package engine;

public class WorkData {
	public final int WORK_TIME;
	public int timeleft;
	public WorkData(int ms) {
		this.WORK_TIME = ms;
		this.timeleft = 0;
	}
}
