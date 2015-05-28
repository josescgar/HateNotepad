import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Initializes common variables to all hateful tests
 * Created by escobeitor on 27/05/15.
 */
public abstract class AbstractHatefulTest {

    /**
     * Mock MVC object for making
     * test requests to the API
     */
    protected MockMvc mockMvc;

    /**
     * Spring security filter chain
     */
    @Autowired
    private FilterChainProxy springSecurityFilterChain;

    /**
     * Web application context
     */
    @Autowired
    protected WebApplicationContext webAppContext;

    @Before
    public void setUp() {

        mockMvc = MockMvcBuilders
                .webAppContextSetup(webAppContext)
                .addFilters(springSecurityFilterChain)
                .build();
    }
}
