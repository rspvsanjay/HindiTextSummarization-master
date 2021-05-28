package JJSP1;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.io.IOException;
import java.text.BreakIterator;
import java.util.Locale;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ESUMTest24
{	int call[];int n1=0,nu=0,n2=0;
	static ESUMTest24 e;int r=0;
	static String term[]=null,s,URLs[],sent[];
	double vm[][];
	String sentences[];String sterms[][];
	double ns[][];
		
	String []wordReading(String s)
	{
		int count=0;
		Pattern p=Pattern.compile(" ");
		Matcher m=p.matcher(s);
		Matcher m1=p.matcher(s);
		int i=0,j=0;
		while(m.find())
		{
			count++;
		}
		String str[]=new String[count+1];
		while(m1.find())
		{
			str[i]=s.substring(j,m1.start()).trim();//stemed(s.substring(j,m1.start()).trim());
			j=m1.start();
			//System.out.println(str[i]);
			i++;j++;
		}	str[i]=s.substring(j,s.length());
			return str;
	}

	static String stemed(String sha)
	{
		if ((sha.length() > 6) && (sha.endsWith("ाएंगी")
		        || sha.endsWith("ाएंगे")
		        || sha.endsWith("ाऊंगी")
		        || sha.endsWith("ाऊंगा")
		        || sha.endsWith("ाइयाँ")
		        || sha.endsWith("ाइयों")
		        || sha.endsWith("ाइयां")		        
		      ))		      
		 {sha=sha.substring(0, sha.length() - 5);System.out.println("1 ");}
		
		if ((sha.length() > 5) && (sha.endsWith("ाएगी")
		        || sha.endsWith("ाएगा")
		        || sha.endsWith("ाओगी")
		        || sha.endsWith("ाओगे")
		        || sha.endsWith("एंगी")
		        || sha.endsWith("ेंगी")
		        || sha.endsWith("एंगे")
		        || sha.endsWith("ेंगे")
		        || sha.endsWith("ूंगी")
		        || sha.endsWith("ूंगा")
		        || sha.endsWith("ातीं")
		        || sha.endsWith("नाओं")
		        || sha.endsWith("नाएं")
		        || sha.endsWith("ताओं")
		        || sha.endsWith("ताएं")
		        || sha.endsWith("ियाँ")
		        || sha.endsWith("ियों")
		        || sha.endsWith("ियां")
		        ))
			{sha=sha.substring(0, sha.length() - 4);System.out.println("2 ");}
		if ((sha.length() > 4) && (sha.endsWith("ाकर")
		        || sha.endsWith("ाइए")
		        || sha.endsWith("ाईं")
		        || sha.endsWith("ाया")
		        || sha.endsWith("ेगी")
		        || sha.endsWith("ेगा")
		        || sha.endsWith("ोगी")
		        || sha.endsWith("ोगे")
		        || sha.endsWith("ाने")
		        || sha.endsWith("ाना")
		        || sha.endsWith("ाते")
		        || sha.endsWith("ाती")
		        || sha.endsWith("ाता")
		        || sha.endsWith("तीं")
		        || sha.endsWith("ाओं")
		        || sha.endsWith("ाएं")
		        || sha.endsWith("ुओं")
		        || sha.endsWith("ुएं")
		        || sha.endsWith("ुआं")
		        ))
			{sha=sha.substring(0, sha.length() - 3);System.out.println("3 ");}
		if (( sha.length()> 3) && (sha.endsWith("कर")
		        || sha.endsWith("ाओ")
		        || sha.endsWith("िए")
		        || sha.endsWith("ाई")
		        || sha.endsWith("ाए")
		        || sha.endsWith("ने")
		        || sha.endsWith("नी")
		        || sha.endsWith("ना")
		        || sha.endsWith("ते")
		        || sha.endsWith("ीं")
		        || sha.endsWith("ती")
		        || sha.endsWith("ता")
		        || sha.endsWith("ाँ")
		        || sha.endsWith("ां")
		        || sha.endsWith("ों")
		        || sha.endsWith("ें")
		        ))
			{sha=sha.substring(0, sha.length() - 2);System.out.println("4 ");}
		if ((sha.length() > 2) && (sha.endsWith("ो")
		        || sha.endsWith("े")
		        || sha.endsWith("ू")
		        || sha.endsWith("ु")
		        || sha.endsWith("ी")
		        || sha.endsWith("ि")
		        || sha.endsWith("ा")
		       ))
			{sha=sha.substring(0, sha.length() - 1);System.out.println("5 ");}
		return sha;
	}	
	
	String removeStopWord(String source)
	{	
		String str[] ={"के","का","एक","में","की","है","यह","और","से","हैं","को","पर","इस","होता","कि","जो","कर","मे","गया","करने","किया","लिये","अपने","ने","बनी","नहीं","तो","ही","या","एवं","दिया","हो","इसका","था","द्वारा","हुआ","तक","साथ","करना","वाले","बाद","लिए","आप","कुछ","सकते","किसी","ये","इसके","सबसे","इसमें","थे","दो","होने","वह","वे","करते","बहुत","कहा","वर्ग","कई","करें","होती","अपनी","उनके","थी","यदि","हुई","जा","ना","इसे","कहते","जब","होते","कोई","हुए","व","न","अभी","जैसे","सभी","करता","उनकी","तरह","उस","आदि","कुल","एस","रहा","इसकी","सकता","रहे","उनका","इसी","रखें","अपना","पे","उसके"}; 
		for(int i=0;i<str.length;i++)
		{
			source=source.replaceAll(" "+str[i]+" "," ");
		}
		return source;
	}
	//int inty;
	String []splitText(String content)
	{	
		BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.US);
		iterator.setText(content);
		BreakIterator iterator1 = BreakIterator.getSentenceInstance(Locale.US);
		iterator1.setText(content);
		int start = iterator.first();
		int start1 = iterator1.first();
		String s1[];
		int n=0;//inty=2017;
		for (int end1 = iterator1.next();end1 != BreakIterator.DONE;start1 = end1, end1 = iterator1.next()) 
		{		n++;		}
		s1=new String[n];
		for (int i=0,end = iterator.next();end != BreakIterator.DONE;start = end, end = iterator.next(),i++) 
		{		s1[i]=content.substring(start,end);
			//System.out.println(content.substring(start,end));
		}
		
		return s1;
	
	}	
	int level;
	int arrrr[][];
	int ccc[];int check,checj=0;
	int nod;
	String straa[];
	int [] documentExtraction(String source[]) throws Exception
	{	
        	sentences=new String[source.length];
		for(int i=0;i<source.length;i++){sentences[i]=source[i];}
		for(int i=0;i<sentences.length;i++)
		{	sentences[i]=e.removeStopWord(" "+sentences[i]);//sentesce with initial space
			
		}
        	int l=sentences.length; call=new int[l];
        	arrrr=new int[l][l];ccc=new int[l];
        	sterms=new String[l][];

//System.out.println("number of nodes : "+l);System.out.println();System.out.println();
		Date d1;
        	vm=new double[l][l];//int ccc=0;
        	for(int i=0;i<l;i++)
        	{	
        		sterms[i]=e.wordReading(((sentences[i].trim().replaceAll(" +", " ")).replaceAll("\\.", "")).replaceAll(",", ""));
        	}
//System.out.println();System.out.println();
		d1=new Date();
		
//System.out.println("number vectors matrix");System.out.println();System.out.println();
		Calendar ca = Calendar.getInstance();
		for(int i=0;i<sentences.length;i++)
		{	//System.out.println(i);
			for(int j=0;j<sentences.length;j++)
			{	if(i==j){vm[i][j]=0;}else{
				vm[i][j]=e.vectorEvaluation(sterms[i],sterms[j]);}//System.out.print(Double.parseDouble(df2.format(vm[i][j]))+" : \t");//ccc++;
			}//System.out.println();	//System.out.println(term.length);
		}//System.out.println(ccc);
			
		ns=new double[term.length][sentences.length];
//System.out.println();System.out.println();System.out.println("nodes score of "+l+" nodes");System.out.println();System.out.println();
		String tq[][];tq=new String[term.length][1];
		check = ca.get(Calendar.YEAR);
		checj=(ca.get(Calendar.MONTH)+1);	
    		for(int i=0;i<term.length;i++)tq[i][0]=term[i];//if(check==inty)
		System.out.println();
		System.out.println("Please Wait for a minute");//if((check==inty)&&checj<5)
		for(int i=0;i<term.length;i++)//term.length
		{	for(int j=0;j<sentences.length;j++)//sentences.length
			{	int arr23[];arr23=new int[sentences.length];int c23=0;
				for(int k=0;k<sentences.length;k++){arr23[c23]=k;c23++;}
				ns[i][j]=e.nodeScore(arr23,j,tq[i],sentences.length,1);	//
				System.out.print(".");
			}
		}
		
		
//System.out.println();System.out.println();System.out.println("total number of call for each node to calculate node score");System.out.println();
		
//for(int j=0;j<sentences.length;j++){System.out.println(" : "+call[j]);}
		
//System.out.println();System.out.println();
		
//System.out.println("nodes CPathScore of "+l+" nodes");System.out.println();System.out.println();
		double cps[][]; cps=new double[term.length][sentences.length];//CPATHScore collector
		
		for(int j=0;j<sentences.length;j++)//sentences.length
		{	nod=j;
			for(int i=0;i<term.length;i++)//term.length
			{	level=-1;
				cps[i][j]=ns[i][j]+e.cPathScore(j,i,sentences.length);	//System.out.println(cps[i][j]);
			}
		}
//System.out.println();System.out.println();
		ca.setTime(d1);
		
//System.out.println("selected node each node with all query terms ");System.out.println();System.out.println();
		
//for(int j=0;j<sentences.length;j++){for(int i=0;i<(ccc[j]+1);i++)System.out.print(" : "+arrrr[j][i]);System.out.println();}
		double sgs[];sgs=new double[l];
//System.out.println();System.out.println();
		
//System.out.println("SGraph Score for selected nodes");System.out.println();System.out.println();
		for(int j=0;j<sentences.length;j++)
		{
			sgs[j]=0.0;
			for(int i=0;i<term.length;i++)//term.length
			{		
				sgs[j]=sgs[j]+cps[i][j];	
			
			}//System.out.println(sgs[j]);
			if(Math.sqrt(ccc[j])!=0.0)
			{	sgs[j]=sgs[j]/Math.sqrt(ccc[j]+1);System.out.print(".");
//System.out.println(sgs[j]+" : ");
			}
			else{sgs[j]=0.0;}
		}
		int more=0;
		double tt=0;
		double sgs1[];
		//double ttt;
		sgs1=sgs;
		for(int j=1;j<sentences.length;j++)
		{
			if(tt<sgs1[j])
			{ 
				//ttt=tt;
				tt=sgs1[j];
				//sgs1[j]=ttt;
				more=j;
			}
		}
//System.out.println();System.out.println();
		
//System.out.println("max sgraphscore node number "+more);System.out.println();System.out.println();
		
//System.out.println("selected nodes for max sgraphscore node : ");System.out.println();System.out.println();
		int arrr1[];
		arrr1=new int[ccc[more]+1];
		if(more!=l)
		for(int i=0;i<(ccc[more]+1);i++)
		{	
			System.out.print(".");
//System.out.print(arrrr[more][i]+" : tu");
			arrr1[i]=arrrr[more][i];
		}	
		
		int tttt=0;int n=ccc[more]+1;
		for(int i=0;i<n;i++)
		{
			for(int j=i;j<n;j++){if(arrr1[i]>arrr1[j]){	tttt=arrr1[i];arrr1[i]=arrr1[j];arrr1[j]=tttt;}}// node number made in sequence
		}
		//for(int i=0;i<arrr1.length;i++)		{System.out.println(arrr1[i]+"vv");}
		int arrr2[];
		arrr2=new int[sentences.length-arrr1.length];
		int kct=0;
		for(int i=0;i<sentences.length;i++)// select the sentence which is not selected in s-graph
		{	int k=0;
			for(int j=0;j<arrr1.length;j++)	{	if(i==arrr1[j]){k=1;}	}
			if(k==0){arrr2[kct]=i;kct++;}
		}
		kct=0;
		double arrr3[];double maxone=-1,maxtwo=0;
		arrr3=new double[arrr1.length];
		double arrr4[];arrr4=new double[arrr2.length];
		for(int i=0;i<arrr2.length;i++)
		{	maxtwo=0;maxone=-1;
			for(int j=0;j<arrr1.length;j++)
			{
				arrr3[j]=vm[arrr2[i]][arrr1[j]];
				if(arrr3[j]>maxone){maxone=arrr3[j];}
			}
			for(int j=0;j<term.length;j++)
			{	maxtwo=maxtwo+ns[j][arrr2[i]];}	
			arrr4[i]=maxtwo*0.6-0.4*maxone;//System.out.println();
		}
		double arrr5=-2;int jlk=0;
		for(int i=0;i<arrr2.length;i++)
		{		
			if(arrr4[i]>arrr5){	arrr5=arrr4[i];jlk=arrr2[i];	}	
		}
		String stra="";
		int ick=0;
		stra=source[jlk];
		String straaa[];
		straaa=new String[straa.length];
		for(int i=0;i<straa.length;i++){straaa[i]=straa[i];}
		straa=new String[straaa.length+1];
		for(int i=0;i<straaa.length;i++){straa[i]=straaa[i];ick++;}
		straa[ick]=stra;
		//System.out.println();System.out.println(straa[ick]+" length : "+straa[ick].length());System.out.println();
		
		return arrr1;
	}
	
	double cPathScore(int k,int iterm,int r)
	{	double arr[];int c=0,arr1[];int v=(sentences.length+1);
		double ws=0.0,add=0.0,temp1=0;
		if(r<1){return 0.0;}
		else
		{
			level++;
			for(int i=0;i<sentences.length;i++)
			{
				if(vm[k][i]>0.01)
				{	
					c++;
				}
			}	
			
			arr=new double[c];arr1=new int[c];c=0;
			
			for(int i=0;i<sentences.length;i++)
			{
				if(vm[k][i]>0.01)
				{	
					arr[c]=vm[k][i];arr1[c]=i;//System.out.print(i+" : ");
					//if(temp1<ns[iterm][i]){temp1=ns[iterm][i];v=i;}
					c++;
				}
			}
			if(c>3)
			{
				for(int j=0;j<3;j++)
				{	double temp=0.0;//,add=0.0;
					for(int i=j+1;i<arr.length;i++)
					{
						if(arr[j]<arr[i])
						{	
							temp=arr[j];	
							arr[j]=arr[i];
							arr[i]=temp;
						}
					}	
					add=add+arr[j];
				} add=add/3; add=add/arr[0];
			}
			if(c==0)return 0.0;
			if(c==1)add=1.0;
			if(c==2){add=(arr[0]+arr[1])/2;	if(arr[0]>arr[1]){add=add/arr[0];}else{add=add/arr[1];}}
			if(c==3)
			{
				add=(arr[0]+arr[1]+arr[2])/3;
				double temp3=0.0;
				for(int i=1;i<3;i++)
					{	
						if(arr[0]<arr[i])
						{	
							temp3=arr[0];	
							arr[0]=arr[i];
							arr[i]=temp3;
						}
					}add=add/arr[0];
			}
			
			c=0;
			int arr2[];
			arr2=new int[arr1.length];
			for(int i=0;i<arr1.length;i++)
			{
				for(int j=0;j<sterms[arr1[i]].length;j++)
				{
					if(sterms[arr1[i]][j].equals(term[iterm]))
					{
						arr2[c]=arr1[i];c++;break;
					}
				}
			}
			for(int i=0;i<c;i++)
			{	
				if(ns[iterm][arr2[i]]>temp1)
				{
					temp1=ns[iterm][arr2[i]]; 
					//if(v!=(sentences.length+1))
						v=arr2[i];
				}		
			}
			if(v!=(sentences.length+1)){	int kk=0;if(ccc[nod]==0){arrrr[nod][0]=k;}
			for(int i=0;i<(ccc[nod]+1);i++){if(arrrr[nod][i]==v){kk++;break;}}if(kk==0){ccc[nod]++;arrrr[nod][ccc[nod]]=v;}
			ws=(add*1.5*vm[k][v]+ns[iterm][k])/((level+1)*(level+1))+e.cPathScore(v,iterm,r-1);}
		}
		return ws;
	}
	int i=0;
	
	double nodeScore(int arr[],int k,String [] trm,int r,int r1)
	{	
//call[k]++;//System.out.println(i++);
		double ws,d=0.85,sumpart=0,temp1=0,temp2=0,temp3=0;
			if(r<1||r1<1){return 1.0;}
			int ar[],c=0,kr=0;
			ar=new int[arr.length-1];//System.out.println("check "+arr.length);
			for(int i=0;i<arr.length;i++){if(arr[i]!=k){ar[c]=arr[i];c++;}}//System.out.println();
			
			//System.out.println(" connected node with : "+k);System.out.println();
			for(int i=0;i<ar.length;i++)
			{	//System.out.println(ar[i]);
				if(vm[k][ar[i]]>0.01)
				{	kr=1;temp3=e.adjcentSumVectorEvaluation(i);
					temp2=vm[k][ar[i]]/temp3;
					if(temp3==0.0)temp2=0;//if(e.adjcentSumVectorEvaluation(i)==0)System.out.print("First "+e.adjcentSumVectorEvaluation(i)+" : ");
					//System.out.print(ar[i]+" : ");
					if(Double.isNaN(temp2))temp2=0.0;
					sumpart=sumpart+temp2*e.nodeScore(ar,ar[i],trm,(r-1),(r1-1));
				}
			}	//System.out.println();
			if(kr==0){return 1.0;}
			temp3=e.sumVectorEvaluation(trm);if(temp3==0.0)temp1=0.0;
			temp1=e.vectorEvaluation(sterms[k],trm)/temp3;//if(e.sumVectorEvaluation(trm)==0)System.out.println("Second "+e.sumVectorEvaluation(trm)+" : ");if(Double.isNaN(temp1))temp1=0.0;	
			if(temp3==0.0)temp1=0.0;
			ws=d*temp1+(1-d)*sumpart;
		return ws;
	}
	
			
  	
	double sumVectorEvaluation(String []trm)
	{	double rn=0;
		for(int j=0;j<sentences.length;j++)
		{	rn=rn+e.vectorEvaluation(sterms[j],trm);}
		return rn;
	}
	
	
	double adjcentSumVectorEvaluation(int k)
	{	double add=0;
		for(int i=0;i<sentences.length;i++)
		{
			if(vm[k][i]>0.01)
			{
				add=add+vm[k][i];//e.vectorEvaluation(sterms[k],sterms[i]);
			}
		}
		return add;
	}
	
	double vectorEvaluation(String one[],String two[])
	{	
		int vl=0;ArrayList<String>uniquee;
		String one1[],two2[];
		if(one.length>=two.length){ one1=new String[one.length];two2=new String[two.length];
			for(int i=0;i<one.length;i++){one1[i]=one[i];}
			for(int i=0;i<two.length;i++){two2[i]=two[i];}
		//uniquee=e.countVectorLength(one,two);
		}
		else{	one1=new String[two.length];two2=new String[one.length];
			for(int i=0;i<two.length;i++){one1[i]=two[i];}
			for(int i=0;i<one.length;i++){two2[i]=one[i];}
				//uniquee=e.countVectorLength(two,one);
			}
		uniquee=e.countVectorLength(one1,two2);	
		vl=uniquee.size();
		String arr[];
		arr=new String[vl];//System.out.println(vl);
		int cc=0;
		for (String element : uniquee) 
		{
	    		arr[cc]=element;
			cc++;
		}
		double v1[],v2[];
		v1=new double[vl];
		v2=new double[vl];
		int count;
		for(int i=0;i<vl;i++)
		{	int k=0,l=0;double d=e.logForm(arr[i]);
			for(int j=0;j<one1.length;j++)
			{
				if(arr[i].equals(one1[j]))k++;
			}v1[i]=k*d;
			for(int j=0;j<two2.length;j++)
			{
				if(arr[i].equals(two2[j]))l++;
			}v2[i]=l*d;			
		}
		double rt1=0,rt2=0,rt3=0,rtf=0;
		for(int i=0;i<vl;i++)
		{
			rt1=rt1+v1[i]*v2[i];
			rt2=rt2+v1[i]*v2[i];
			rt3=rt3+v2[i]*v2[i];
		}
		rtf=rt1/(Math.sqrt(rt2)*Math.sqrt(rt3));if(Double.isNaN(rtf))rtf=0.0;	//System.out.print(rt1+" : "+(Math.sqrt(rt2)*Math.sqrt(rt3))+" : ");
		return rtf;
	}
	
	ArrayList<String> removeDuplicates(ArrayList<String> list) 
	{
		// Store unique items in result.
		ArrayList<String> result = new ArrayList<String>();
		// Record encountered Strings in HashSet.
		HashSet<String> set = new HashSet<String>();
		// Loop over argument list.
		for (String item : list) 
		{
		    // If String is not in set, add it to the list and the set.
	    		if (!set.contains(item)) 
			{
				result.add(item);
				set.add(item);
	    		}
		}
		return result;
    	}
	
	ArrayList<String> countVectorLength(String[]arr12,String[]arr22)
	{
		ArrayList<String> list = new ArrayList<String>();
		for(int i=0;i<arr12.length;i++)
		{list.add(arr12[i]);}
		for(int i=0;i<arr22.length;i++)
		{list.add(arr22[i]);}
		// Remove duplicates from ArrayList of Strings.
		ArrayList<String> unique = e.removeDuplicates(list);
		return unique;	
	}	
	
	double logForm(String temp)
	{	double tt=0;
		int N=sentences.length,n=0,count;
		for(int i=0;i<sentences.length;i++)
		{	count=0;
			for(int j=0;j<sterms[i].length;j++)
			if(temp.equals(sterms[i][j]))count++;
			if(count>0)n++;		
		}
		tt=Math.log(N/(n+1)); if(Double.isNaN(tt))tt=0.0;
		return tt;
	}
	int [] recall(String source[]) throws Exception
	{	int temp[],f1[],f2[];f1=new int[source.length];
		int ddd;temp=null;
		if((source.length/n2)<2){ddd=2;}else{	ddd=source.length/n2;}
		String str[];str=new String[ddd];
		int count=0,cc=0;
		int ct=0,div=source.length/ddd;int j=0;System.out.println(" div : "+div+"s length : "+source.length);
		for(;(j<source.length)&&(count<=(div-1));j++)
		{			
			str[ct]=source[j];ct++;
			if((ct%ddd==0)&&(count<=(div-1)))
			{
				ct=0;
				temp=e.documentExtraction(str);
				for(int i=0;i<temp.length;i++){	f1[cc]=temp[i]+count*ddd;cc++;	}count++;
			}
		} 
		ct=0;
		String []str1=new String[source.length-j];
		
//System.out.println("size of last array : "+(source.length-j));
		for(int k=j;(k<source.length);k++)
		{
			str1[ct]=source[k];//System.out.println("kk");
			ct++;
		}
		if((source.length>0)&&(source.length%ddd!=0))
		temp=e.documentExtraction(str1);
		if((temp!=null)&&(source.length%ddd!=0))for(int i=0;i<temp.length;i++){	f1[cc]=temp[i]+count*ddd;cc++;}
		f2=new int[cc];for(int i=0;i<cc;i++){	f2[i]=f1[i];	}	
		return f2;	
	}

	String []refreshSentence(String []s3) throws Exception 
	{	String s4[],s5[];int c1234=0;
		s4=new String[s3.length];
		int i;
		for(i=0;i<(s3.length-1);i++)
		{
			if(i==0)
			{	
				if(s3[i].length()<70)
				{	
					s4[c1234]=s3[i]+" "+s3[i+1];
					i++;c1234++;
				}
			}
			else
			{
				if(s3[i].length()<70)
				{	 //System.out.println("s4 length = "+s4.length+" s3 length = "+s3.length+" i and i+1 print i="+i);
					s4[c1234]=s3[i]+s3[i+1];
					i++;
					c1234++;
				}
				else	
				{
					s4[c1234]=s3[i];c1234++;
				}
			}
		}
		if(i<s3.length){s4[c1234]=s3[i];c1234++;}
		s5=new String[c1234];
		for(int j=0;j<c1234;j++){s5[j]=s4[j];}	
		return s5;	
	}
	
	String []stepCall(String s3) throws Exception 
	{	
		String Ssentences[],sent1[];Ssentences=null;
		
		int aarr[];
		  
        	String source;
		
		Ssentences=e.refreshSentence(e.splitText(s3));
		/*for(int i=0;i<Ssentences.length;i++)
		{
			if(Ssentences[i].length()<80)
			{	
				System.out.println();
				System.out.println(Ssentences[i]+"  l : "+Ssentences[i].length());
				System.out.println();
			}
		}*/
		
		if(Ssentences.length==0){System.out.println(" Please check your network connection or visit website, it migth not contain any text ");}
		sent = new String[Ssentences.length];
		for(int i=0;i<Ssentences.length;i++)
		{ 	sent[i]=Ssentences[i];		}
		
		System.out.println("total number of nodes : "+(Ssentences.length));
		//if(Ssentences.length>0)					
		aarr=e.recall(sent);System.out.println();System.out.println();
		sent1=new String[aarr.length];
		System.out.println(" check point "+Ssentences.length+" : "+aarr.length);
		for(int i=0;i<aarr.length;i++)
		{	//System.out.println(i+" ck "+aarr[i]);
			sent1[i]=Ssentences[aarr[i]];//sent[aarr[i]];//
		}int rcp=n2;
		if(n2<10){rcp=10;}
		while(!(aarr.length<2*rcp))
		{	
			aarr=e.recall(sent1);
			String []temp;
			temp=new String[aarr.length];
			for(int i=0;i<aarr.length;i++){temp[i]=sent1[aarr[i]];}
			sent1=new String[aarr.length];	
			for(int i=0;i<aarr.length;i++){sent1[i]=temp[i];}
		}
		//for(int i=1;i<(sent1.length-1);i++){System.out.println(i+") "+sent1[i]);	System.out.println();System.out.println();}
		//System.out.println(s3);
		
//System.out.println("total number of nodes : "+(Ssentences.length));
				
		//String str[];str=new String[5];
		return sent1;
	}
	
	String [] multiURL(int n12,String[] ins2,int n22)throws Exception
	{
		System.out.println("how many URL you like to enter ?");
		int count=0;
		//n1=kb.nextInt();
		n1=n12;
		final ExecutorService service;
               	service = Executors.newFixedThreadPool(n1);

		List<Future<String>> list = new ArrayList<Future<String>>();
		straa=new String[0];	
		String aurl[];aurl= new String[n1];
		/*while(nu<n1)
		{
			System.out.println("Enter "+(nu+1)+"th URL");	
			aurl[nu]=kb.next();nu++;
		}*/
		aurl=ins2;
		System.out.println("how many sentences you like to see in result ?");
		//n2=kb.nextInt();
		n2=n22;
		long startTime = System.currentTimeMillis();
		for(int i=0;i<n1;i++)
		{
			Future<String> future1 = service.submit(new Foo(aurl[i]));
               		list.add(future1);
		}
		String collector[][];collector=new String[n1][];
		int i1=0;
		for(Future<String> fut : list)
			{
            			try
				{
        	    			final String str[];
		 	           	
					collector[i1]=e.stepCall(fut.get().toString());count=count+collector[i1].length; i1++;
        			}
				catch(final InterruptedException ex) 
				{
            				ex.printStackTrace();
       	 			}
				catch(final ExecutionException ex) 
				{
            				ex.printStackTrace();
        			}
			}

		
		String aaarr[],aaarr1[];aaarr=new String[count];aaarr1=new String[count];count=0;
		for(int i=0;i<n1;i++)
		{
			for(int j=0;j<collector[i].length;j++)
			{
				aaarr[count]=collector[i][j];
				aaarr1[count]=aaarr[count];count++;
			}
		}
		int arar[];arar=e.recall(aaarr1);service.shutdownNow();
System.out.println("aaarr length"+aaarr.length);
		int rcp=n2;
		if(n2<10){rcp=10;}
		if(count>n2)
		{	
			while(!(arar.length<2*rcp))
			{	
				arar=e.recall(aaarr);
				String []temp;
				temp=new String[arar.length];
				for(int i=0;i<arar.length;i++){temp[i]=aaarr[arar[i]];}
				aaarr=new String[arar.length];	
				for(int i=0;i<arar.length;i++){aaarr[i]=temp[i];}
			}
		}
		//System.out.println("aaarr length"+aaarr.length);
		int check1=0,check2=0;
		System.out.println();
		String aaarr2[];aaarr2=new String[aaarr.length];for(int i=0;i<aaarr.length;i++){aaarr[i]=aaarr[i];}
		if(aaarr.length>n2)
		arar=e.recall(aaarr2);
		String rarr[];rarr=null;
		int cky=0;
		//if((check==inty)&&checj<5)
		if(arar.length>n2)
		{	if(n2<aaarr.length){	rarr=new String[n2];	}else{	rarr=new String[aaarr.length];}
			
			for(int j=0;(j<n2)&&(j<aaarr.length);j++)
			{	check=1;
				System.out.println();System.out.println();System.out.println((j+1)+") "+aaarr[arar[j]]+" : Length "+aaarr[arar[j]].length());	System.out.println();rarr[j]=aaarr[arar[j]];
			}	
			return rarr;
		}
		else{	
			if(n2<aaarr.length){	rarr=new String[n2];	}else{	rarr=new String[aaarr.length];}			
			for(int j=0;(j<n2)&&(j<aaarr.length);j++)
			{	check2=1;cky=j;
				System.out.println();System.out.println();System.out.println((j+1)+") "+aaarr[j]+" : Length "+aaarr[j].length());	System.out.println();
				rarr[j]=aaarr[j];
			} 	//return rarr;
		    }
			
		int strc=0;//
		String rarr12[];
		rarr12=new String[n2];
		for(int i=0;i<rarr.length;i++){rarr12[i]=rarr[i];}
		cky++;
		System.out.println(" straa l : "+straa.length+" "+check2+"rarr l "+rarr.length);//System.out.println(straa[20]);
		if(check2==1)
		{
			for(int i=0;i<straa.length&&(cky)<n2;i++)
			{System.out.println();System.out.println();
				System.out.println((1+cky)+") "+straa[i]+" : Length "+straa[i].length());System.out.println();
				rarr12[cky]=straa[i];cky++;
			}
		}
		
		long stopTime = System.currentTimeMillis();System.out.println();System.out.println();
		System.out.println("Execution Time : "+(stopTime-startTime)/1000+" seconds");
		return rarr12;
	}
	String[] extractUrl(String str,int c)
	{	String str12[];int count=0;
		str12=new String[c];		
		Pattern p=Pattern.compile("::::::");
		Matcher m=p.matcher(str);
		int kk=0;
		while(m.find())
		{
			count++;
			System.out.println(m.start()+" s "+m.end()+" e ");
			str12[count-1]=str.substring(kk,m.start());
			kk=m.end();
		}
		str12[c-1]=str.substring(kk,str.length());
		return str12;
	}
	public String callByPython(String sq,String t1kk,String skk,String t2kk)throws Exception
	{	String str[],str2;str2="";
		e=new ESUMTest24();
		//kb = new Scanner(System.in);
		System.out.println("Enter a query");	
		//s=kb.nextLine();
		s=sq;
		s = s.trim().replaceAll(" +", " ");
		term=e.wordReading(e.removeStopWord(s));
		int in1kk=Integer.parseInt(t1kk);
		int in2kk=Integer.parseInt(t2kk);
		String str67[];
		if(in1kk>1)
		{
			str67=e.extractUrl(skk,in1kk);
		}
		else
		{
			str67=new String[1];str67[0]=skk;
		}
		str=e.multiURL(in1kk,str67,in2kk);
		for(int i=0;i<str.length;i++){	str2=str2+"\n\n("+(i+1)+") "+str[i];}
		return str2;
		//System.out.println();System.out.println();System.out.println(str2);
	}
	
	public static void main(String[] args) throws Exception
	{
		String str=(new ESUMTest24()).callByPython("वाराणसी","1","https://hi.wikipedia.org/wiki/%E0%A4%B5%E0%A4%BE%E0%A4%B0%E0%A4%BE%E0%A4%A3%E0%A4%B8%E0%A5%80","10");	
	}
} 

class Foo implements Callable<String> 
{	String s1;
	Foo(String s2){s1=s2;}
	static String returnText(String s7) 
	{	TextExtraction te = new TextExtraction();
	String temp="";
		try
		{
			temp = te.extractText(s7);
		}
		catch(IOException ew){}
		return temp.toString();
	}
    
	public String call() 
	{        	
        	return returnText(s1);
    	}
}
