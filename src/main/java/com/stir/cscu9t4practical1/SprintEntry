package com.stir.cscu9t4practical1;

public class SprintEntry extends Entry {
	
	protected int reps; 
	protected int recovery;
	

	public SprintEntry(String n, int d, int m, int y, int h, int min, int s, float dist, int reps, int recovery) {
		super(n, d, m, y, h, min, s, dist);
		this.reps = reps;
		this.recovery = recovery;
		// TODO Auto-generated constructor stub
	}
	
	public int getRepetitions() {
		return reps;
	}
	
	public int getRecovery() {
		return recovery;
	}

	public String getEntry() {
	
		String result = getName() + " sprinted " + getRepetitions() + "x" + (int)getDistance() + "m in " +getHour()+":"+getMin()+":"+ getSec() + " with " + getRecovery() + " minutes recovery" 
				+ " on "  +getDay()+"/"+getMonth()+"/"+getYear()+"\n";
		return result;
	}
	
}
