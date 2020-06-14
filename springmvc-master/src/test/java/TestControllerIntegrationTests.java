import com.example.config.SpringMvcConfig;
import com.example.service.DemoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringMvcConfig.class})
//用来声明加载的ApplicationContext 是一个 WebApplicationContext
//属性用来指定Web资源的位置，默认为src/main/webapp
@WebAppConfiguration
public class TestControllerIntegrationTests {

    private MockMvc mockMvc; //模拟 web Mvc 对象

    @Autowired
    private DemoService demoService;  // 测试用例中注入的 Spring Bean

    @Autowired
    WebApplicationContext wac ; // 注入WebApplicationContext

    @Autowired
    MockHttpSession session;  //注入模拟的 session

    @Autowired
    MockHttpServletRequest request;  //注入模拟的 request

    @Before //在测试开始前的初始化工作
    public  void  setup(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public  void testNormalController() throws Exception {
         mockMvc.perform(get("/normal"))  // 模拟向 /normal 发出 get请求
                 .andExpect(status().isOk()) // 预期返回状态为200
                 .andExpect(view().name("page")) //预期返回 view page
                 .andExpect(forwardedUrl("/WEB-INF/jsp/page.jsp")) //预期真正转向的页面路径
                 .andExpect(model().attribute("msg",demoService.saySomething()));
    }

    @Test
    public  void testRestController() throws Exception{
         mockMvc.perform(get("/testRest")) //模拟向 /testRest 发出 get请求
                 .andExpect(status().isOk())
                 .andExpect(content().contentType("text/plain;charset=UTF-8")) //预期返回的媒体类型
                 .andExpect(content().string(demoService.saySomething())); //预期返回的内容
    }

}
