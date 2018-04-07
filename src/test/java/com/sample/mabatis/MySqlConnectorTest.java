package com.sample.mabatis;

import java.sql.Connection;
import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:**/root-context.xml"})
public class MySqlConnectorTest {

    @Inject
    private DataSource dataSource;

    @Test
    public void testConnection() throws Exception {
        try (Connection con = dataSource.getConnection()) {

            System.out.println("\n >>>>>>>>>> Connection 출력 : " + con + "\n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
