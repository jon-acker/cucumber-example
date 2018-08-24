package acme;

import java.time.LocalDateTime;

public class SystemClock {
    private LocalDateTime dateTime;

    public SystemClock(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public LocalDateTime getLocalDateTime() {
        return this.dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

}
