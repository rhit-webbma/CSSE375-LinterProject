package example.goodAdapter;

public class CatToFishAdapter implements Fish{
	
	private Cat cat;
	
	public CatToFishAdapter(Cat cat) {
		this.cat = cat;
	}

	@Override
	public void swim() {
		this.cat.stalk();
	}

	@Override
	public void eat() {
		this.cat.attack();
	}

	@Override
	public void rest() {
		this.cat.sleep();
	}

	@Override
	public void beFish() {
		
	}

	@Override
	public int numScales() {
		return 0;
	}
}
