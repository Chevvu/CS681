package edu.umb.cs681.hw5;

import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Property {
	public static void main(String[] args) {
		List<List<String>> csvData = new ArrayList<>();
		Path path = Paths.get("PropertyPricing.csv");
		try (Stream<String> lines = Files.lines(path)) {
			csvData = lines.map(line -> {
				return Stream.of(line.split(",")).map(value -> value.substring(0)).collect(Collectors.toList());
			}).collect(Collectors.toList());
		} catch (Exception ex) {
			System.out.println("error" + ex);
		}
		
		System.out.println("Finding the minimum, Maximum and Average value of properties type : TWO-FAM DWELLING ");
		Double MaxValProperty3BHK = csvData.stream().skip(1).filter((row) -> row.get(12).contains("TWO-FAM DWELLING"))
				.mapToDouble((data) -> Double.parseDouble(data.get(29))).max().getAsDouble();
		Double MinValProperty3BHK = csvData.stream().skip(1).filter((row) -> row.get(12).contains("TWO-FAM DWELLING"))
				.mapToDouble((data) -> Double.parseDouble(data.get(29))).min().getAsDouble();
		Double AvgValProperty3BHK = csvData.stream().skip(1).filter((row) -> row.get(12).contains("TWO-FAM DWELLING"))
				.mapToDouble((data) -> Double.parseDouble(data.get(29))).average().getAsDouble();
		BigDecimal number = BigDecimal.valueOf(MaxValProperty3BHK);
        DecimalFormat decimalFormat = new DecimalFormat("#,###.##");
        String formattedNumber = decimalFormat.format(number);
		System.out.println("Maximum valued property :" + formattedNumber );
		System.out.println("Minimum valued property :" + MinValProperty3BHK );
		System.out.println("Average valued property :" + AvgValProperty3BHK );
		
		System.out.println("Finding the minimum, Maximum and Average value of properties in accordance with a street : PRINCETON ST");
		Double MaxValPropertyPrincetonST = csvData.stream().skip(1).filter((row) -> row.get(4).contains("PRINCETON ST"))
				.mapToDouble((data) -> Double.parseDouble(data.get(29))).max().getAsDouble();
		Double MinValPropertyPrincetonST = csvData.stream().skip(1).filter((row) -> row.get(4).contains("PRINCETON ST"))
				.mapToDouble((data) -> Double.parseDouble(data.get(29))).min().getAsDouble();
		Double AvgValPropertyPrincetonST = csvData.stream().skip(1).filter((row) -> row.get(4).contains("PRINCETON ST"))
				.mapToDouble((data) -> Double.parseDouble(data.get(29))).average().getAsDouble();
		System.out.println("Maximum valued property :" + MaxValPropertyPrincetonST );
		System.out.println("Minimum valued property :" + MinValPropertyPrincetonST );
		System.out.println("Average valued property :" + AvgValPropertyPrincetonST );
		
		System.out.println("Finding the minimum, Maximum and Average value of properties in accordance with a City : Mattapan");
		Double MaxValPropertyInMattapan = csvData.stream().skip(1).filter((row) -> row.get(6).contains("MATTAPAN"))
				.mapToDouble((data) -> Double.parseDouble(data.get(29))).max().getAsDouble();
		Double MinValPropertyInMattapan = csvData.stream().skip(1).filter((row) -> row.get(6).contains("MATTAPAN"))
				.mapToDouble((data) -> Double.parseDouble(data.get(29))).min().getAsDouble();
		Double AvgValPropertyInMattapan = csvData.stream().skip(1).filter((row) -> row.get(6).contains("MATTAPAN"))
				.mapToDouble((data) -> Double.parseDouble(data.get(29))).average().getAsDouble();
		BigDecimal numberMaxValPropertyInMattapan = BigDecimal.valueOf(MaxValPropertyInMattapan);
        DecimalFormat decimalFormatMaxValPropertyInMattapan = new DecimalFormat("#,###.##");
        String formattedNumberMaxValPropertyInMattapan = decimalFormatMaxValPropertyInMattapan.format(numberMaxValPropertyInMattapan);
		System.out.println("Maximum valued property :" + formattedNumberMaxValPropertyInMattapan );
		System.out.println("Minimum valued property :" + MinValPropertyInMattapan );
		System.out.println("Average valued property :" + AvgValPropertyInMattapan );
		
		
}
}
