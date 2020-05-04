package test.Elevator;

public class ButtonPanelHallImpl implements ButtonPanelHall {
	private final int floorId;
	private final ElevatorController elevatorController;

	private boolean upRequested;
	private boolean downRequesed;

	public ButtonPanelHallImpl(int floorId,
							   ElevatorController elevatorController) {
		this.floorId = floorId;
		this.elevatorController = elevatorController;
	}

	@Override
	public void pressUp() {
		elevatorController.request(floorId, RequestDirection.UP);
		upRequested = true;
	}

	@Override
	public void pressDown() {
		elevatorController.request(floorId, RequestDirection.DOWN);
		downRequesed = true;
	}

	@Override
	public void display() {

	}
}
