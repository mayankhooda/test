package test.Elevator;

import java.util.HashMap;

public interface ElevatorAllocator {
	int allocate(HashMap<Integer, Elevator> elevators, int floorId, RequestDirection direction);
}
