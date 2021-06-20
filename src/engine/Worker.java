package engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Worker {
	
	private HashMap<Job, WorkData> jobs;
	private HashMap<Job, WorkData> addqueue;
	private ArrayList<Job> removequeue;
	
	public Worker() {
		jobs = new HashMap<Job, WorkData>();
		addqueue = new HashMap<Job, WorkData>();
		removequeue = new ArrayList<>();
	}
	
	public void addJob(Job job, Integer ms) {
		addqueue.put(job, new WorkData(ms));
	}
	
	public void addJob(Job job, Integer ms, Integer timeToExecution) {
		addqueue.put(job, new WorkData(ms, timeToExecution));
	}
	
	public void removeJob(Job job) {
		removequeue.add(job);
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
		jobs.putAll(addqueue);
		addqueue.clear();
		for(Job j : removequeue) {
			jobs.remove(j);
		}
		removequeue.clear();
	}
	
	
}
