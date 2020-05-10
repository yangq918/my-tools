package org.yq.tool.yktools;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.yq.tool.yktools.action.DoctorDataAction;

@SpringBootTest
class YkToolsApplicationTests {

    @Autowired
    private DoctorDataAction doctorDataAction;

    @Test
    void contextLoads() {
    }

    @Test
    public void testLoadData()
    {
        doctorDataAction.doAction();
    }

}
