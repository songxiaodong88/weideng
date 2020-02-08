package com.wisdom;

import com.wisdom.excel.entity.WuShuExcelEntity;
import org.apache.poi.ss.formula.functions.T;
import org.junit.Test;

import java.lang.reflect.Field;
import java.rmi.activation.Activator;

public class TestDemo {

    private T model;
    private Class clazz;
    @Test
    public void testModel(){

        {
            try {

//                model = (WuShuExcelEntity)Class.forName("com.wisdom.excel.entity.WuShuExcelEntity").newInstance();
                clazz = Class.forName("com.wisdom.excel.entity.WuShuExcelEntity");
                clazz.newInstance();
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    System.out.println("fild===="+field.getName());
                }

            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }


    @Test
    public void testString(){
        String s = "/static/"+"'img/05.jpg'";

        System.out.println(s);

    }
}
