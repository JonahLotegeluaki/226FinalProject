package structures;

import java.util.List;
import java.util.ArrayList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class HospitalGraph {

    private Map<String, Map<String, Double>> graph;

    public HospitalGraph() {
        graph = new HashMap<>();
    }

    public void addLocation(String location) {
        graph.putIfAbsent(location, new HashMap<>());
        // Add a vertex
    }

    public void addConnection(String from, String to, double time) {
        addLocation(from);
        addLocation(to);
        graph.get(from).put(to, time);
        graph.get(to).put(from, time);
    }

    public Map<String, Double> getNeighbors(String location) {
        for (String loc : graph.keySet()) {
            if (loc.equals(location)) {
                return graph.get(loc);
            }
        }
        // Return adjacent locations and travel times
        return new HashMap<>();
    }

    public boolean containsLocation(String location) {
        return graph.containsKey(location);
    }

    public Set<String> getLocations() {
        return new HashSet<>(graph.keySet());
    }

    public int getConnectionCount() {
        int count = 0;
        for (Map<String, Double> neighbors : graph.values()) {
            count += neighbors.size();
        }
        return count/2;
    }
    

    
    public int getLocationCount() {
        return graph.size();
    }

    public List<String> findShortestPath(String start, String end) {

        if (!containsLocation(start) || !containsLocation(end)) {
            return new ArrayList<>();
        }

       Map<String, Double> distances = new HashMap<>();

        Map<String, String> previous = new HashMap<>();

        Set<String> visited = new HashSet<>();

        //Initialize distance map
        for (String location : graph.keySet()) {
                distances.put(location, Double.POSITIVE_INFINITY);
            }
        //Set our home base
        distances.put(start, 0.0);


        while (visited.size() < distances.size()) {

            String current = null;
            double smallestDistance = Double.POSITIVE_INFINITY;
            
            //Find our shortest unvisited path
            for (Map.Entry<String, Double> entry : distances.entrySet()) {
                if (entry.getValue() < smallestDistance && !visited.contains(entry.getKey())) {
                    smallestDistance = entry.getValue();
                    current = entry.getKey();
                }
            }

            if (current == null) {
                break;
            }

            //Go through our neighboring entries (this part sucked to code just so you guys know)
            for (Map.Entry<String, Double> NeighboringEntry : graph.get(current).entrySet()) {
                if (distances.get(NeighboringEntry.getKey()) > distances.get(current) + NeighboringEntry.getValue()) {
                    distances.put(NeighboringEntry.getKey(), distances.get(current) + NeighboringEntry.getValue());
                    previous.put(NeighboringEntry.getKey(), current);
                }
            }

            visited.add(current);

            if(current.equals(end)) {
                break;
            }


        }

        if (!visited.contains(end)) {
            return new ArrayList<>();
        }
        // Reconstruct the shortest path
        List<String> path = new ArrayList<>();
        String current = end;
        while (current != null) {
            path.add(0, current);
            current = previous.get(current);
        }
        return path;
    }

    




    public void loadGraph(String filePath) throws IOException {

        for (String line : Files.readAllLines(
                Path.of(filePath))) {

            String[] parts = line.split(",");
            
            if (parts.length == 1) {
                addLocation(parts[0]);
            } else if (parts.length == 3) {

                String from = parts[0];
                String to = parts[1];
                double weight = Double.parseDouble(parts[2]);

                addConnection(from, to, weight);
            }
        }
    }



    public double getRouteCost(List<String> path) {

        double cost = 0.0;

        for (int i = 0; i < path.size() - 1; i++) {

            String from = path.get(i);
            String to = path.get(i + 1);

            cost += graph.get(from).get(to);
        }

        return cost;
    }
}
