<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>T-SUMM</title>
<style>#info 
{
	float:left;
	text-align:center;
	width:770px;
	height:600px;
	background:#fcfcfc;border:thin solid;border-color:#CCC;-moz-border-radius: 5px;
	-webkit-border-radius: 5px;-o-border-radius: 5px;font-family: "Myriad Pro";font-size: 12px;
}

</style>

<script type="text/javascript">
    function decodeURL(tt) {
      return decodeURIComponent(tt);
    }
  </script>

</head>
<body>
<p align="right"><a href="https://yss2015000906.herokuapp.com/"> Click here for English version</a></p>



<%@ page import="JJSP1.ESUMTest24"%>
<% 
	String[] values=request.getParameterValues("habits");
//for(int i=0;i<values.length;i++){out.println(values[i]);}
 %>
 <table border="2"><td>
 <%@ page import="java.net.URLDecoder"%>
 <% 
				out.println("&nbsp;You have entered "+(values.length-2)+" URL(s) for the Query \""+values[0]+"\"<br><br>");
				out.println();
				String decoded1 = URLDecoder.decode(values[1]);
				out.println("&nbsp;"+decoded1+"");out.println();
						for(int i=2;i<(values.length-1);i++)
						{
							String decoded = URLDecoder.decode(values[i]);
        				 			out.println("<br><br>&nbsp;"+decoded);out.println();
        					 
						}
			
			%> 
			
 
 </td></table><center><h2>Hindi Text Summary</h2></center>
 
 <%
	String skk="";
	skk=skk+values[1];
	for(int i=2;i<(values.length-1);i++)
	       
	{	
       
		skk=skk+"::::::"+values[i];
   
	}
	String s2 = Integer.toString(values.length-2);
	String sl = values[values.length-1];
	
	try{
			String str=(new ESUMTest24()).callByPython(values[0],s2,skk,sl);
			out.println("<script>function goBack() {window.history.back();}</script>");
			
			String str2[]=str.split("\n");
			
				for(String h:str2)
				{
			%>
			
			
			<% 
				out.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+h+"<br>");
			%>
			
			<%
				}
				}catch(Exception eee){}
			%>
			
			
			<br><br>
			
			
			
			<center>
			<INPUT Type="button" VALUE="GoBack" onClick="history.go(-1);return true;">
			</center>
			
			<br>
			
			<hr>
			<center><p style="display:inline-block; vertical-align:bottom;">This project is sponsored by <a href="http://www.dst.gov.in/"> DST </a> - <a href="http://www.serb.gov.in/home.php"> SERB </a> &nbsp;&nbsp;(Ref No. YSS/2015/000906)
			<br><br>
			<br>Developed by &nbsp;&nbsp;  <a href="https://rspvsanjayprofile.herokuapp.com/"> Sanjay Kumar Gupta (JRF)</a> &nbsp;&nbsp;
			Supervised by &nbsp;&nbsp;<a href="https://sites.google.com/site/ravindranathchowdaryshomepage/">  Dr. Ravindranath Chowdary C</a></p>
			</center><html>
			<% 
				out.close(); 
				
			%>   
</body>
</html>