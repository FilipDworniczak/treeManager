package pl.filipdworniczak.rest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.filipdworniczak.TreeManagerTests;
import org.apache.commons.io.IOUtils;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by FilipDworniczak on 2016-07-06.
 */

@IntegrationTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class NodeResourceTest extends TreeManagerTests {

    @Autowired
    NodeResource nodeResource;

    private MockMvc restNodeMockMvc;

    @Before
    public void setup() {
        this.restNodeMockMvc = MockMvcBuilders.standaloneSetup(nodeResource).build();
    }

    @Test
    public void testGetRootNode() throws Exception {
        MvcResult mvcResult = restNodeMockMvc.perform(get("/api/node")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andReturn();
        String json = mvcResult.getResponse().getContentAsString();
        String expectedJson = IOUtils.toString(TreeManagerTests.class.getResourceAsStream("/rest/getRootNodeResponse.json"));
        Assert.assertEquals(expectedJson, json);
    }

    @Test
    public void testDeleteNode() throws Exception {
        MvcResult mvcResult = restNodeMockMvc.perform(delete("/api/node/3")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andReturn();
        String json = mvcResult.getResponse().getContentAsString();
        String expectedJson = IOUtils.toString(TreeManagerTests.class.getResourceAsStream("/rest/deleteNodeResponse.json"));
        Assert.assertEquals(expectedJson, json);
    }

    @Test
    public void testChangeDesiredValue() throws Exception {
        MvcResult mvcResult = restNodeMockMvc.perform(put("/api/node/1?value=7")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andReturn();
        String json = mvcResult.getResponse().getContentAsString();
        String expectedJson = IOUtils.toString(TreeManagerTests.class.getResourceAsStream("/rest/changeDesiredValueResponse.json"));
        Assert.assertEquals(expectedJson, json);
    }

    @Test
    public void testAddNode() throws Exception {
        MvcResult mvcResult = restNodeMockMvc.perform(post("/api/node?parentNodeId=6&desiredValue=15")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andReturn();
        String json = mvcResult.getResponse().getContentAsString();
        String expectedJson = IOUtils.toString(TreeManagerTests.class.getResourceAsStream("/rest/addNodeResponse.json"));
        Assert.assertEquals(expectedJson, json);
    }
}
