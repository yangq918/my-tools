package org.yq.tool.yktools;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.yq.tool.yktools.action.DoctorDataAction;
import org.yq.tool.yktools.action.ScanDataAction;

@SpringBootTest
@Transactional
class YkToolsApplicationTests {

    @Autowired
    private DoctorDataAction doctorDataAction;

    @Autowired
    private ScanDataAction scanDataAction;

    @Test
    void contextLoads() {
    }

    @Test
    public void testLoadData()
    {
        doctorDataAction.doAction();
    }


    @Test
    @Transactional(propagation = Propagation.NEVER)
    @Rollback(false)
    public void testScanData()
    {
        scanDataAction.doAction();
    }

}
