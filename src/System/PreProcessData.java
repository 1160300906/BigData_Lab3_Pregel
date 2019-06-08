package System;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class PreProcessData {

	public static void main(String[] args) {
		String filepath="src/System/web-Google.txt";
		File file=new File(filepath);
		   try{
			   BufferedWriter out = new BufferedWriter(new FileWriter("src/System/input.txt"));
	           BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
	           String line = null;
	           int linenum=0;//统计文件行数，跳过前四行，从i=5开始
	           int newlinenum=0;//统计当前正在划分的worker中的顶点数目，如果数目大于875713/n则停止，表示完成了一次划分
	           int preID=-1;//当前行前一行的ID
	           String newline="";
	            while((line = br.readLine())!=null){//使用readLine方法，一次读一行
	               linenum++;
	               //前四行为数据描述，跳过
	               if(linenum>4) {
	            	   if(linenum==5) {
	            		    String[] linestring=line.split("\t");
	            	        int vertexID=Integer.valueOf(linestring[0]);
	            	        newline=line;
	            	        preID=vertexID;
	            	   }
	            	   else {
	            		   String[] linestring=line.split("\t");
	            	        int vertexID=Integer.valueOf(linestring[0]);
	            	        if(vertexID==preID) {
	            	        	newline=newline+","+linestring[1];
	            	        	preID=vertexID;
	            	        }
	            	        else {
	            	        	out.write(newline+"\n");
	            	        	newlinenum++;
	  	            	        newline=line;
	  	            	        preID=vertexID;
	            	        }
	            	   }
	            	   //如果vertexID!=preID，则为新的顶点
	            	 
	               }
	            } System.out.println(newlinenum);
	            br.close();   
	            out.close();
	        }catch(Exception e){
	            e.printStackTrace();
	        }
		  
	}
	
}
