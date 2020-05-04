package test.Elevator;

import java.util.Queue;

public class ElevatorImpl {
	enum ElevatorState {
		MOVING, STOPPED
	}

	int elevatorId;
	int capacity;
	int currentWeight;
	int currentFloor;
	ElevatorState elevatorState;
	Queue<Integer> scheduledStops;


}
