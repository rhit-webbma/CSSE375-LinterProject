package example.goodFacade;

public class Main {

	public static void main(String[] args) {
		MovieTheaterFacade theater = new MovieTheaterFacade();
		System.out.println("---Playing movie---");
		theater.playMovie();
		System.out.println();
		System.out.println("---Pausing movie---");
		theater.pauseMovie();
		System.out.println();
		System.out.println("---Resuming movie---");
		theater.resumeMovie();
		System.out.println();
		System.out.println("---Ending Movie---");
		theater.endMovie();
	}
	
}
