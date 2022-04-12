package infra;

public class EventPublisher {
  private static EventPublisher instance;

  public static EventPublisher getInstance() {
    if (instance == null) {
      instance = new EventPublisher();
    }
    return instance;
  }

  public void publish(Event event) {

  }
}
