package com.violet.validator;

import com.violet.AbstractMockMvc;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * <p></p>
 *
 * @author xlp
 * @date 2020/4/1 下午4:30
 * @since 1.0.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UniqueTest extends AbstractMockMvc {


    //expect => 期望

    @Test
    public void testExpUnique() throws Exception {
        String data = "{\"id\":\"123\",\"name\":\"小明\"}";
        mvc.perform(
                post("/validator/create")
                .content(data).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andDo(print());
    }
}
