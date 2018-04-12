<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'net01.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/jtopo-min.js">
	</script>
	<script type="text/javascript">  
        $(document).ready(function() {  
            //画布对象：canvas  
            var canvas = document.getElementById('canvas');  
            //抽象的舞台对象对应一个Canvas对象，所有图形展示的地方  
            var stage = new JTopo.Stage(canvas);  
            //场景对象  
            var scene = new JTopo.Scene(stage);  
            //背景颜色设置  
            scene.background='#CDC5BF';  
            //节点添加  
            var node = new JTopo.Node("EMC");  
            //设置节点位置  
            node.setLocation(150, 100);  
            //节点填充颜色  
            node.fillcolor='#CD0000';  
            //场景对象添加节点  
            scene.add(node);  
        });  
    </script>  
  </head>
  
  <body> 
     <canvas id="canvas" width="300" height="200"></canvas> 
  </body>
</html>
