package org.mybatis.extend.demo.test.service;

import org.junit.Test;
import org.mybatis.extend.demo.mapper.account.AdminAccountMapper;
import org.mybatis.extend.demo.model.account.AdminAccount;
import org.mybatis.extend.demo.service.account.AdminAccountService;
import org.mybatis.extend.demo.test.base.BaseTest;
import org.mybatis.extend.page.param.Page;
import org.mybatis.extend.page.result.PageResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Bob Jiang on 2017/2/25.
 */
public class AdminAccountTest extends BaseTest {

    @Autowired
    private AdminAccountService adminAccountService;

    @Autowired
    private AdminAccountMapper adminAccountMapper;

    @Test
    public void page() {
        List<AdminAccount> list = adminAccountMapper.getAll("1", new Page(1));
        PageResult<AdminAccount> pageResult = new PageResult<AdminAccount>(list);
        System.out.println(pageResult);

        select();
    }

    @Test
    public void select() {
        List<AdminAccount> list = adminAccountService.selectAll();
        for (AdminAccount account : list) {
            System.out.println(account);
        }
    }

}
