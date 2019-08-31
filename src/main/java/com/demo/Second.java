package com.demo;


import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerClient;   //导入
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;


import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException; //导入URLException
import java.net.URL;  //导入url
import java.net.URLConnection;

public class Second  implements JavaSamplerClient {
    //URLNAME 就是在图形化界面当中显示的变量名称
    private static final String URLNAME = "URL";  //定义静态变量URLNAME

    //设置界面当中默认显示的变量的值
    private static final String DEFAULTURL = "http://www.baidu.com";  //定义静态变量DEFAULTURL

    //用来存储响应的结果，目的是将响应的结果放到查看结果树中
    private String restultData;   //定义一个变量，用于存放响应结果

    private static int count = 0; //定义一个计数器，查看getDefaultParameters函数的调用次数

    /*这个方法决定了在jmeter当中要显示哪些属性
     * */
    public Arguments getDefaultParameters() {
        System.out.println("getDefaultParameters run");
        Arguments arguments = new Arguments();  //这个是图像界面中的参数显示部分
        arguments.addArgument(URLNAME,DEFAULTURL);  //URLNAME为参数的键，DEFAULTURL为参数的值
        count = count+1;
        System.out.println("getDefaultParameters run 第"+count+"次");
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



    SampleResult result = new SampleResult();  //定义一个采样器响应结果

    /*这个方法就是实现你具体功能逻辑的方法
    * */
    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {

        System.out.println("runTest run");
        StringBuffer sb = new StringBuffer();   //定义一个StringBuffer变量sb

        try {
            URL url = new URL(inputUrl);   //定义一个url

            URLConnection conn = url.openConnection();  //连接url

            byte[] buffer = new byte[1024];   //定义一个1024字节长度的数组
            int len ;   //定义一个整数变量

            result.sampleStart(); //标记事务的开始
            InputStream in = conn.getInputStream();  //获取url输入流信息
            //in.read()是一次读一个字节。in.read(buffer)意思是一次读1024节，等于-1时表示没得读了
            //不等于-1表示一直有内容可读的时候
            while((len=in.read(buffer))!=-1){
                //将buffer变量的内容转换为字符串，编码格式为UTF-8赋值给 restultData
                restultData = new String(buffer,"UTF-8");
                sb.append(restultData);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        restultData = "这就是响应结果";
        //将sb的内容转换为字符串，赋值给restultData
        restultData = sb.toString();

        //jmeter中java请求 请求的的名字（label）,设置此项后，
        // java 请求的label便固定为定义的内容
        result.setSampleLabel("自定义java请求访问");
        //设置java 请求的响应结果，为true表示响应成功，为false,表示响应失败
        result.setSuccessful(true);
//        result.setSuccessful(false);

        result.setResponseData(restultData,null);  //设置响应结果，编码默认GBK
//        result.setResponseData(restultData,"UTF-8");  //设置响应结果，编码默认GBK
        result.setDataType(SampleResult.TEXT);   //设置响应结果的数据类型为text
        return result; //返回响应结果mvn
//        return null;
    }

    /*这个方法就是来做一些收尾工作的。
    * */
    public void teardownTest(JavaSamplerContext javaSamplerContext) {
        System.out.println("teardownTest run");

    }

}
