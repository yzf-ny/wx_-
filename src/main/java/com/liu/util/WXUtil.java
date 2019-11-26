package com.liu.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class WXUtil {
       private static String token="yzf";
	
	public static String check(String signature,String timestamp,String nonce,String echostr) {

		try {
			
			String []arr=new String[] {token,timestamp,nonce};
		   //1将token、timestamp、nonce三个参数进行字典序排序
			   Arrays.sort(arr);
		   //2将三个参数字符串拼接成一个字符串进行sha1加密 
		      String srt="";
		      for(String ar:arr) {
		    	  srt+=ar;
		      }
		      String sha1;
			sha1 = sha1(srt);
			 //3 开发者获得加密后的字符串可与signature对比，标识该请求来源于微信
		      if(sha1.equals(signature)) {
				  System.out.println("this is success");
		    	  return echostr;
		      }
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			System.out.println("错误信息："+e.getMessage());
		}
		System.out.println("this is error");
		return null;	
	}
	
	
	  /**
     * sha1加密
     * @param data
     * @return
     * @throws NoSuchAlgorithmException 
     */
    public static  String sha1(String data) throws NoSuchAlgorithmException {

        //信息摘要器                                算法名称
        MessageDigest md = MessageDigest.getInstance("SHA1");
        //把字符串转为字节数组
        byte[] b = data.getBytes();
        //使用指定的字节来更新我们的摘要
        md.update(b);
        //获取密文  （完成摘要计算）
        byte[] b2 = md.digest();
        //获取计算的长度
        int len = b2.length;
        //16进制字符串
        String str = "0123456789abcdef";
        //把字符串转为字符串数组
        char[] ch = str.toCharArray();
        
        //创建一个40位长度的字节数组
        char[] chs = new char[len*2];
        //循环20次
        for(int i=0,k=0;i<len;i++) {
            byte b3 = b2[i];//获取摘要计算后的字节数组中的每个字节
            // >>>:无符号右移  
            // &:按位与
            //0xf:0-15的数字
            chs[k++] = ch[b3 >>> 4 & 0xf];
            chs[k++] = ch[b3 & 0xf];
        }
        
        //字符数组转为字符串
        return new String(chs);
    }
}
