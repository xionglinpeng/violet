package com.violet;

import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * <p></p>
 *
 * @author xlp
 * @date 2020/4/2 上午9:41
 * @since 1.0.0
 */
public abstract class AbstractMockMvc {

    @Autowired
    private WebApplicationContext webApplicationContext;

    protected MockMvc mvc;

    protected MockHttpSession session;

    @Before
    public void setupMockMvc() {
        this.mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        this.session = new MockHttpSession();
    }

}
