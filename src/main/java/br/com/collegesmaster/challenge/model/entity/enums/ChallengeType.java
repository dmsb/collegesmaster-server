package br.com.collegesmaster.challenge.model.entity.enums;

public enum ChallengeType {
	NORMAL('N'),
	KILL_KILL('K'), 
	DETERMINED_TIME('D');
	
	private final Character abbr;
	
	private ChallengeType(Character abbr) {
		this.abbr = abbr;
	}
	
	public Character getLetter() {
		return abbr;
	}
}
