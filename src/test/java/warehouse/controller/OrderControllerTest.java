package warehouse.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import warehouse.config.AppConfig;
import warehouse.model.Product;
import warehouse.model.dto.request.OrderRequestDto;
import warehouse.service.ProductService;
import warehouse.service.impl.ProductServiceImpl;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AppConfig.class})
@WebAppConfiguration
public class OrderControllerTest extends AbstractTestNGSpringContextTests {
    public static final MediaType APPLICATION_JSON_UTF8
            = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), StandardCharsets.UTF_8);
    @Autowired
    private ProductService productService;
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @BeforeClass
    public void beforeClass() {
        Product productOne = new Product();
        productOne.setTitle("First product");
        productOne.setQuantity(173);
        productOne.setWeight(274.9);
        productOne.setSku("PRODUCT1");
        productOne.setPrice(1839.0);

        Product productTwo = new Product();
        productTwo.setTitle("Second product");
        productTwo.setQuantity(294);
        productTwo.setWeight(384.6);
        productTwo.setSku("PRODUCT2");
        productTwo.setPrice(392.3);

        Product productThree = new Product();
        productThree.setTitle("Third product");
        productThree.setQuantity(9);
        productThree.setWeight(24.8);
        productThree.setSku("PRODUCT3");
        productThree.setPrice(3242.3);

        Product productFour = new Product();
        productFour.setTitle("Fourth product");
        productFour.setQuantity(13);
        productFour.setWeight(3843.1);
        productFour.setSku("PRODUCT4");
        productFour.setPrice(4999.9);

        Product productFive = new Product();
        productFive.setTitle("Fifth product");
        productFive.setQuantity(38);
        productFive.setWeight(732.2);
        productFive.setSku("PRODUCT5");
        productFive.setPrice(2421.49);

        List.of(productOne, productTwo, productThree, productFour, productFive)
                .forEach(productService::add);
    }


    @BeforeMethod(alwaysRun = true)
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void givenWac_whenServletContext_thenItProvidesGreetController() {
        ServletContext servletContext = webApplicationContext.getServletContext();
        Assert.notNull(servletContext, "Servlet context should not be NULL!");
        Assert.isTrue(servletContext instanceof MockServletContext,
                "Servlet context should be instance of MockServletContext");
        Assert.notNull(webApplicationContext.getBean("orderController"),
                "Bean greetController should not be null");
    }

    @Test(invocationCount = 10, threadPoolSize = 3)
    public void givenGreetURI_whenMockMVC_thenVerifyResponse() throws Exception {
        OrderRequestDto requestDto = new OrderRequestDto();
        Map<String, Integer> map = new HashMap<>();
        map.put("PRODUCT1", 75);
        map.put("PRODUCT2", 150);
        map.put("PRODUCT3", 10);
        requestDto.setSkuAndQuantity(map);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson = ow.writeValueAsString(requestDto);
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.post("/new-order")
                        .contentType(APPLICATION_JSON_UTF8)
                        .content(requestJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assertions.assertEquals(200, response.getStatus());
    }
}
