<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html lang="ZH-CN">
<head>
  <meta charset="utf-8">
  <title>web RTC 测试</title>
  <style>
    .booth {
      width:400px;
     
      background:#ccc;
      border: 10px solid #ddd;
      margin: 0 auto;
    }
  </style>
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script></head>
<body>
  <video width="200px" height="150px"></video>
  <canvas id="canvas" width="200px" height="150px"></canvas>
  <p>
      <button id="start">打开摄像头</button>
      <button id="snap">截取图像</button>
      <button id="close">关闭摄像头</button>
         <button onclick="aa()">登录</button>
  </p>
 
 
  
</body>
</html>
<script>
var  base="";
 var Pic="";
    window.onload = function () {
           var canvas = document.getElementsByTagName('canvas')[0],
               context = canvas.getContext('2d'),
               video = document.getElementsByTagName("video")[0],
               snap = document.getElementById("snap"),
               close = document.getElementById("close"),
               start = document.getElementById("start"),
               MediaStreamTrack;
           start.addEventListener('click', function () {
               if (navigator.mediaDevices && navigator.mediaDevices.getUserMedia) {
                   navigator.mediaDevices.getUserMedia({
                       video: true,
                       audio: true
                   }).then(function (stream) {
                       console.log(stream);
                       MediaStreamTrack=typeof stream.stop==='function'?stream:stream.getTracks()[1];
                       video.src=(window.URL).createObjectURL(stream);
                       video.play();
                   }).catch(function(err){
                       console.log(err);
                   });
               }else if(navigator.getMedia){
                   navigator.getMedia({
                       video: true
                   }).then(function (stream) {
                       console.log(stream);
                       MediaStreamTrack=stream.getTracks()[1];
                       video.src=(window.webkitURL).createObjectURL(stream);
                       video.play();
                   }).catch(function(err){
                       console.log(err);
                   });
               }
           });
           snap.addEventListener('click', function () {
               context.drawImage(video, 0, 0,200,150);
                 Pic = document.getElementById("canvas").toDataURL("image/jpg");              
                 //对其进行base64编 之后的字符串
                 Pic = Pic.replace(/^data:image\/(png|jpg);base64,/,"")
           });
           close.addEventListener('click', function () {
               MediaStreamTrack && MediaStreamTrack.stop();
           });
           
           
                         
           
           
        //   var imgSrc = document.getElementById("canvas").toDataURL("image/jpg");
			//	  base= imgSrc.split("base64,")[1];
				 //alert(imgSrc.split("base64,")[1]);
		
				 
       }
     
        function aa(){
        		// alert(Pic); 
				 //
				$.ajax({
              url:"facelogin",
             type:"post",
             data:{"LoginBase":Pic},
             success:function(data){
            var  result= JSON.parse(data);
       //  alert(result.error_msg);  验证状态
       alert(result.result.user_list[0].user_id);
             }
             });
        }
  </script>
 