package group2.core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    private static final String LOCATION_ID = "location_1";
    private static final String USER_ID = "user_1";
    private static final String SESSION_ID_1 = "session_1";
    private static final String SESSION_ID_2 = "session_2";

    @Mock UserConfig config;
    @Mock SessionRepository sessionRepository;
    @InjectMocks BookingService service;

    @Test
    void emptyState() {
        service.bookAvailableSessions();
        verify(sessionRepository, never()).bookSession(any(), eq(USER_ID));
    }

    @Test
    void bookIfAvailable() {
        when(config.userId()).thenReturn(USER_ID);
        when(config.preferences()).thenReturn(List.of(new LocationPreference(LOCATION_ID)));
        when(sessionRepository.findByLocationId(LOCATION_ID)).thenReturn(List.of(
                new Session(SESSION_ID_1, LOCATION_ID, LocalDateTime.now().plus(1L, ChronoUnit.DAYS), 0),
                new Session(SESSION_ID_2, LOCATION_ID, LocalDateTime.now().plus(2L, ChronoUnit.DAYS), 1)
        ));
        service.bookAvailableSessions();
        verify(sessionRepository, times(1)).bookSession(SESSION_ID_2, USER_ID);
    }
}
