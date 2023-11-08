package edu.umb.cs681.hw03;
import java.util.*;

public class CarPriceResultHolder {
	private double average;
	private int numCarExamined;
	
	public double getAverage() {
		return average;
	}
	
	public static void main(String[]args) {
		

		ArrayList<Car> myCars = new ArrayList<Car>();
		myCars.add(new Car("Toyota", "Camry", 30, 2018, 31567));
		myCars.add(new Car("Honda", "Civic", 26, 2017, 16736));
		myCars.add(new Car("Toyota", "Prius", 49, 2019, 13598));
		myCars.add(new Car("Honda", "Accord", 32, 2022, 14899));

		
      Double averagePrice = myCars.stream()
              .map(singleCar->singleCar.getPrice())
              .reduce(new CarPriceResultHolder(),(result, price) -> {
              	double avg = ((result.numCarExamined*result.average)+price)/(++result.numCarExamined);
              	result.average = avg;
              	return result;
              },(finalResult,intermediateResult)->finalResult).getAverage();
      
      System.out.println("The average of all the cars using the 3 Versions of reduce : "+averagePrice);
		
	}


}
