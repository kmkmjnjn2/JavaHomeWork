package com.ebanma.cloud.service.impl;

import com.ebanma.cloud.core.BeanFactory;
import com.ebanma.cloud.dao.AccountDao;
import com.ebanma.cloud.domain.Account;
import com.ebanma.cloud.service.TransferService;

public class TransferServiceImpl implements TransferService {

    // 最佳状态
    private AccountDao accountDao;

    // 构造函数传值/set方法传值
    public void setAccountDao(AccountDao accountDao) {
        System.out.println("调用方法");
        this.accountDao = accountDao;
    }

    @Override
    public void transfer(String fromCardNo, String toCardNo, int money) throws Exception {

        /*try{
            // 开启事务(关闭事务的自动提交)
            TransactionManager.getInstance().beginTransaction();*/

        Account from = accountDao.queryAccountByCardNo(fromCardNo);
        System.out.println("from:"+from);
        Account to = accountDao.queryAccountByCardNo(toCardNo);
        System.out.println("to:"+to);

        from.setMoney(from.getMoney()-money);
        to.setMoney(to.getMoney()+money);

        accountDao.updateAccountByCardNo(to);
        int c = 1/0;
        accountDao.updateAccountByCardNo(from);

        /*    // 提交事务

            TransactionManager.getInstance().commit();        }catch (Exception e) {            e.printStackTrace();            // 回滚事务
            TransactionManager.getInstance().rollback();
            // 抛出异常便于上层servlet捕获
            throw e;
        }*/    }
}