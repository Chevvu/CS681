package edu.umb.cs681.hw13.ThreadSafe;

public interface WaterTank  {
	public void IntakeLiters(double liters);
	public void OutflowLiters(double liters);
	public double getTankStatus();
}
