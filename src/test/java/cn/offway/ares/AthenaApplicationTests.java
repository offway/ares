package cn.offway.ares;

import cn.offway.ares.service.impl.PhAuthServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AthenaApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void testMsg() {
        String result = "您的身份审核已通过";
        String content = "恭喜！可以借衣啦！";
//		result = "您的身份审核未通过";
//		content = "NO";
        Map<String, Object> args = new HashMap<>();
        args.put("touser", "oJksw5Zw4egJsmqfg9s1OVaz3Saw");
        args.put("template_id", "Kp9iDQ5mUycHBTroqgGttJB5fQyxBZcmBpI-zTHAUwc");
        Map<String, Object> data = new HashMap<>();
        Map<String, String> k1 = new HashMap<>();
        k1.put("value", result);
        data.put("thing4", k1);
        Map<String, String> k2 = new HashMap<>();
        k2.put("value", content);
        data.put("thing5", k2);
        args.put("data", data);
        PhAuthServiceImpl.sendSubscribeMsg(args, PhAuthServiceImpl.getToken());
    }
}
