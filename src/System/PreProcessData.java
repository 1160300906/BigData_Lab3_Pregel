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
	           BufferedReader br = new BufferedReader(new FileReader(file));//����һ��BufferedReader������ȡ�ļ�
	           String line = null;
	           int linenum=0;//ͳ���ļ�����������ǰ���У���i=5��ʼ
	           int newlinenum=0;//ͳ�Ƶ�ǰ���ڻ��ֵ�worker�еĶ�����Ŀ�������Ŀ����875713/n��ֹͣ����ʾ�����һ�λ���
	           int preID=-1;//��ǰ��ǰһ�е�ID
	           String newline="";
	            while((line = br.readLine())!=null){//ʹ��readLine������һ�ζ�һ��
	               linenum++;
	               //ǰ����Ϊ��������������
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
	            	   //���vertexID!=preID����Ϊ�µĶ���
	            	 
	               }
	            } System.out.println(newlinenum);
	            br.close();   
	            out.close();
	        }catch(Exception e){
	            e.printStackTrace();
	        }
		  
	}
	
}
