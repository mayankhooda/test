package google;

import javafx.util.Pair;
import org.junit.Test;

import java.util.*;

public class AirportTest {

	class Flight {
		String id;
		String from;
		String to;
		int departure;
		int arrival;

		Flight(String id, String from, String to, int departure, int arrival) {
			this.id = id;
			this.from = from;
			this.to = to;
			this.departure = departure;
			this.arrival = arrival;
		}
	}

	@Test
	public void test() {
		String startingAirport = "JFK";
		String targetAirport = "last";
		int startingTime = 100;
		Flight flight1 = new Flight("1", "JFK", "LAX", 600, 950);
		Flight[] flights = new Flight[10];

		Map<String, Flight> flightMap = new HashMap<>();
		Map<String, List<String>> graph = new HashMap<>();

		for (Flight flight : flights) {
			flightMap.put(flight.id, flight);
			graph.putIfAbsent(flight.from, new ArrayList<>());
			graph.get(flight.from).add(flight.id);
		}

		Map<String, Integer> quickestPath = new HashMap<>();
		quickestPath.put(startingAirport, startingTime);

		PriorityQueue<Pair<String, Integer>> priorityQueue = new PriorityQueue<>((a, b) -> a.getValue()-b.getValue());
		priorityQueue.add(new Pair<>(startingAirport, startingTime));

		HashSet<String> visited = new HashSet<>();

		while(!priorityQueue.isEmpty()) {
			Pair<String, Integer> currentPortAndQuickestTime = priorityQueue.poll();

			String currentPort = currentPortAndQuickestTime.getKey();
			Integer quickestTime = currentPortAndQuickestTime.getValue();

			if (currentPort.equals(targetAirport))
				break;

			if (visited.contains(currentPort)) continue;
			else visited.add(currentPort);

			if (!graph.containsKey(currentPort)) continue;
			for (String nextFlightId : graph.get(currentPort)) {
				Flight nextFlight = flightMap.get(nextFlightId);
				// if flight is possible
				if (quickestTime <= nextFlight.departure) {
					// if taking this flight is quicker
					if (!quickestPath.containsKey(nextFlight.to)
							|| quickestPath.get(nextFlight.to) > nextFlight.arrival) {
						quickestPath.put(nextFlight.to, nextFlight.arrival);
						priorityQueue.add(new Pair<>(nextFlight.to, nextFlight.arrival));
					}
				}
			}

		}
	}

	@Test
	public void testt() {
		System.out.println(minDeletionSize(new String[]{"babca","bbazb"}));
	}

	public int minDeletionSize(String[] A) {
		int n = A[0].length();
		int[] memo = new int[n];
		return solve(n-1, A, memo);
	}

	int solve(int n, String[] A, int[] memo) {
		if (memo[n] != 0)
			return memo[n];

		int k = A.length;
		int res = 0;
		for (int i=0; i<n; i++) {
			boolean flag = true;
			for (int j=0; j<k; j++) {
				char curr = A[j].charAt(n);
				char prev = A[j].charAt(i);

				if (prev > curr) {
					flag = false;
					break;
				}

			}

			if (flag) {
				res = Math.max(res, solve(i, A, memo));
			}
		}

		memo[n] = 1 + res;
		return memo[n];
	}
}
