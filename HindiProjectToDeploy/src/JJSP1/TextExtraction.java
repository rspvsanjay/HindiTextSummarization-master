package JJSP1;

import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.Collections;
public class TextExtraction 
{
	static TextExtraction fj;
	public String toHtmlString(String url) throws IOException 
	{
		StringBuilder sb = new StringBuilder();
		   for(Scanner sc = new Scanner(new URL(url).openStream()); sc.hasNext(); )
		      sb.append(sc.nextLine()).append('\n');
		   return sb.toString();
	}
	
	static int search(String key,String target)
	{
		int count=0;
		Pattern p=Pattern.compile(key);
		Matcher m=p.matcher(target);
		while(m.find()){count++;if(count>0)break;}
		return count;
	} 
	
	String extractText(String s) throws IOException
	{
		
		fj = new TextExtraction();		
		 
		String h1 = fj.toHtmlString(s); 
        System.out.println("extracted \n");
        
        String html="";
        long start = System.currentTimeMillis();
        html=h1;
        html=html.replaceAll("\n", " ");
                
        String html_text[]; 
        
        html=html.replaceAll("<script[^>]*>(.*?)</script>", "");
        html=html.replaceAll("<header[^>]*>(.*?)</header>", "");
        html=html.replaceAll("<footer[^>]*>(.*?)</footer>", "");
        html=html.replaceAll("<aside[^>]*>(.*?)</aside>", "");
        html=html.replaceAll("<nav[^>]*>(.*?)</nav>", "");
        html=html.replaceAll("<button[^>]*>(.*?)</button>", "");
        html=html.replaceAll("<style[^>]*>(.*?)</style>", "");
        html=html.replaceAll("<title[^>]*>(.*?)</title>", "");
        html=html.replaceAll("<ins[^>]*>(.*?)</ins>", "");
        html=html.replaceAll("<marquee[^>]*>(.*?)</marquee>", "");
        html=html.replaceAll("<link[^>]*>", "");
        html=html.replaceAll("<meta[^>]*>", "");
        html=html.replaceAll("<img[^>]*>", "");
        html=html.replaceAll("<input[^>]*>", "");
        html=html.replaceAll("<iframe[^>]*>(.*?)</iframe>", "");
        
        html=html.replaceAll("(?i)</strong>", "");
        html=html.replaceAll("(?i)</B>", "");
        html=html.replaceAll("(?i)</a>", "");
        html=html.replaceAll("(?i)</sup>", "");
        html=html.replaceAll("(?i)</span>", "");
        html=html.replaceAll("(?i)</i>", "");
        html=html.replaceAll("(?i)</TT>", "");
        html=html.replaceAll("(?i)</br>", "");
        html=html.replaceAll("(?i)<strong[^>]*>", "");
        html=html.replaceAll("(?i)<sup[^>]*>", "");
        html=html.replaceAll("(?i)<span[^>]*>", "");
        html=html.replaceAll("(?i)<i[^>]*>", "");
        html=html.replaceAll("(?i)<b>", "");
        html=html.replaceAll("(?i)<b[^>]*>", "");
        html=html.replaceAll("(?i)<A[^>]*>", "");
        html=html.replaceAll("(?i)<A>", "");
        html=html.replaceAll("(?i)<TT[^>]*>", "");
        html=html.replaceAll("(?i)<br[^>]*>", "").trim();
        
        html=html.replaceAll("<", "\n<");
        html=html.replaceAll(">", ">\n ");
        //html=html.replaceAll("<", "\n<");
        //html=html.replaceAll(">", ">\n ");
        html_text = html.split("\n");
        
        
        ArrayList<Integer> length1 = new ArrayList<Integer>();//ArrayList<String> obj = new ArrayList<String>();
        ArrayList<Float> ratio = new ArrayList<Float>();//ArrayList<String> obj = new ArrayList<String>();
        ratio.add(0.0f);
        ArrayList<String> line = new ArrayList<String>();
        int total =1;
        
        for(String h:html_text)
        {	                	
        	if(h.startsWith("</") && h.endsWith(">") && length1.size()!=0)
        	{
        		total = total - length1.get(length1.size() - 1);
        		length1.remove(length1.size() - 1);
        	}
        	else if(search("<(.*)>",h)>0)
        	{
        		total = total+h.length();
        		length1.add(h.length());
        	}
        	else
        	{
        		if(h.length()>0 && h.trim().length() > 0)
        		{
        			String h4[] = h.split(" ");
        			h="";
        			for(String h5:h4)
        			{	
        					if(h5.startsWith("&") && h5.endsWith(";") || h5.endsWith("|")){}
        					else	{	 h += h5 +" ";	}        				
        			}
        			float a,b;a=h.length();b=total;                			
        			ratio.add((a/b));
        			line.add(h);
        		}
        	}
        }
       
        float max_r=Collections.max(ratio);
        int i1=-1;
        String text="";
        
        for(float r:ratio)
        {
        	if(r>0)
        	{
        		i1=i1+1;	
        		if(r > max_r*0.45){	text += line.get(i1) + "\n";}
        		else if(r > max_r*0.3)
        		{ 
        			String temp=line.get(i1);                			
        			if(temp.endsWith(".")){text += temp + "\n";}	
        		}
        		else {}                		
        	}
        }       
        long end = System.currentTimeMillis();
        System.out.println(" progaram end in "+(end-start)/1000+" seconds"+" or "+(end-start)+" miliseconds");//System.out.println(++i2+" th loop end in "+(end-start)/1000+" seconds");
       
        System.out.println("finished  length : "+text.length());
        text = text.replaceAll("\\[(.*?)\\]", "");
		
		return text;
	}	
	
}

