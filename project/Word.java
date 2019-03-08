package project;
public class Word implements Comparable<Word>{
	private String string;
	private int length;
	private HiddenString hs;
	
	public Word() {
		string = "";
		length = 0;
		hs = new HiddenString();
	}
	public Word(String string) {
		this.string = string;
		length = string.length();
		hs = new HiddenString();
	}
	private Word(String string, int i) { // Only used by hidden word class
		this.string = string;
		length = string.length();
	}
	
	public void setWord(String string) {
		this.string = string;
		length = string.length();
		hs = new HiddenString();
	}
	public Word getHiddenWord() {return hs.toWord();}
	public void updateHiddenWordIfContains(char c) {hs.updateHiddenWordIfContains(c);}
	public void add(char c) {
		string = string + c;
		length = string.length();
	}
	public void add(String string) {
		this.string = this.string + string;
		length = this.string.length();
	}
	public char charAt(int position) {return string.charAt(position);}
	public int length() {return length;}
	public boolean contains(char c) {
		for (int i = 0; i < string.length(); i++)
			if (string.charAt(i) == c)
				return true;
		return false;
	}
	@Override
	public String toString() {return string;}
	@Override
	public int compareTo(Word w) {return string.compareTo(w.toString());}
	
	// PRIVATE INNER CLASS
	private class HiddenString {
		private String hiddenString;
		
		private HiddenString() {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < string.length(); i++) {
				if (string.charAt(i) == '-')
					sb.append("- ");
				else 
					sb.append("_ ");
			}
			hiddenString = sb.toString();
		}
		private void updateHiddenWordIfContains(char c) {
			for (int i = 0; i < string.length(); i++) {
				if (string.charAt(i) == c) {
					StringBuilder sb = new StringBuilder(hiddenString);
					sb.replace(i*2, (i*2)+1, Character.toString(c));
					hiddenString = sb.toString();
				}
			}
		}
		private Word toWord() {return new Word(hiddenString,1);}
	}

}
