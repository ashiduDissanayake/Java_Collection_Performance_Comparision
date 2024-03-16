import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        long[][] avgTimes = new long[10][4];
        for(int t = 0; t < 100; t++){
            int numberOfItems = 100;

            // Create a list of Java Collection implementations
            List<Collection<Integer>> collections = new ArrayList<>();
            collections.add(new HashSet<>());
            collections.add(new TreeSet<>());
            collections.add(new LinkedHashSet<>());
            collections.add(new ArrayList<>());
            collections.add(new LinkedList<>());
            collections.add(new ArrayDeque<>());
            collections.add(new PriorityQueue<>());

            // Load each collection with 100,000 random Integer objects

            for (int i = 0; i < numberOfItems; i++) {
                int value = (int) (Math.random() * numberOfItems);
                for (Collection<Integer> collection : collections) {
                    collection.add(value);
                }

            }
            for (Collection<Integer> collection : collections) {
                System.out.println(collection);
            }
            List<Integer> array = new ArrayList<>(collections.get(3));

            // Test the map implementations
            List<Map<Integer, Integer>> maps = new ArrayList<>();
            maps.add(new HashMap<>());
            maps.add(new TreeMap<>());
            maps.add(new LinkedHashMap<>());

            //Load each map with 100,000 random key-value pairs
            for (Map<Integer, Integer> map : maps) {
                int value = (int) (Math.random() * numberOfItems);
                for (int i = 0; i < numberOfItems; i++) {
                    map.put((int) (i+1),value);
                }
            }





            // Perform the tests for each method and print the time taken
            for (int i = 0; i < collections.size(); i++) {
                avgTimes[i][0] += (testAdd(collections.get(i)));
                avgTimes[i][1] += (testContains(collections.get(i)));
                avgTimes[i][2] += (testRemove(collections.get(i)));
                avgTimes[i][3] += (testClear(collections.get(i)));
            }

            // Perform the tests for each method and print the time taken
            for (int i = 7; i < maps.size()+7; i++) {
                avgTimes[i][0] += (testPut(maps.get(i-7)));
                avgTimes[i][1] += (testContainsKey(maps.get(i-7)));
                avgTimes[i][2] += (testRemoveMap(maps.get(i-7)));
                avgTimes[i][3] += (testClearMap(maps.get(i-7)));
            }

        }
        for(long[] arr : avgTimes){
            for(long l : arr){
                System.out.printf("%d ,", l/100);
            }
            System.out.println();
        }
        graphPlot(avgTimes);
        System.out.println("Graph.txt has been created");
    }

    // Define the missing methods
    public static long testAdd(Collection<Integer> collection) {
        long startTime = System.nanoTime();
        collection.add((int) (Math.random() * 100000));
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    public static long testContains(Collection<Integer> collection) {
        long startTime = System.nanoTime();
        collection.contains((int) (Math.random() * 100000));
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    public static long testRemove(Collection<Integer> collection) {
        long startTime = System.nanoTime();
        collection.remove((int) (Math.random() * 100000));
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    public static long testClear(Collection<Integer> collection) {
        long startTime = System.nanoTime();
        collection.clear();
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    public static long testPut(Map<Integer, Integer> map) {
        long startTime = System.nanoTime();
        map.put((int) (Math.random() * 100000), (int) (Math.random() * 100000));
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    public static long testContainsKey(Map<Integer, Integer> map) {
        long startTime = System.nanoTime();
        map.containsKey((int) (Math.random() * 100000));
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    public static long testRemoveMap(Map<Integer, Integer> map) {
        long startTime = System.nanoTime();
        map.remove((int) (Math.random() * 100000));
        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    public static long testClearMap(Map<Integer, Integer> map) {
        long startTime = System.nanoTime();
        map.clear();
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
    public static void graphPlot(long[][] avgTimes) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("Graph.txt"));
            for(long[] arr : avgTimes){
                for(long l : arr){
                    writer.write(String.valueOf(l));
                    writer.write("   ");
                }
                writer.write("\n");
            }
            writer.close();

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
