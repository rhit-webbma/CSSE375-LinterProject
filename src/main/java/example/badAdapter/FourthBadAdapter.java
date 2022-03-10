package example.badAdapter;

public class FourthBadAdapter implements Cat{
	
	private Fish fish;

	@Override
	public void stalk() {
		Dog dog = new Dog();
		dog.run();
	}

	@Override
	public void attack() {
		Dog dog = new Dog();
		dog.jump();
	}

	@Override
	public void sleep() {
		
	}

}
