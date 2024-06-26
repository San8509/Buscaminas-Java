package modelos;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Santiago clase puntuacion
 */
public class Score {

	private List<Integer> score;
	
	public Score() {
		this.score= new ArrayList<Integer>();
	}

	public List<Integer> getScore() {
		return score;
	}

	public void setScore( int score) {
		this.score.add(score);
	}	
	
}
