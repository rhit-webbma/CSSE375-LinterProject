package example.goodFacade;

public class Projector {

	private Speakers speakers;
	private Screen screen;
	private DvdPlayer dvdPlayer;
	private CdPlayer cdPlayer;
	
	public void turnOn() {
		System.out.println("Turning on projector");
	}
	
	public void turnOff() {
		System.out.println("Turning off projector");
	}
	
	public void tvMode() {
		System.out.println("Setting projector to tv mode");
	}
	
	public void wideScreenMode() {
		System.out.println("Setting projector to wide screen mode");
	}
}
