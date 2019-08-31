package com.functions;

import org.apache.jmeter.engine.util.CompoundVariable;
import org.apache.jmeter.functions.AbstractFunction;
import org.apache.jmeter.functions.InvalidVariableException;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.samplers.Sampler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Plus extends AbstractFunction {

    private Object[] values;   //创建对象数组，用于存放参数的值
    private CompoundVariable first, second; //存储第一个和第二个参数(jmeter需要是这种类型)

    /*执行方法
    * */
    public String execute(SampleResult sampleResult, Sampler sampler) throws InvalidVariableException {
        System.out.println("execute run!!!");
        first = (CompoundVariable) values[0];  //解析数组对象第一个对象的值，赋值给first
        System.out.println("第一个参数是" + first);
        second = (CompoundVariable) values[1]; //解析数组对象第一个对象的值，赋值给first
        System.out.println("第二个参数是" + second);

        //定义一个整数变量，用于储存两个数相加的和
        //new Integer(first.execute().trim()),将first去掉空格等转换为整数类型，
        //snew Integer(second.execute().trim()),将second去掉空格等转换为整数类型，
        int count = new Integer(first.execute().trim())+
                new Integer(second.execute().trim());
        System.out.println("两数相加的和是"+count);
        return String.valueOf(count);   //将count转换为字符串类型返回
//        return null;
    }

    /*设置参数，接收用户传递的参数
    * */
    public void setParameters(Collection<CompoundVariable> collection) throws InvalidVariableException {
        System.out.println("setParameters run!!!");
        checkParameterCount(collection,2);  //判断传入的是两个参数
        values = collection.toArray();  //将传入的参数转化为数组，赋值给values


    }
    /*功能名称
    * */
    public String getReferenceKey() {
        System.out.println("getReferenceKey run!!!");
        return "__MyDeom";  //返回函数名称
//        return null;
    }

    /*功能描述，参数描述
    * */
    public List<String> getArgumentDesc() {
        System.out.println("getArgumentDesc run!!!");
        List desc = new ArrayList();  //定义一个数组，用于存放参数列表
        desc.add("第一个数字");  //添加参数
        desc.add("第二个数字");  //添加参数

        return desc;   //返回数组
//        return null;
    }
}
