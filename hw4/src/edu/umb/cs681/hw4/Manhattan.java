package edu.umb.cs681.hw4;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class Manhattan implements DistanceMetric{

//    @Override
//    public double distance(List<Double> p1, List<Double> p2) {
//        double distance = 0d;
//        if(p1.size() == p2.size()){
//            for(int i = 0; i < p1.size(); i++){
//                distance = distance + Math.abs(p1.get(i) - p2.get(i));
//            }
//            return distance;
//        } else {
//            return -1;
//        }
//
//    }
    @Override
    public double distance(List<Double> p1, List<Double> p2) {
        if (p1.size() == p2.size()) {
        	double distance = IntStream.range(0, p1.size())
                    .mapToDouble(i -> Math.abs(p1.get(i) - p2.get(i)))
                    .sum();
            return distance;
        } else {
        	return -1;
        }
        
    }
    
    public static void main(String[] args) {
        // Generate random data for testing
        List<List<Double>> points = generateRandomData(1000, 100);

        // Calculate the distance matrix using the Euclidean distance metric
        List<List<Double>> distanceMatrix = Distance.matrix(points, new Manhattan());

        // Print the distance matrix
        System.out.println("The distance matrix for the points are :");
        for (List<Double> row : distanceMatrix) {
            System.out.println(row);
        }
    }

    private static List<List<Double>> generateRandomData(int numPoints, int numDimensions) {
        List<List<Double>> points = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < numPoints; i++) {
            List<Double> point = new ArrayList<>();
            for (int j = 0; j < numDimensions; j++) {
                point.add(random.nextDouble());
            }
            points.add(point);
        }

        return points;
    }
}
