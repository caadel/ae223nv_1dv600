package project;
/*
 * Other motifs can be added instead of the hangman in the future 
 * if a more child-friendly version were to be developed.
 */
public class Drawing {

	private String[] drawings;
	
	public Drawing() {
		drawings = new String[12];
	}
	
	public String get(int index) { 
		if (index < 0 || index >= drawings.length)
			throw new IndexOutOfBoundsException("Index must be between 0 and 12!");
		
		return drawings[index];
	}
	
	public void setDrawingToHangman() {
		drawings[0]  = "  ___________________\n |                   |\n |                   |\n |                   |\n |                   |\n |                   |\n |                   |\n |                   |\n |                   |\n |___________________|\n";
		drawings[1]  = "  ___________________\n |                   |\n |                   |\n |                   |\n |                   |\n |                   |\n |                   |\n |           ___     |\n |          /   \\    |\n |___________________|\n";
		drawings[2]  = "  ___________________\n |                   |\n |            |      |\n |            |      |\n |            |      |\n |            |      |\n |            |      |\n |           _|_     |\n |          /   \\    |\n |___________________|\n";
		drawings[3]  = "  ___________________\n |      _______      |\n |            |      |\n |            |      |\n |            |      |\n |            |      |\n |            |      |\n |           _|_     |\n |          /   \\    |\n |___________________|\n";
		drawings[4]  = "  ___________________\n |      _______      |\n |      |     |      |\n |            |      |\n |            |      |\n |            |      |\n |            |      |\n |           _|_     |\n |          /   \\    |\n |___________________|\n";
		drawings[5]  = "  ___________________\n |      _______      |\n |     _|_    |      |\n |    |x x|   |      |\n |    |_�_|   |      |\n |            |      |\n |            |      |\n |           _|_     |\n |          /   \\    |\n |___________________|\n";
		drawings[6]  = "  ___________________\n |      _______      |\n |     _|_    |      |\n |    |x x|   |      |\n |    |_�_|   |      |\n |      |     |      |\n |      |     |      |\n |           _|_     |\n |          /   \\    |\n |___________________|\n";
		drawings[7]  = "  ___________________\n |      _______      |\n |     _|_    |      |\n |    |x x|   |      |\n |    |_�_|   |      |\n |      |     |      |\n |     /|     |      |\n |           _|_     |\n |          /   \\    |\n |___________________|\n";
		drawings[8]  = "  ___________________\n |      _______      |\n |     _|_    |      |\n |    |x x|   |      |\n |    |_�_|   |      |\n |      |     |      |\n |     /|\\    |      |\n |           _|_     |\n |          /   \\    |\n |___________________|\n";
		drawings[9]  = "  ___________________\n |      _______      |\n |     _|_    |      |\n |    |x x|   |      |\n |    |_�_|   |      |\n |      |     |      |\n |     /|\\    |      |\n |     /     _|_     |\n |          /   \\    |\n |___________________|\n";
		drawings[10] = "  ___________________\n |      _______      |\n |     _|_    |      |\n |    |x x|   |      |\n |    |_�_|   |      |\n |      |     |      |\n |     /|\\    |      |\n |     / \\   _|_     |\n |          /   \\    |\n |___________________|\n";
		drawings[11] = "  ___________________\n |                   |\n |    ___________    |\n |   |  _     _  |   |\n |   | |_|   |_| |   |\n |   | 	         |   |\n |   |  \\_____/  |   |\n |   |___________|   |\n |                   |\n |___________________|\n";
	}

}
