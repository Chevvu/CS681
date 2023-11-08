package edu.umb.cs681.hw02;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Person {
	private String firstName, lastName;
	private LocalDate dob;
	private LinkedList<Dose> listOfDoses;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        System.out.println("Calculating the vaccination rate for each age category.");
        List<Person> people = generatePersons(1000);

        // Calculate the vaccination rate for this community.
        long fullyVacCount = people.stream()
                .filter(p -> p.getAge(p.getDob()) >= 18 && p.getVacCount() >= 3)
                .count();
        double vacRate = (double) fullyVacCount / people.size() * 100;
        System.out.println("The vaccination rate for the community: " + vacRate + "%");

        // Calculate the vaccination rate for each age category.
        // Use Collectors.groupingBy().
        Map<AgeCat, Long> fullyVacCountByAgeCategory = people.stream()
                .filter(p -> p.getVacCount() >= 3)
                .collect(Collectors.groupingBy(Person::getAgeCat, Collectors.counting()));
        Map<AgeCat, Long> countByAgeCategory = people.stream()
                .collect(Collectors.groupingBy(Person::getAgeCat, Collectors.counting()));
        for (AgeCat ageCat : AgeCat.values()) {
            float vacCount=fullyVacCountByAgeCategory.get(ageCat);
            float personCount=countByAgeCategory.get(ageCat);
            float rate=  (vacCount / personCount)*100;
            System.out.println("Rate of vacination for " + ageCat + " : " + rate  );
        }
        
        // Calculate the average # of vaccinations administered in each age category.
        // Use Collectors.groupingBy().
        var averageVaccinationsByAgeCategory = people.stream()
                .collect(Collectors.groupingBy(Person::getAgeCat,
                        Collectors.averagingInt(Person::getVacCount)));
        System.out.println("Average number of vaccinations by age category: " + averageVaccinationsByAgeCategory);
        
        
        // Calculate the average age of the people who have never been vaccinated.
        // Use Collectors.partitioningBy().
        var averageAgeOfUnvaccinated = people.stream()
                .collect(Collectors.partitioningBy(p -> p.getVacCount() == 0,
                        Collectors.averagingInt(p -> p.getAge(p.getDob()))));

        
        System.out.println("Average age of unvaccinated people: " + averageAgeOfUnvaccinated.get(true));
		
	}
	
	private static List<Person> generatePersons(int count) {
        List<Person> people = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            String firstName = "First" + i;
            String lastName = "Last" + i;
            int year = random.nextInt(70) + 1950; // Random year of birth between 1950 and 2019
            int month = random.nextInt(12) + 1;
            int day = random.nextInt(28) + 1;
            LocalDate dob = LocalDate.of(year, month, day);
            LinkedList<Dose> doses = generateDoses(random.nextInt(3)+1);
            Person person = new Person(firstName, lastName, dob, doses);
            people.add(person);
        }
        return people;
    }

    private static LinkedList<Dose> generateDoses(int count) {
        LinkedList<Dose> doses = new LinkedList<>();
        Random random = new Random();
        IntStream.range(0, count).forEach(i -> {
            Dose dose = new Dose();
            dose.setVacProductName("Vaccine" + i);
            dose.setLotNumber("Lot" + i);
            dose.setDate(LocalDate.now());
            dose.setVacSite("Site" + i);
            doses.add(dose);
        });
        return doses;
    }

//	public LinkedList<Dose> getListOfDoses() {
//		return listOfDoses;
//	}

	public void setListOfDoses(LinkedList<Dose> listOfDoses) {
		this.listOfDoses = listOfDoses;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	
	public int getAge(LocalDate dob) {
		  LocalDate currentDate = LocalDate.now();

		  // Calculate the period between the person's date of birth and the current date.
		  Period period = Period.between(dob, currentDate);

		  // Get the number of years in the period.
		  int age = period.getYears();

		  // Return the person's age.
		  return age;
	}
	
	public AgeCat getAgeCat() {
		int age=this.getAge(this.dob);
		AgeCat category;
		if(age<=18){
			category=AgeCat.YOUNG;
			
		}
		else if(age>18 && age<=40) {
			category=AgeCat.MID;
		}
		else {
			category=AgeCat.OLD;
		}
		return category;
	}


	
	public Person(String firstName, String lastName, LocalDate dob, LinkedList<Dose> listOfDoses) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.listOfDoses = listOfDoses;
	}

	public LinkedList<Dose> getDoses(){
		return this.listOfDoses;
		
	}
	public int getVacCount() {
		return this.getDoses().size();
	}

}
