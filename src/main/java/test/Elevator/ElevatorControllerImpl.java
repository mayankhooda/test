package test.Elevator;

import java.util.HashMap;

public class ElevatorControllerImpl implements ElevatorController {
	private final HashMap<Integer, Elevator> elevators;

	public ElevatorControllerImpl() {
		this.elevators = new HashMap<>();
	}

	@Override
	public void request(int floorId, RequestDirection direction) {
		int elevatorId = allocateElevatorOptimally(floorId, direction);
		elevators.get(elevatorId).assignRequest(floorId);
	}

	private int allocateElevatorOptimally(int floorId, RequestDirection direction) {
		return 0;
	}

	@Override
	public void dispatch(int elevatorId, int floor) {
		elevators.get(elevatorId).assignRequest(floor);
	}

	@Override
	public void addElevator(int elevatorId, Elevator elevator) {
		elevators.put(elevatorId, elevator);
	}

	@Override
	public void removeElevator(int elevatorId) {
		elevators.remove(elevatorId);
	}


}
