package com.aaa.controlleraaa;

import com.baidu.ai.aip.utils.Base64Util;
import com.baidu.ai.aip.utils.HttpUtil;
import com.baidu.ai.aip.utils.GsonUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.*;

import sun.misc.BASE64Encoder;


/**
* 人脸注册
*/
public class FaceAdd {

    /**
    * 重要提示代码中所需工具类
    * FileUtil,Base64Util,HttpUtil,GsonUtils请从
    * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
    * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
    * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
    * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
    * 下载
    */
    public static String add(String regiterBase) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/add";
        try {
			// 本地文件路径
         String imgParam=GetImageStr();//获取base64的图片图片编码
            // 注意这里的图片格式！
            System.out.println("imgParam:" + regiterBase);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", regiterBase);
            map.put("group_id", "tianliuzhenTestFace");
            map.put("user_id", "zhang");
            map.put("user_info", "face");
            map.put("liveness_control", "NONE");
            map.put("image_type", "BASE64");
            map.put("quality_control", "LOW");

            String param = GsonUtils.toJson(map);

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
   
      /*  参数： user_id  用户id（由数字、字母、下划线组成），长度限制128B
       *  参数： user_info   ：用户资料，长度限制48B 默认空
       *  参数：group_id   用户组id，标识一组用户（由数字、字母、下划线组成），长度限制48B。
       *  产品建议：根据您的业务需求，可以将需要注册的用户，按照业务划分，分配到不同的group下，例如按照会员手机尾号作为groupid，
       *  用于刷脸支付、会员计费消费等，这样可以尽可能控制每个group下的用户数与人脸数，提升检索的准确率
       *  
       *  参数：image  图片信息(总数据大小应小于10M)，图片上传方式根据image_type来判断
       *  参数： image_type   图片类型
       *    BASE64:图片的base64值，base64编码后的图片数据，编码后的图片大小不超过2M；
            URL:图片的 URL地址( 可能由于网络等原因导致下载图片时间过长)；
            FACE_TOKEN：人脸图片的唯一标识，调用人脸检测接口时，会为每个人脸图片赋予一个唯一的FACE_TOKEN，同一张图片多次检测得到的FACE_TOKEN是同一个。
                      
                            参数  quality_control：图片质量控制
             NONE: 不进行控制
             LOW:较低的质量要求
             NORMAL: 一般的质量要求
             HIGH: 较高的质量要求
                                  默认 NONE
                               若图片质量不满足要求，则返回结果中会提示质量检测失败
      *
      *
      *  参数 liveness_control：  活体检测控制
          NONE: 不进行控制
           LOW:较低的活体要求(高通过率 低攻击拒绝率)
          NORMAL: 一般的活体要求(平衡的攻击拒绝率, 通过率)
          HIGH: 较高的活体要求(高攻击拒绝率 低通过率)
                        默认NONE
                        若活体检测结果不满足要求，则返回结果中会提示活体检测失败
       */ 
            
         /*  
          * 根据ak与sk获取token
          *  这里直接调用   AuthService
          *  
          *  */
            AuthService as=new AuthService();
         
            String accessToken = as.getToken();

            String result = HttpUtil.post(url, accessToken, "application/json", param);
            
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        FaceAdd.add("");
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