package task2;

import java.util.*;

public class Teams implements Subject {

	private ArrayList<Observer> observer;
	
	
	public Teams(){
		observer = new ArrayList <Observer>();
	}
	
	public ArrayList<Observer> getPlayerList(){
		return observer;
	}
	
	@Override
	public void register(Observer object) {
		// TODO Auto-generated method stub
		observer.add(object); // register players to team
		
	}

	@Override
	public void notifyObs() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void getUpdates(Observer object) {
		// TODO Auto-generated method stub
		
		
		
	}

	
}
