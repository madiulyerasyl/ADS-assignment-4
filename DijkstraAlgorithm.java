import java.util.*;

public class DijkstraAlgorithm {

    static final int INF = Integer.MAX_VALUE;

    static String[] cities = {
            "Edinburgh",
            "Glasgow",
            "Stirling",
            "Perth",
            "Dundee"
    };

    static int[][] roadDistances = {

            {0,70,50,100,0},
            {70,0,50,0,0},
            {50,50,0,40,0},
            {100,0,40,0,60},
            {0,0,0,60,0}
    };

    public static void main(String[] args) {

        findShortestPath(0);
    }

    public static void findShortestPath(int startCity) {

        int numberOfCities = cities.length;

        int[] shortestDistance = new int[numberOfCities];

        boolean[] visitedCity = new boolean[numberOfCities];

        int[] previousCity = new int[numberOfCities];

        for (int index = 0; index < numberOfCities; index++) {

            shortestDistance[index] = INF;

            previousCity[index] = -1;
        }

        shortestDistance[startCity] = 0;

        for (int count = 0; count < numberOfCities - 1; count++) {

            int currentCity = findMinimumDistance(shortestDistance, visitedCity);

            visitedCity[currentCity] = true;

            for (int neighbour = 0; neighbour < numberOfCities; neighbour++) {

                if (!visitedCity[neighbour]
                        && roadDistances[currentCity][neighbour] != 0
                        && shortestDistance[currentCity] != INF
                        && shortestDistance[currentCity] + roadDistances[currentCity][neighbour]
                        < shortestDistance[neighbour]) {

                    shortestDistance[neighbour] =
                            shortestDistance[currentCity]
                                    + roadDistances[currentCity][neighbour];

                    previousCity[neighbour] = currentCity;
                }
            }
        }

        System.out.println("Shortest Distance = "
                + shortestDistance[4]);

        System.out.print("Shortest Path: ");

        printPath(previousCity, 4);

        System.out.println();
    }

    public static int findMinimumDistance(int[] shortestDistance,
                                          boolean[] visitedCity) {

        int minimumDistance = INF;

        int cityIndex = -1;

        for (int index = 0; index < shortestDistance.length; index++) {

            if (!visitedCity[index]
                    && shortestDistance[index] < minimumDistance) {

                minimumDistance = shortestDistance[index];

                cityIndex = index;
            }
        }

        return cityIndex;
    }

    public static void printPath(int[] previousCity,
                                 int destination) {

        if (previousCity[destination] == -1) {

            System.out.print(cities[destination]);

            return;
        }

        printPath(previousCity, previousCity[destination]);

        System.out.print(" -> " + cities[destination]);
    }
}