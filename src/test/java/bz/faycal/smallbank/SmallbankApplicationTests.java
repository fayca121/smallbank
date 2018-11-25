package bz.faycal.smallbank;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Tag("Integration Test")
public class SmallbankApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void contextLoads() {
    }

    @Test
    @DisplayName("Check operations page ( GET /operations)")
    public void testOperationsPage() throws Exception{
        mockMvc.perform(get("/operations"))
                .andExpect(status().is(302));
                //.andExpect(content().string(containsString("accounts")));
    }

}
