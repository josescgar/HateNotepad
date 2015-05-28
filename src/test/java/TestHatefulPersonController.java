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
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import com.escobeitor.hatenotepad.repository.HatefulPersonRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;


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

    @Override
    public void setUp() {
        super.setUp();
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
        try {

            String user = mockMvc.perform(get("/person/get")
                    .with(httpBasic("admin", "admin"))
                    .param("email", "csgermanico@gmail.com"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

            Assert.assertNotNull(user);
            Assert.assertNotEquals("", user);

        } catch(Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void testFindByFakeEmail() {
        try {

            String user = mockMvc.perform(get("/person/get")
                    .with(httpBasic("admin", "admin"))
                    .param("email", "randommail@random.com"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

            Assert.assertNotNull(user);
            Assert.assertEquals("", user);

        } catch(Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void testInsertNotAuthorized() {
        try {

            mockMvc.perform(post("/person/insert")
                    .with(httpBasic("escobeitor", "manolo"))
                    .param("email", "randommail@random.com")
                    .param("nickname", "joselito")
                    .param("assholeness", "MODERATE"))
                .andExpect(status().isForbidden());

        } catch(Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void testInsertEmailAlreadyExists() {
        try {

            mockMvc.perform(post("/person/insert")
                    .with(httpBasic("admin", "admin"))
                    .param("email", "csgermanico@gmail.com")
                    .param("nickname", "joselito")
                    .param("assholeness", "MODERATE"))
                .andExpect(status().isInternalServerError());

        } catch(Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void testInsertMissingParameters() {
        try {

            mockMvc.perform(post("/person/insert")
                    .with(httpBasic("admin", "admin"))
                    .param("nickname", "joselito"))
                .andExpect(status().isBadRequest());

        } catch(Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void testInsertNew() {
        try {

            String personId = mockMvc.perform(post("/person/insert")
                    .with(httpBasic("admin", "admin"))
                    .param("email", "hello@escobeitor.com")
                    .param("nickname", "joselito")
                    .param("assholeness", "MODERATE"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

            HatefulPerson person = hatefulPersonRepository.findByEmail("hello@escobeitor.com");

            Assert.assertNotNull(person);
            Assert.assertNotEquals("", personId);
            Assert.assertEquals(personId, person.getId());
            Assert.assertEquals(person.getAssholeLevel(), EAssholeLevel.MODERATE);

            hatefulPersonRepository.delete(person);

        } catch(Exception e) {
            Assert.fail();
        }
    }
}
