package javaonline.dao.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@ContextConfiguration(locations = {"classpath:H-application-context-test.xml", "classpath:hibernate-context-test.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class HOrderDaoTest {
    @Test
    @Transactional
    @Rollback
    public void deleteOpenedOrder() throws Exception {

    }

    @Test
    @Transactional
    @Rollback
    public void getAllOrders() throws Exception {

    }

}