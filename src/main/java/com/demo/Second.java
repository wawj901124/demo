package com.demo;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerClient;   //导入
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

public class Second  implements JavaSamplerClient {
    //URLNAME 就是在图形化界面当中显示的变量名称
    private static final String URLNAME = "URL";  //定义静态变量URLNAME

    //设置界面当中默认显示的变量的值
    private static final String DEFAULTURL = "http://www.baidu.com";  //定义静态变量DEFAULTURL


    /*这个方法决定了在jmeter当中要显示哪些属性
     * */
    public Arguments getDefaultParameters() {
        System.out.println("getDefaultParameters run");
        Arguments arguments = new Arguments();  //这个是图像界面中的参数显示部分
        arguments.addArgument(URLNAME,DEFAULTURL);  //URLNAME为参数的键，DEFAULTURL为参数的值
        return arguments; //返回参数
//        return null;
    }

//    public static void main(){
//
//    }

    private String inputUrl;  //定义一个参数用于记录用户输入的url


    /*这个方法就是一个初始化方法，我们所有的初始化的动作都可以在这里写
    * */
    public void setupTest(JavaSamplerContext javaSamplerContext) {
        inputUrl = javaSamplerContext.getParameter(URLNAME,DEFAULTURL);  //获取用户输入的url
        System.out.println("setupTest run");
        System.out.println("用户输入的url地址是："+inputUrl);

    }



    /*这个方法就是实现你具体功能逻辑的方法
    * */
    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
        System.out.println("runTest run");
        return null;
    }

    /*这个方法就是来做一些收尾工作的。
    * */
    public void teardownTest(JavaSamplerContext javaSamplerContext) {
        System.out.println("teardownTest run");

    }

}
