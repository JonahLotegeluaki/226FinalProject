package structures;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class HospitalGraphTest {

    private HospitalGraph graph;

    @BeforeEach
    public void setUp() {
        graph = new HospitalGraph();

        graph.addConnection("Entrance", "WaitingRoom", 0.1);
        graph.addConnection("Reception", "WaitingRoom", 0.1);
        graph.addConnection("WaitingRoom", "ER", 0.2);
        graph.addConnection("ER", "PatientRooms", 0.4);
        graph.addConnection("WaitingRoom", "Triage", 0.5);
        graph.addConnection("Triage", "ICU", 1.0);
        graph.addConnection("Triage", "Surgery", 0.8);
        graph.addConnection("Triage", "PatientRooms", 1.2);
        graph.addConnection("Radiology", "Pharmacy", 3.0);
        graph.addConnection("PatientRooms", "Radiology", 0.2);
        graph.addConnection("PatientRooms", "Staff", 0.45);
        graph.addConnection("Admin", "Staff", 0.1);
        graph.addConnection("Staff", "Radiology", 0.3);
        graph.addConnection("Radiology", "ICU", 0.3);
        graph.addConnection("ICU", "Surgery", 0.3);
        graph.addConnection("PatientRooms", "Lab", 0.5);
        graph.addConnection("Staff", "Lab", 0.2);
        graph.addConnection("Lab", "Chapel", 5.0);
        graph.addConnection("Chapel", "PatientRooms", 0.5);
        graph.addConnection("Pharmacy", "PatientRooms", 0.8);
        graph.addConnection("Chapel", "Pharmacy", 0.3);
        graph.addConnection("Surgery", "Pharmacy", 1.0);
        graph.addConnection("WaitingRoom", "ER", 0.4);

        graph.addLocation("Storage");
    }

    @Test
    public void testAddLocation() {
        graph.addLocation("Office");

        assertTrue(graph.getNeighbors("Office").isEmpty());
    }

    @Test
    public void testAddConnection() {
        graph.addConnection("Entrance", "Office", 0.5);

        assertEquals(0.5,
                graph.getNeighbors("Entrance").get("Office"),
                0.0001);
    }

    @Test
    public void testUndirectedConnection() {
        assertEquals(0.1,
                graph.getNeighbors("Entrance").get("WaitingRoom"),
                0.0001);

        assertEquals(0.1,
                graph.getNeighbors("WaitingRoom").get("Entrance"),
                0.0001);
    }

    @Test
    public void testMultipleNeighbors() {
        Map<String, Double> neighbors = graph.getNeighbors("WaitingRoom");

        assertEquals(4, neighbors.size());

        assertTrue(neighbors.containsKey("Entrance"));
        assertTrue(neighbors.containsKey("Reception"));
        assertTrue(neighbors.containsKey("ER"));
        assertTrue(neighbors.containsKey("Triage"));
    }

    @Test
    public void testDuplicateConnection() {
        assertEquals(0.4,
                graph.getNeighbors("WaitingRoom").get("ER"),
                0.0001);
    }

    @Test
    public void testDuplicateLocation() {
        graph.addLocation("WaitingRoom");

        assertEquals(4,
                graph.getNeighbors("WaitingRoom").size());
    }

    @Test
    public void testDirectShortestPath() {
        assertEquals(
                List.of("Entrance", "WaitingRoom"),
                graph.findShortestPath("Entrance", "WaitingRoom"));
    }

    @Test
    public void testMultipleLocationShortestPath() {
        assertEquals(
                List.of("Entrance", "WaitingRoom", "ER",
                        "PatientRooms", "Radiology"),
                graph.findShortestPath("Entrance", "Radiology"));
    }

    @Test
    public void testIndirectPathIsShorter() {
        assertEquals(
                List.of("Lab", "PatientRooms", "Chapel"),
                graph.findShortestPath("Lab", "Chapel"));
    }

    @Test
    public void testAvoidExpensiveConnection() {
        assertEquals(
                List.of("Radiology", "PatientRooms", "Pharmacy"),
                graph.findShortestPath("Radiology", "Pharmacy"));
    }

    @Test
    public void testReverseShortestPath() {
        assertEquals(
                List.of("Radiology", "PatientRooms", "ER",
                        "WaitingRoom", "Entrance"),
                graph.findShortestPath("Radiology", "Entrance"));
    }

    @Test
    public void testSameStartAndEnd() {
        assertEquals(
                List.of("ER"),
                graph.findShortestPath("ER", "ER"));
    }

    @Test
    public void testDisconnectedLocation() {
        assertTrue(
                graph.findShortestPath("Entrance", "Storage").isEmpty());
    }

    @Test
    public void testDisconnectedStartingLocation() {
        assertTrue(
                graph.findShortestPath("Storage", "Entrance").isEmpty());
    }

    @Test
    public void testInvalidStartingLocation() {
        assertTrue(
                graph.findShortestPath("FakeRoom", "ER").isEmpty());
    }

    @Test
    public void testInvalidEndingLocation() {
        assertTrue(
                graph.findShortestPath("ER", "FakeRoom").isEmpty());
    }
}