import com.cmartin.learn.*;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * Created by cmartin on 02/03/2017.
 */
public class DTOTest {

    @Test
    public void test() {
        Piston piston = new Piston();
        piston.setDiameter(10.25);
        List<Piston> pistons = Arrays.asList(piston, piston);
        Engine engine = new Engine();
        engine.setCode(5555L);
        engine.setPistonCount(6);
        engine.setUniversalCode(UUID.randomUUID());
        engine.setPistons(pistons);
        Car car = new Car();
        car.setEngine(engine);
        car.setMake("Opel");
        car.setNumberOfSeats("4");
        car.setSerialNumber(BigInteger.valueOf(1234567890));

        CarDto carDto = CarMapper.INSTANCE.carToCarDto(car);

        Assert.assertNotNull(carDto);
        Assert.assertNotNull(carDto.getCylinderCount());
    }
}
