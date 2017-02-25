package org.mybatis.extend.demo.test.service;

import org.junit.Test;
import org.mybatis.extend.demo.model.account.AdminAccount;
import org.mybatis.extend.demo.service.account.AdminAccountService;
import org.mybatis.extend.demo.test.base.BaseTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Bob Jiang on 2017/2/25.
 */
public class AdminAccountTest extends BaseTest {

    @Autowired
    private AdminAccountService adminAccountService;

    @Test
    public void select() {
        List<AdminAccount> list = adminAccountService.selectAll();
        for (AdminAccount account : list) {
            System.out.println(account);
        }
    }

}
