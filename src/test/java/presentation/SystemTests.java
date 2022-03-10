package presentation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SystemTests {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testStyleChecks() {
		// Console Manager arguments input style
		// example.badClass$ example.superBadClass$
		
		System.out.println("\n-----------Testing Style Check Material------------\n");
		String[] classes = {"example.badClass$", "example.superBadClass$"};
		ConsoleManager.main(classes);
	}
	
	@Test
	void testRedundantInterfaces() {
		// Console Manager arguments input style
		// example.interfaces.Animal example.interfaces.Bird example.interfaces.Cat 
		// example.interfaces.ClassRunner example.interfaces.Dog example.interfaces.FlyingBirds 
		// example.interfaces.FlyingCats example.interfaces.MyList 
		// example.interfaces.NonRedundantInterface example.interfaces.RedundantInterface
		
		System.out.println("\n-----------Testing Redundant Interfaces Material------------\n");
		String[] classes = {"example.interfaces.Animal", "example.interfaces.Bird",
		                    "example.interfaces.Cat", "example.interfaces.ClassRunner",
		                    "example.interfaces.Dog", "example.interfaces.FlyingBirds",
		                    "example.interfaces.FlyingCats", "example.interfaces.MyList",
		                    "example.interfaces.NonRedundantInterface",
		                    "example.interfaces.RedundantInterface"};
		ConsoleManager.main(classes);
	}
	
	@Test
	void testFacadePattern() {
		// Console Manager arguments input style
		// example.goodFacade.CdPlayer example.goodFacade.DvdPlayer example.goodFacade.Lights 
		// example.goodFacade.Main example.goodFacade.MovieTheaterFacade example.goodFacade.PopcornMaker 
		// example.goodFacade.Projector example.goodFacade.Screen example.goodFacade.Speakers example.badFacade.CatFacade
		
		System.out.println("\n-----------Testing Facade Pattern Material------------\n");
		String [] classes = {"example.goodFacade.CdPlayer", "example.goodFacade.DvdPlayer", 
				"example.goodFacade.Lights", "example.goodFacade.Main", 
				"example.goodFacade.MovieTheaterFacade", "example.goodFacade.PopcornMaker", 
				"example.goodFacade.Projector", "example.goodFacade.Screen", 
				"example.goodFacade.Speakers", "example.badFacade.CatFacade"};
		ConsoleManager.main(classes);
	}
	
	@Test
	void testStrategyPattern() {
		// Console Manager arguments input style
		// example.goodStrategy.BlueFish example.goodStrategy.Fish example.goodStrategy.NoSwim 
		// example.goodStrategy.OneFish example.goodStrategy.RedFish 
		// example.goodStrategy.SwimBehavior example.goodStrategy.SwimFast 
		// example.goodStrategy.TwoFish
		
		System.out.println("\n-----------Testing Strategy Pattern Material------------\n");
		String[] classes = {"example.goodStrategy.BlueFish", "example.goodStrategy.Fish", 
				"example.goodStrategy.NoSwim", "example.goodStrategy.OneFish", 
				"example.goodStrategy.RedFish", "example.goodStrategy.SwimBehavior", 
				"example.goodStrategy.SwimFast", "example.goodStrategy.TwoFish"};
		ConsoleManager.main(classes);
	}
	
	@Test
	void testAdapterPattern() {
		System.out.println("\n-----------Testing Adapter Pattern Material------------\n");
		String[] classes = {"example.goodAdapter.Cat", "example.goodAdapter.Fish", "example.goodAdapter.CatToFishAdapter", 
				"example.badAdapter.FirstBadAdapter", "example.badAdapter.SecondBadAdapter", 
				"example.badAdapter.ThirdBadAdapter", "example.badAdapter.FourthBadAdapter", "example.badAdapter.FifthBadAdapter"};
		ConsoleManager.main(classes);
	}

}
