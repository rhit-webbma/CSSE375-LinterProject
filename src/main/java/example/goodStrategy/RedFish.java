package example.goodStrategy;

public class RedFish extends Fish {

	public RedFish(SwimBehavior swimBehavior1, OtherSwimBehavior swimBehavior2) {
		super(swimBehavior1, swimBehavior2);
	}

	@Override
	public void otherFunctionality() {
		// TODO Auto-generated method stub
		String fish = "Red Fish String";
		this.hi = "newValue"; // Hollywood Violation
	}
	
}
