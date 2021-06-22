package ui.colors;

import java.awt.Color;
import java.util.HashMap;

public abstract class TetrisColors {
	private HashMap<Integer, Color> data;	
	private Color default_color = null;
	private int i = 0;
	
	public TetrisColors() {
		data = new HashMap<Integer, Color>();
	}
	
	public void add(String b) {
		i++;
		data.put(i, Color.decode(b));
	}
	
	public Color get(int index) {
		Color c = data.get(index);
		if(c == null) return this.default_color;
		return c;
	}
	
	public void setDefaultColor(Color c) {
		this.default_color = c;
	}
}
