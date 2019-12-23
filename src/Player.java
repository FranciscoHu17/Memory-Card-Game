
public class Player {
	public String name;
	private int score;
	
	public Player(String input) {
		name = input;
		score = 0;
	}
	public void setScore(int s) {
		score = s;
	}
	public int getScore(){
		return score;
	}
	public void addPoint() {
		score++;
	}
	
}
