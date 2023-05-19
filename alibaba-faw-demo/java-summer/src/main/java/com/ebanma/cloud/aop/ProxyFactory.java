package com.ebanma.cloud.aop;

import com.ebanma.cloud.util.TransactionManager;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory {

    private TransactionManager transactionManager;

    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    /**
     * 得到jdk代理
     * @param obj
     * @return
     */
    public Object getJdkProxy(Object obj) {
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object result = null;
                try {
                    System.out.println("进入代理");
                    // 开启事务（关闭事务的自动提交）
                    transactionManager.begin();
                    result = method.invoke(obj, args);
                    // 提交事务
                    System.out.println("提交事务");
                    transactionManager.commit();

                } catch (Exception e) {
                    // 回滚事务
                    System.out.println("回滚事务");
                    transactionManager.rollback();
                    throw new RuntimeException(e);
                }
                return result;
            }
        });
    }

    public Object getCglibProxy(Object obj) {
        return Enhancer.create(obj.getClass(), new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

                Object result = null;
                try {
                    // 开启事务（关闭事务的自动提交）
                    transactionManager.begin();
                    result = method.invoke(obj, objects);
                    // 提交事务
                    transactionManager.commit();

                } catch (Exception e) {
                    // 回滚事务
                    transactionManager.rollback();
                    throw new RuntimeException(e);
                }
                return result;
            }
        });
    }



}
