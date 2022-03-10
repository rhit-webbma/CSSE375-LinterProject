package example.goodFacade;

public class DvdPlayer {

	public void turnOn() {
		System.out.println("Dvd player turned on");
	}
	
	public void turnOff() {
		System.out.println("Dvd player turned off");
	}
	
	public void insertDvd() {
		System.out.println("Inserted Dvd");
	}
	
	public void removeDvd() {
		System.out.println("Removed Dvd");
	}
	
	public void play() {
		System.out.println("Playing Dvd");
	}
	
	public void pause() {
		System.out.println("Paused Dvd");
	}
	
	public void resume() {
		System.out.println("Resumed Dvd");
	}
	
	public void stop() {
		System.out.println("Stopped Dvd");
	}
}
