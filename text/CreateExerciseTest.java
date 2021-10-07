package Test;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CreateExerciseTest {



    @Test
    public void dating() {
        String x;
        x = CreateExercise.Dating(2, 4);
        Assert.assertEquals("1/2",x);
    }
}