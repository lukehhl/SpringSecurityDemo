import com.luke.springsecurity.DemoDBApplication;
import com.luke.springsecurity.service.MenuService;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/*
@RunWith(SpringRunner.class)
*/
@SpringBootTest(classes = DemoDBApplication.class)
@AllArgsConstructor
class TestApplication {

    @Autowired
    MenuService menuService;

    @Test
    void contextLoads() {
    }

    @Test
    public void test() {
        System.out.println(menuService.queryALlWithRoles());
    }


}
