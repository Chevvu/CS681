package edu.umb.cs681.hw01;

import java.util.stream.Collectors;
import java.util.*;

public class Car {

	private String make, model;
	private int mileage, year;
	private float price;
	public  List<Car> cars = new ArrayList<Car>();
    private int domination;

	public Car(String make, String model, int mileage, int year, float price) {
		this.make = make;
		this.model = model;
		this.mileage = mileage;
		this.year = year;
		this.price = price;
	}

	public String getMake() {
		return make;
	}

	public String getModel() {
		return model;
	}

	public int getMileage() {
		return mileage;
	}

	public int getYear() {
		return year;
	}

	public float getPrice() {
		return price;
	}


    public void setDominationCount(ArrayList<Car> myCars){
    	for (Car car : myCars) {
			if (!this.getModel().equals(car.getModel())) {
				if (this.getMileage() <= car.getMileage() && this.getPrice() >= car.getPrice()
						&& this.getYear() <= car.getYear()) {
					this.cars.add(car);
				}
			}
		}
    }
    
    public int getDominationCount(){
    	return this.cars.size();
    }
	

	public static void getPriceMinMaxAvg(List<Car> carList) {
		System.out.println("Minimum : "+carList.stream().map( (Car car)-> car.getPrice()).min( Comparator.comparing( price -> price )).get());
		System.out.println("Maximum : "+carList.stream().map( (Car car)-> car.getPrice()).max( Comparator.comparing( price -> price )).get());
		System.out.println("Average : "+ carList.stream().collect(Collectors.averagingDouble((Car car)->car.getPrice())));
	}
	
	public static void getYearMinMaxAvg(List<Car> carList) {

		System.out.println("Minimum : "+ carList.stream().map( (Car car)-> car.getYear()).min( Comparator.comparing( year -> year )).get());
		System.out.println("Maximum : "+ carList.stream().map( (Car car)-> car.getYear()).max( Comparator.comparing( year -> year )).get());
		System.out.println("Average : "+ Math.round(carList.stream().collect(Collectors.averagingDouble((Car car)->car.getYear()))));
		
	}

	public static void getMilageMinMaxAvg(List<Car> carList) {

		System.out.println("Minimum : "+ carList.stream().map( (Car car)-> car.getMileage()).min( Comparator.comparing( milage -> milage )).get());;
		System.out.println("Maximum : "+ carList.stream().map( (Car car)-> car.getMileage()).max( Comparator.comparing( milage -> milage )).get());
		System.out.println("Average : "+ Math.round(carList.stream().collect(Collectors.averagingDouble((Car car)->car.getMileage()))));
		
	}

	public static void getDominationCountMinMaxAvg(List<Car> carList) {
		if(carList.isEmpty()) {
			
		} else {

		System.out.println("Minimum : "+ carList.stream().map( (Car car)-> car.getDominationCount()).min( Comparator.comparing( price -> price )).get());;
		System.out.println("Maximum : "+ carList.stream().map( (Car car)-> car.getDominationCount()).max( Comparator.comparing( price -> price )).get());
		System.out.println("Average : "+ Math.round(carList.stream().collect(Collectors.averagingDouble((Car car)->car.getDominationCount()))));
		
		}
	}
	


