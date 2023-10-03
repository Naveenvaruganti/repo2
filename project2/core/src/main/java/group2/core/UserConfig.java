package group2.core;

import java.util.List;

public record UserConfig(
        String userId, List<LocationPreference> preferences
) {
}
