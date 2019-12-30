package com.CommonTest;

import com.Exception.MyException;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class ExceptionTest {
    public static void main(String[] args){
        String[] sexs = {"男性","女性","中性"};
        for(int i = 0; i < sexs.length; i++){
            if("中性".equals(sexs[i])){
                throw new MyException("你全家都是中性！");
            }else{
                System.out.println(sexs[i]);
            }
        }
    }

}
