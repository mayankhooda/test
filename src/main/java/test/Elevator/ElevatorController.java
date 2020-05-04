package test.Elevator;

public interface ElevatorController {
	void request(int floorId, RequestDirection direction);

	void dispatch(int elevatorId, int floor);

	void addElevator(int elevatorId, Elevator elevator);

	void removeElevator(int elevatorId);
}
