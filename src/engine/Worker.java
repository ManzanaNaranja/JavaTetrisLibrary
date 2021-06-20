package engine;

import java.util.HashMap;
import java.util.Map;

public class Worker {
	
	private HashMap<Job, WorkData> jobs;
	
	public Worker() {
		jobs = new HashMap<Job, WorkData>();
	}
	
	public void addJob(Job job, Integer ms) {
		jobs.put(job, new WorkData(ms));
	}
	
	public void update(int ms) {
		for(Map.Entry<Job, WorkData> element : jobs.entrySet()) {
			WorkData data = element.getValue();
			data.timeleft -= ms;
			if(data.timeleft <= 0) {
				element.getKey().dothis();
				data.timeleft += data.WORK_TIME;
			}
		}
	}
	
	
}
