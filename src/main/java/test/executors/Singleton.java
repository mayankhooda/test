package test.executors;

public enum Singleton {
	INSTANCE;

	Singleton() {

	}

	Singleton getFirst() {
		return INSTANCE;
	}
}
