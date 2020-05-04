package test.Elevator;

public class ButtonPanelCarImpl implements ButtonPanelCar {
	private final int elevatorId;
	private final ElevatorController elevatorController;

	public ButtonPanelCarImpl(int elevatorId,
							  ElevatorController elevatorController) {
		this.elevatorId = elevatorId;
		this.elevatorController = elevatorController;
	}

	@Override
	public void selectFloor(int floor) {
		elevatorController.dispatch(elevatorId, floor);
	}

	@Override
	public void display() {

	}
}
