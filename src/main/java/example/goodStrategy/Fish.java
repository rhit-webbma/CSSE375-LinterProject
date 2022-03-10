package example.goodStrategy;

public abstract class Fish {
	SwimBehavior swimBehavior1;
	OtherSwimBehavior swimBehavior2;
	String hi;
	
	public Fish(SwimBehavior swimmingBehavior1, OtherSwimBehavior swimmingBehavior2) {
		this.swimBehavior1 = swimmingBehavior1;
		this.swimBehavior2 = swimmingBehavior2;
		this.hi = "hello";
	}

	public void doSwim() {
		swimBehavior1.swim();
		swimBehavior2.swim();
	}
	
	abstract public void otherFunctionality();
}
