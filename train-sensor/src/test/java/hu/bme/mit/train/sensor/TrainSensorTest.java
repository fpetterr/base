package hu.bme.mit.train.sensor;



import hu.bme.mit.train.interfaces.TrainController;
import hu.bme.mit.train.user.TrainUserImpl;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class TrainSensorTest {

    TrainController mockCont;
    TrainUserImpl mockUser;
    TrainSensorImpl sensor;

    @Before
    public void before() {
        mockUser = mock(TrainUserImpl.class);
        mockCont = mock(TrainController.class);
        sensor = new TrainSensorImpl(mockCont, mockUser);
    }

    @Test
    public void TryToSetSpeedOverLimit() {
        when(mockCont.getReferenceSpeed()).thenReturn(570);
        sensor.overrideSpeedLimit(570);
        verify(mockUser, times(1)).setAlarmState(true);
    }

    @Test
    public void TryToSetSpeedUnderLimit() {
        when(mockCont.getReferenceSpeed()).thenReturn(-20);
        sensor.overrideSpeedLimit(-20);
        verify(mockUser, times(1)).setAlarmState(true);
    }

    @Test
    public void TryToSetSpeedUnder50Percent() {
        when(mockCont.getReferenceSpeed()).thenReturn(160);
        sensor.overrideSpeedLimit(60);
        verify(mockUser, times(1)).setAlarmState(true);
    }

    @Test
    public void TryToSetCorrectTestIfSet() {
        when(mockCont.getReferenceSpeed()).thenReturn(150);
        sensor.overrideSpeedLimit(120);
        verify(mockUser, times(0)).setAlarmState(true);
    }
}

