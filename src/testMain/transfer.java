package testMain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import FileTransfer.Client;
import FileTransfer.FileServer;

public class transfer {
	 final static String url = "http://139.9.138.43:8089//call_information/FileTransfer";
	    
	    public static void main(String[] args) {
	    	System.out.println("正在启动客户端");
	    	try {
    			Client client = new Client(); // 启动客户端连接
    			System.out.println("客户端启动");
    			client.sendFile("D:\\pbxrecord\\pbxrecord\\pbxrecord\\192.168.1.218\\20190916\\212_200_20190916-152553_24657.mp3"); // 传输文件
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
	    	post(url);

	    }

	    /**
	     * 发送HttpPost请求
	     * 
	     * @param strURL
	     *            服务地址
	     * @param params
	     *            json字符串,例如: "{ \"id\":\"12345\" }" ;其中属性名必须带双引号<br/>
	     */
	    public static void post(String strURL) {
	        System.out.println(strURL);
	        BufferedReader reader = null;
	        try {
	        	
	            URL url = new URL(strURL);
	            // 创建连接
	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	            connection.setDoOutput(true);
	            connection.setDoInput(true);
	            connection.setUseCaches(false);
	            connection.setInstanceFollowRedirects(true);
	            connection.setRequestMethod("POST"); // 设置请求方式
	            connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
	            connection.connect();
	            
	            //一定要用BufferedReader 来接收响应， 使用字节来接收响应的方法是接收不到内容的
	            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码
	            out.flush();
	            out.close();
	         // 读取响应
	            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
	            String line;
	            String res = "";
	            while ((line = reader.readLine()) != null) {
	                res += line;
	            }
	            reader.close();
	            System.out.println(res);
	            
	        } catch (IOException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } 
	    }
}
