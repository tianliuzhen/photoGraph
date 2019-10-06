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
  <div class="booth">
    <video id="video" width="400" height="300"></video>
    <button id='tack'> snap shot</button>
    <canvas id='canvas' width='400' height='300'></canvas>
    <img id='img' src=''>
  
     <button onclick="aa()">上传</button>
  </div>
  <script>
  var base;
    var video = document.getElementById('video'),
        canvas = document.getElementById('canvas'),
        snap = document.getElementById('tack'),
        img = document.getElementById('img'),
        vendorUrl = window.URL || window.webkitURL;
    //媒体对象
    navigator.getMedia = navigator.getUserMedia ||
                         navagator.webkitGetUserMedia ||
                         navigator.mozGetUserMedia ||
                         navigator.msGetUserMedia;
    navigator.getMedia({
        video: true, //使用摄像头对象
        audio: false  //不适用音频
    }, function(strem){
        console.log(strem);
        video.src = vendorUrl.createObjectURL(strem);
        video.play();
    }, function(error) {
        //error.code
        console.log(error);
    });
    snap.addEventListener('click', function(){
        //绘制canvas图形
        canvas.getContext('2d').drawImage(video, 0, 0, 400, 300); 
        //把canvas图像转为img图片
       img.src = canvas.toDataURL("image/png");   
              var imgSrc = document.getElementById("canvas").toDataURL("image/jpg");
				  base= imgSrc.split("base64,")[1];
				//alert(base);
    })
    
    //
      function aa(){
        		 alert(base); 
				 //
				$.ajax({
              url:"billshow",
             type:"post",
             data:{"base":base},
             success:function(data){
         
             }
             });
        }
  </script>
</body>