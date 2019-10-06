package com.aaa.controlleraaa;


import com.baidu.ai.aip.utils.HttpUtil;
import com.baidu.ai.aip.utils.GsonUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import sun.misc.BASE64Encoder;

/**
* 人脸搜索
*/
public class FaceSearch {

    /**
    * 重要提示代码中所需工具类
    * FileUtil,Base64Util,HttpUtil,GsonUtils请从
    * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
    * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
    * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
    * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
    * 下载
    */
    public static String search(String loginBase) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/search";
        try {
        	// String imgParam=GetImageStr();//获取base64的图片图片编码
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", loginBase);
            map.put("liveness_control", "NONE"); 	//活体检测控制
            map.put("group_id_list", "tianliuzhenTestFace,group_repeat"); //从指定的group中进行查找 用逗号分隔，上限10个
            map.put("image_type", "BASE64");
            map.put("quality_control", "LOW");
          //  map.put("user_id", "zhang");//当需要对特定用户进行比对时，指定user_id进行比对。即人脸认证功能。

            String param = GsonUtils.toJson(map);
            
            
            
            AuthService as=new AuthService();
            
            String accessToken = as.getToken();
            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。

            String result = HttpUtil.post(url, accessToken, "application/json", param);
            System.out.println(result);
            
            
            
            
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        FaceSearch.search("");
    }
    //模拟图片转码base64
    public static  String GetImageStr()
    {//将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        String imgFile = "C:\\java\\xxx2.jpg";//待处理的图片
        InputStream in = null;
        byte[] data = null;
        //读取图片字节数组
        try
        {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        //对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);//返回Base64编码过的字节数组字符串
    }
}