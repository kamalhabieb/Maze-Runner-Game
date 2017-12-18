import models.facade.Facade;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FacadeTest {

    private Facade facade;

    @BeforeEach
    public void setup(){
        facade = new Facade();
    }

    @Test
    public void testFacade() {
        facade.initializeGame(Facade.EASY);
    }

}
