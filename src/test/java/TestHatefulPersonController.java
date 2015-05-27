import com.escobeitor.hatenotepad.HatefulStarter;
import com.escobeitor.hatenotepad.enums.EAssholeLevel;
import com.escobeitor.hatenotepad.model.HatefulPerson;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.escobeitor.hatenotepad.repository.HatefulPersonRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by escobeitor on 27/05/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = HatefulStarter.class)
@WebAppConfiguration
@ActiveProfiles("development")
public class TestHatefulPersonController extends AbstractHatefulTest {

    /**
     * Test Hateful Person
     */
    private HatefulPerson testPerson;

    @Autowired
    HatefulPersonRepository hatefulPersonRepository;

    @Before
    public void initialize() {
        testPerson = new HatefulPerson("escobeitor", "csgermanico@gmail.com", EAssholeLevel.COMPLETE_DOUCHEBAG); //Maybe the most hateful person of them all
        testPerson = hatefulPersonRepository.save(testPerson);
    }

    @After
    public void cleanUp() {
        hatefulPersonRepository.delete(testPerson);
    }

    @Test
    public void testFindNotAuthorized() {
        try {

            mockMvc.perform(get("/person/get")
                    .param("email", "csgermanico@gmail.com"))
                .andExpect(status().isUnauthorized());

        } catch(Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void testFindByEmailExists() {

    }

    @Test
    public void testFindByFakeEmail() {

    }

    @Test
    public void testInsertNotAuthorized() {

    }

    @Test
    public void testInsertEmailAlreadyExists() {

    }

    @Test
    public void testInsertMissingParameters() {

    }

    @Test
    public void testInsertNew() {

    }
}
