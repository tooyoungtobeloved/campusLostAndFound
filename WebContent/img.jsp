<%@page import="java.awt.Font"%>
<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.awt.Graphics"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="java.util.Random"%>
<%@page import="java.awt.Color"%>
<%@ page language="java" contentType="image/jpeg; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%!
	  //随机产生颜色值
		public Color getColor(){
			Random ran = new Random();
			int a = ran.nextInt(256);//产生一个0-255的一个随机数
			int b = ran.nextInt(256);
			int c = ran.nextInt(256);
			return new Color(a,b,c);//red,green,blue
		}
    
    	//产生验证码值的方法,四位随机数
    	public String getNum(){
    		int result = (int)(Math.random()*9000)+1000;
    		return String.valueOf(result);
    	}
    %>
    
    <%
    	//禁止缓存，防止验证码过期
    	response.setHeader("Pragma", "no-cache");
    	response.setHeader("Cache-Control", "no-cache");
    	response.setHeader("Expires", "0");
    	
    	
    	//绘制验证码
    	BufferedImage  image = new BufferedImage(80,30,BufferedImage.TYPE_INT_RGB);
    	//画笔
    	Graphics  graphics = image.getGraphics();
    	//填充背景色
    	graphics.fillRect(0,0,200,60);
    	
    	//绘制干扰线条
    	for(int i = 0;i<30;i++){
    		Random ran = new Random();
    		int xBegin = ran.nextInt(150);
    		int yBegin = ran.nextInt(60);
    		
    		int xEnd = ran.nextInt(xBegin+15);
    		int yEnd = ran.nextInt(yBegin+15);
    		
    		graphics.setColor(getColor());
    		graphics.drawLine(xBegin,yBegin,xEnd,yEnd);
    	}
    	
    	graphics.setFont(new Font("seif",Font.TYPE1_FONT,18));
    	//绘制验证码
    	graphics.setColor(Color.BLACK);
    	String checkCode = getNum();
    	StringBuffer sb = new StringBuffer();
    	for(int i = 0;i<checkCode.length();i++){
    		sb.append(checkCode.charAt(i)+" ");//验证码的每一位数字
    		
    	}
    	
    	graphics.drawString(sb.toString(), 10, 20);
    	
    	//将验证码真实的值保存在session中，供比较
    	session.setAttribute("checkCode", checkCode);
    	
    	//真实产生图片
    	ImageIO.write(image, "jpeg", response.getOutputStream());
    	
    	out.clear();
    	out = pageContext.pushBody();//把图片push到input标签，type为image的src当中
    %>
    
    
    