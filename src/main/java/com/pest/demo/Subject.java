package task2;

public interface Subject {
	
	public void register(Observer object);
	
	public void notifyObs();
	
	public void getUpdates(Observer object);
	
}
