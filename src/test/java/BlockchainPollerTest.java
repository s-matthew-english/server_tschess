import org.junit.Test;

import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class BlockchainPollerTest {

    @Test
    public void hitInfuraTest() throws Exception {
        Timer t = new Timer();
        BlockchainPoller mTask = new BlockchainPoller();
        t.scheduleAtFixedRate(mTask, 0, 10);

        TimeUnit.SECONDS.sleep(1000);
    }
}
