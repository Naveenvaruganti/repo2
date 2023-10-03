package group2.core;

import java.time.LocalDateTime;

public record Session(
        String id,
        String locationId,
        LocalDateTime localDateTime,
        int availableSpots
) {
}
