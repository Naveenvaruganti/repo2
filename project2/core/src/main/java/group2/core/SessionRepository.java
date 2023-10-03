package group2.core;

import java.util.List;

public interface SessionRepository {

    List<Session> findByLocationId(String locationId);

    void bookSession(String sessionId, String userId);
}
