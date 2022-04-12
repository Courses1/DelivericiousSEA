package infra;

import java.util.UUID;

public abstract class Event {
    protected Object data;
    UUID id;

    abstract public String toMessage();
}