	public static void main(String[] args) {

		ArrayList<Car> myCars = new ArrayList<Car>();
		myCars.add(new Car("Toyota", "Camry", 30, 2018, 31567));
		myCars.add(new Car("Honda", "Civic", 26, 2017, 16736));
		myCars.add(new Car("Toyota", "Prius", 49, 2019, 13598));
		myCars.add(new Car("Honda", "Accord", 32, 2022, 14899));

		

		
		System.out.println("******************* SORTING POLICY 1 : PRICE *******************");
		List<Car> sortedCarsPrice = myCars.stream().sorted(Comparator.comparing((Car car) -> car.getPrice())).collect(Collectors.toList());
		List<Car> highPricesGroup=sortedCarsPrice.stream().collect(Collectors.partitioningBy((Car car)->car.getPrice()>15000)).get(true);
		long highGroupCount = highPricesGroup.stream().count();
		System.out.println("=============================High group===========================");
		System.out.println("count : "+highGroupCount);
		for(Car car : highPricesGroup) {
			System.out.println("Car details : make of the car - "+car.getMake()+" model of the car - "+car.getModel());
		}
		getPriceMinMaxAvg(highPricesGroup);
		
		List<Car> lowPricesGroup = sortedCarsPrice.stream().collect(Collectors.partitioningBy((Car car)->car.getPrice()>15000)).get(false);
		long lowGroupCount = lowPricesGroup.stream().count();
		System.out.println("=============================Low group===========================");
		System.out.println("count : "+lowGroupCount);
		for(Car car : lowPricesGroup) {
			System.out.println("Car details : make of the car - "+car.getMake()+" model of the car - "+car.getModel());
		}
		getPriceMinMaxAvg(lowPricesGroup);
		

		
		System.out.println("******************* SORTING POLICY 2 : YEAR *******************");
		List<Car> sortedCarsYear = myCars.stream().sorted(Comparator.comparing((Car car) -> car.getYear()))
				.collect(Collectors.toList());
		List<Car> highYearGroup = sortedCarsYear.stream().collect(Collectors.partitioningBy((Car car)->car.getYear()>2018)).get(true);
		System.out.println("=============================High group===========================");
		long highYearGroupCount = highYearGroup.stream().count();
		System.out.println("count : "+highYearGroupCount);
		for(Car car : highYearGroup) {
			System.out.println("Car details : make of the car - "+car.getMake()+" model of the car - "+car.getModel());
		}
		getYearMinMaxAvg(highYearGroup);
		System.out.println("=============================Low group===========================");
		List<Car> lowYearGroup = sortedCarsYear.stream().collect(Collectors.partitioningBy((Car car)->car.getYear()>2018)).get(false);
		long lowYearGroupCount = lowYearGroup.stream().count();
		System.out.println("count : "+lowYearGroupCount);
		for(Car car : lowYearGroup) {
			System.out.println("Car details : make of the car - "+car.getMake()+" model of the car - "+car.getModel());
		}
		getYearMinMaxAvg(lowYearGroup);
		

		
		System.out.println("******************* SORTING POLICY 3 : MILAGE *******************");
		List<Car> sortedCarsMileage = myCars.stream().sorted(Comparator.comparing((Car car) -> car.getMileage())).collect(Collectors.toList());
		List<Car> highMilageGroup = sortedCarsMileage.stream().collect(Collectors.partitioningBy((Car car)->car.getMileage()>30)).get(true);
		System.out.println("=============================High group===========================");
		long highMilageGroupCount = highMilageGroup.stream().count();
		System.out.println("count : "+highMilageGroupCount);
		for(Car car : highMilageGroup) {
			System.out.println("Car details : make of the car - "+car.getMake()+" model of the car - "+car.getModel());
		}
		getMilageMinMaxAvg(highMilageGroup);
		
		System.out.println("=============================Low group===========================");
		List<Car> lowMilageGroup = sortedCarsMileage.stream().collect(Collectors.partitioningBy((Car car)->car.getMileage()>30)).get(false);
		long lowMilageGroupCount = lowMilageGroup.stream().count();
		System.out.println("count : "+lowMilageGroupCount);
		for(Car car : lowMilageGroup) {
			System.out.println("Car details : make of the car - "+car.getMake()+" model of the car - "+car.getModel());
		}
		getMilageMinMaxAvg(lowMilageGroup);


		

		
		System.out.println("******************* SORTING POLICY 4  : DOMINATION *******************");
		List<Car> sortedCarsDomination = myCars.stream().sorted(Comparator.comparing((Car car) -> car.getDominationCount())).collect(Collectors.toList());
		List<Car> highDominationGroup = sortedCarsDomination.stream().collect(Collectors.partitioningBy((Car car)->car.getDominationCount()>1)).get(true);
		System.out.println("=============================High group===========================");
		long highDominationGroupCount = highDominationGroup.stream().count();
		System.out.println("count : "+highDominationGroupCount);
		for(Car car : highDominationGroup) {
			System.out.println("Car details : make of the car - "+car.getMake()+" model of the car - "+car.getModel());
		}
		getDominationCountMinMaxAvg(highDominationGroup);
		System.out.println("=============================Low group===========================");
		List<Car> lowDominationGroup = sortedCarsDomination.stream().collect(Collectors.partitioningBy((Car car)->car.getDominationCount()>1)).get(false);
		long lowDominationGroupCount = lowDominationGroup.stream().count();
		System.out.println("count : "+lowDominationGroupCount);
		for(Car car : lowDominationGroup) {
			System.out.println("Car details : make of the car - "+car.getMake()+" model of the car - "+car.getModel());
		}
		getDominationCountMinMaxAvg(lowDominationGroup);

		
	}
}