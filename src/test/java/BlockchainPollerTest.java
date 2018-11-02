import org.junit.Test;

public class BlockchainPollerTest {

    @Test
    public void hitInfuraTest() {
        try {
            BlockchainPoller.hitInfura();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
