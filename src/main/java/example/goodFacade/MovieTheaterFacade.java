package example.goodFacade;

public class MovieTheaterFacade {

	private PopcornMaker popcorn = new PopcornMaker();
	private DvdPlayer dvdPlayer = new DvdPlayer();
	private CdPlayer cdPlayer = new CdPlayer();
	private Lights lights = new Lights();
	private Projector projector = new Projector();
	private Screen screen = new Screen();
	private Speakers speakers = new Speakers();
	
	public void playMovie() {
		popcorn.turnOn();
		popcorn.makePopcorn();
		screen.lowerScreen();
		lights.dim();
		projector.turnOn();
		projector.wideScreenMode();
		speakers.turnOn();
		speakers.setVolume(10);
		dvdPlayer.turnOn();
		dvdPlayer.insertDvd();
		dvdPlayer.play();
	}
	
	public void endMovie() {
		dvdPlayer.stop();
		dvdPlayer.removeDvd();
		dvdPlayer.turnOff();
		lights.turnOn();
		speakers.turnOff();
		projector.turnOff();
		screen.raiseScreen();
		popcorn.turnOff();
	}
	
	public void pauseMovie() {
		dvdPlayer.pause();
		lights.turnOn();
	}
	
	public void resumeMovie() {
		lights.dim();
		dvdPlayer.resume();
	}
	
	public void playMusic() {
		cdPlayer.turnOn();
		cdPlayer.playMusic();
	}
	
	public void stopMusic() {
		cdPlayer.stopMusic();
		cdPlayer.turnOff();
	}
	
}
