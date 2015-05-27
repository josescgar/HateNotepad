import com.escobeitor.hatenotepad.HatefulStarter;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by escobeitor on 27/05/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = HatefulStarter.class)
@WebAppConfiguration
@ActiveProfiles("development")
public class TestHateNoteController extends AbstractHatefulTest {
}
