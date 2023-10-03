package group2.core;

import java.util.List;

public class BookingService {

    private final UserConfig config;
    private final SessionRepository sessionRepository;

    public BookingService(UserConfig config, SessionRepository sessionRepository) {
        this.config = config;
        this.sessionRepository = sessionRepository;
    }

    public void bookAvailableSessions() {
        for (LocationPreference preference : config.preferences()) {
            final List<Session> sessions = sessionRepository.findByLocationId(preference.locationId());
            for (Session session : sessions) {
                if (session.availableSpots() > 0) {
                    sessionRepository.bookSession(session.id(), config.userId());
                }
            }
        }
    }
}
