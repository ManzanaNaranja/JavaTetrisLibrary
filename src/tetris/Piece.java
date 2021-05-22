package tetris;

import java.awt.Color;

public enum Piece {

	
	I(new int[] {0x0F00, 0x2222, 0x00F0, 0x4444}, "#0F9BD7", 1),
	J(new int[] {0x8E00, 0x6440, 0x0E20, 0x44C0}, "#E35B02", 2),
	L(new int[] {0x2E00, 0x4460, 0x0E80, 0xC440}, "#2141C6", 3),
	O(new int[] {0xCC00, 0xCC00, 0xCC00, 0xCC00}, "#E39F02", 4),
	S(new int[] {0x6C00, 0x4620, 0x06C0, 0x8C40}, "#59B101", 5),
	T(new int[] {0x4E00, 0x4640, 0x0E40, 0x4C40}, "#AF298A", 6),
	Z(new int[] {0xC600, 0x2640, 0x0C60, 0x4C80}, "#D70F37", 7),
	Empty(null, "#000000", 0),
	Dead(null, "#6A6A6A", -1);
	private final int[] blocks;
	private final Color color;
	private int value;
	
	Piece(int[] blocks, String color, int value) {
		this.blocks = blocks;
		this.color = Color.decode(color);
		this.value = value;
	}
	
	public int[] getBlocks() {
		return blocks;
	}

	public Color getColor() {
		return color;
	}
	
	public static Piece getPieceByValue(int val) {
		for(Piece p : Piece.values()) {
			if(p.value == val) return p;
		}
		return null;
		
	}
	
	public int getValue() {
		return this.value;
	}
	
	public String toString(int index) {
		String res = String.format("%16s", Integer.toBinaryString(blocks[index])).replace(' ', '0');
		String[] arr = res.split("");
		for(int i = 3; i < res.length(); i+=4) {
			arr[i] = arr[i] + "\n";
		}
		return String.join("", arr);
	}
	
	public static Piece[] getAll() {
		Piece[] values = Piece.values();
		Piece[] arr = new Piece[values.length-1];
		for(int i = 0; i < values.length-1; i++) {
			arr[i] = values[i];
		}
		return arr;
	}
	

}
