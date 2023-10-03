package group2.app;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import lombok.extern.slf4j.Slf4j;
import group2.core.BookingService;
import group2.core.Session;
import group2.core.SessionRepository;
import group2.core.UserConfig;

import java.util.Collections;
import java.util.List;

@Slf4j
public class Main implements RequestHandler<Void, String> {

    private final BookingService bookingService;

    public Main() {
        UserConfig config = new UserConfig("foo", Collections.emptyList());
        SessionRepository sessionRepository = new SessionRepository() {
            @Override
            public List<Session> findByLocationId(String locationId) {
                return Collections.emptyList();
            }

            @Override
            public void bookSession(String sessionId, String userId) {
                log.info("Booking session {}", sessionId);
            }
        };
        bookingService = new BookingService(config, sessionRepository);
    }

    @Override
    public String handleRequest(Void unused, Context context) {
        log.info("Starting booking process...");
        bookingService.bookAvailableSessions();
        log.info("Ending booking process...");
        return "Done!";
    }
}
