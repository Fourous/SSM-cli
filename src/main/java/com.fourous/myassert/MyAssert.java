package com.fourous.myassert;

import com.fourous.Exception.MyException;

/**
 * 自定义断言
 * @author fourous
 */
public class MyAssert{
    /**
     *
     * @param object
     * @param message
     */
    public static void myDestinationAssert(Object object,String message){
        if(object == null){
            throw new MyException(message);
        }
    }
}