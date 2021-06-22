package tetris;

import java.util.LinkedList;

import helpers.AUtils;

public class BoardHistory {
	private LinkedList<int[][]> data;
	
	public BoardHistory() {
		data = new LinkedList<int[][]>();
	}
	
	public void add(int[][] item) {
		data.add(AUtils.deepCopy(item));
	}
	
	public int[][] undo() {
		if(data.size() > 1) {
			return data.removeLast();
		}
		return null;
	}
	
	public int[][] current_state() {
		return data.getLast();
	}
}
