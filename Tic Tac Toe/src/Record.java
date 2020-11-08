/**
 * #251036612
 * @author Shehzer Naumani
 *
 */
public class Record {
	String configuration;
	int score;
	/**
	 * constructor which instatiates config and score
	 * @param Config
	 * @param score
	 */
	public Record(String Config, int score) {
		this.configuration=Config;
		this.score=score;
		
	}
	/**
	 * 
	 * @return configuration
	 */
	public String getConfig() {
		return this.configuration;
	}
	/**
	 * 
	 * @return score
	 */
	public int getScore() {
		return this.score;
	}
	
	
}
