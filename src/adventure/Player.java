package adventure;

import java.util.Date;

public class Player extends Actor
{
	private int score;
	private Date dateStarted;
	private Date dateLastPlayed;
	
	/**
	 * Use this constructor for resuming a game
	 * @param name name of the player
	 * @param dateStarted date that the player started the game
	 * @param score the current score of the game
	 */
	public Player(String name, Date dateStarted, int score)
	{
		super(name);
		this.dateStarted = dateStarted;
		this.score = score;
		this.dateLastPlayed = new Date();
	}
	
	public Player(String name)
	{
		super(name);
		this.score = 0;
		this.dateStarted = new Date();
		this.dateLastPlayed = new Date();
	}

	public int getScore() {
		return score;
	}

	public Date getDateStarted() {
		return dateStarted;
	}

	public Date getDateLastPlayed() {
		return dateLastPlayed;
	}
}
