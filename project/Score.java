package project;

public class Score implements Comparable<Score>{
	private int score;
	private String name;
	
	public Score(String name, int score) {
		this.name = name;
		this.score = score;
	}
	
	public String getName() {return name;}
	public int getScore() {return score;}
	@Override
	public int compareTo(Score s) {return score - s.getScore();}
}
