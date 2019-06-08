package System;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Master {
	
	public static int superStep=0;//超级轮数
	public static ArrayList<Integer> workerState=new ArrayList<Integer>();
	public static HashMap<Integer, Worker> worker=new HashMap<Integer,Worker>();//所有的worker，由ID唯一标识
	
	
	//进行图划分
	public static ArrayList<ArrayList<String>> partition(int n){
		String filepath="src/System/input.txt";
		File file=new File(filepath);
		ArrayList<ArrayList<String>> lists=new ArrayList<ArrayList<String>>();
		
		   try{
	           BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
	           String line = null;
	           for(int j=0;j<n;j++) { 
	        	   ArrayList<String> e=new ArrayList<String>();
	        	   lists.add(e);
	           }
	        	//   int linenum=0;
	        	//   ArrayList<String> vertexs=new ArrayList<String>();
	           
	        	   while((line = br.readLine())!=null){//使用readLine方法，一次读一行
//	        		   linenum++;
//	                   if(linenum>739453/n) {
//	            		   break;
//	            	   }
	                   String[] linestring=line.split("\t");
	            	   int vertexID=Integer.valueOf(linestring[0]);
	            	   int k=vertexID%n;
	            	   lists.get(k).add(line);
	            	//	   vertexs.add(line);
	               }
	        	
//	        	   for(int i=0;i<lists.size();i++) {
//	   				for(int j=0;j<lists.get(i).size();j++) {
//	   					String[] linestring=lists.get(i).get(j).split("\t");
//	   					String[] outVertex=linestring[1].split(","); 
//	   	            	   for(int k=0;k<outVertex.length;k++) {
//	   	            		   int vertexID=Integer.valueOf(outVertex[k]);
//	   	            		   if(!list1.contains(vertexID)) {	   	            			
//	   		            	   list1.add(vertexID);
//	   		            	 //  list2.add(vertexID);
//	   		            	 //  int k1=vertexID%n;
//	   		            	 //  lists.get(k1).add(vertexID+"");
//	   	            		   }
//	   	            	   }
//	   				}
//	   			}
//	        	   for(int i=739453;i<list1.size();i++) {
//	        		  int k1=list1.get(i)%n;
// 		            	   lists.get(k1).add(list1.get(i)+"");
//	        	   }
	        	//   lists.add(vertexs);
	        	//   System.out.println(j+":"+vertexs.size()+" vertexs");
	         //  }
	           br.close();    
	        }catch(Exception e){
	            e.printStackTrace();
	        }
		  
		   return lists;
	}
	//save函数将图划分结果保存在硬盘上
	public static void save(int n) {
		ArrayList<ArrayList<String>> lists=partition(n);
		try {
			for(int i=0;i<lists.size();i++) {
				int edgesum=0;//统计边数
				BufferedWriter out = new BufferedWriter(new FileWriter("src/System/Worker"+i+".txt"));
				
				for(int j=0;j<lists.get(i).size();j++) {
					String[] linestring=lists.get(i).get(j).split("\t");
//					if(linestring.length>1) {
						String[] outVertex=linestring[1].split(","); 
	            	   edgesum=edgesum+outVertex.length;
	            	   out.write(lists.get(i).get(j)+"\n");	   
//					}else {
//						out.write(lists.get(i).get(j)+"\n");	 
//					} 	   
				}				
				   System.out.println("Worker"+i+":"+lists.get(i).size()+" vertexs,"+edgesum+" edges");
				   out.close();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
//	public static void save(int n) {
//	  ArrayList<ArrayList<Integer>> lists=partition(n);
//	  String filepath="src/System/input.txt";
//		File file=new File(filepath);
//	   try {
//		   BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
//		   String line = null;
//		   for(int i=0;i<lists.size();i++) {
//			   int linenum=0;
//			   int edgesum=0;//统计边数
//			   BufferedWriter out = new BufferedWriter(new FileWriter("src/System/Worker"+i+".txt"));
//			   while((line = br.readLine())!=null){
//				   linenum++;
//                   if(linenum>739453/n) {
//            		   break;
//            	   }
//                   String newline="";
//                   String[] linestring=line.split("\t");
//                   newline=linestring[0]+"\t";
//                //   out.write(linestring[0]+"\t");
//            	   String[] outVertex=linestring[1].split(","); 
//            	   int num=0;
//            	   for(int j=0;j<outVertex.length;j++) {
//            		 
//            	//	   if(lists.get(i).contains(Integer.valueOf(outVertex[j]))) { 
//            			   num++;
//            			   if(newline.equals(linestring[0]+"\t")) {
//            				   newline=newline+outVertex[j];
//            			   }else {
//            				   newline=newline+","+outVertex[j];
//            			   }
//            			 //  out.write(outVertex[j]+",");
//            		 //  }
//            	   }
//            	   edgesum=edgesum+num;
//            	   out.write(newline+"\n");
//			   }
//			   System.out.println("Worker"+i+":"+lists.get(i).size()+" vertexs,"+edgesum+" edges");
//			   out.close();
//		   }
//		   br.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	//把划分好的图加载到各个worker里
	public static void load(int n,String vertexClassName) {
		 try{
			 //初始化worker结点
			 for(int j=0;j<n;j++) { 
				 Worker newWorker=new Worker();
	        	   newWorker.setId("worker"+j);
	        	   HashMap<Integer,Vertex> vertexs=new  HashMap<Integer,Vertex>();
	        	   newWorker.setVertexs(vertexs);
	        	    worker.put(j,newWorker);
			 }
			 //用ID mod n把点划分到相应的worker中 
			 for(int i=0;i<916428;i++) {
				 if(vertexClassName.equals("PageRankVertex")) {
					 Vertex newVertex=new PageRankVertex();
					 HashMap<Integer,Double> nextmsgs=new HashMap<Integer,Double>();
					 newVertex.setNextmsgs(nextmsgs);
					 newVertex.setVertexID(i);
          		     newVertex.setState("active");
          		     newVertex.setValue(1.0);//初始时所有顶点的值为1
          		     worker.get(i%n).getVertexs().put(i, newVertex);
				 }else if(vertexClassName.equals("ShortestPathVertex")){
					 Vertex newVertex=new ShortestPathVertex();
					 HashMap<Integer,Double> nextmsgs=new HashMap<Integer,Double>();
					 newVertex.setNextmsgs(nextmsgs);
					 newVertex.setVertexID(i);
          		     newVertex.setState("active");
          		     newVertex.setValue(Double.MAX_VALUE);
//          		     if(i==0) {
//          		    	  newVertex.setValue(0.0);
//          		     }else {
//          		    	newVertex.setValue(Double.MAX_VALUE);
//          		     }
          		    //初始时所有顶点的值为1
          		     worker.get(i%n).getVertexs().put(i, newVertex);
				 } else {
          		   System.out.println("不存在这样的类，输入错误，请重新输入");
          		   System.exit(0);
          	    }
			 }
			 //读图划分文件，添加相应的出边
			 for(int j=0;j<n;j++) { 
				   BufferedReader br = new BufferedReader(new FileReader(new File("src/System/Worker"+j+".txt")));//构造一个BufferedReader类来读取文件
		           String line = null;
		           while((line = br.readLine())!=null){
		        	   String[] linestring=line.split("\t");
	            	   int vertexID=Integer.valueOf(linestring[0]);
	            	   if(vertexClassName.equals("PageRankVertex")) {
	            		   HashMap<Integer, Double> nextmsgs=new HashMap<Integer,Double>();
	            		  ArrayList<Integer> outgoingEdges=new ArrayList<Integer>(); //出边的集合
	            		   if(linestring.length!=1) {
	            			   String[] outVertex=linestring[1].split(","); 
	            			   for(int i=0;i<outVertex.length;i++) {
	            			   nextmsgs.put(Integer.valueOf(outVertex[i]), 1.0/outVertex.length);
	            			   outgoingEdges.add(Integer.valueOf(outVertex[i]));
	            			   }
	            		   }	            		  
	            		   worker.get(j).getVertexs().get(vertexID).setOutgoingEdges(outgoingEdges);
	            		   worker.get(j).getVertexs().get(vertexID).setNextmsgs(nextmsgs);
	            	     }
	            	     else if(vertexClassName.equals("ShortestPathVertex")) {
	            	    	 HashMap<Integer, Double> nextmsgs=new HashMap<Integer,Double>();
	            	    	 ArrayList<Integer> outgoingEdges=new ArrayList<Integer>(); //出边的集合
		            		   if(linestring.length!=1) {
		            			   String[] outVertex=linestring[1].split(","); 
		            			   for(int i=0;i<outVertex.length;i++) {
		            			   nextmsgs.put(Integer.valueOf(outVertex[i]), worker.get(j).getVertexs().get(vertexID).getValue()+1);
		            			   outgoingEdges.add(Integer.valueOf(outVertex[i]));
		            			   }
		            		   }
		            		   worker.get(j).getVertexs().get(vertexID).setOutgoingEdges(outgoingEdges);
		            		   worker.get(j).getVertexs().get(vertexID).setNextmsgs(nextmsgs);
	            	     }
	            	     else {
	            		   System.out.println("不存在这样的类，输入错误，请重新输入");
	            		   System.exit(0);
	            	    }
		           }
	        	    br.close();
			 }
	
			 for(int i=0;i<n;i++) {
				 System.out.println("worker"+i+":"+worker.get(i).getVertexs().size()+" vertexs");
			 }
		 }
		 catch(Exception e){
	            e.printStackTrace();
	        }
	}
//	//n是要划分的worker个数，由人为指定,vertexClassName由人为指定Vertex的具体类
//	public static void load1(int n,String vertexClassName) {
//		String filepath="src/System/input.txt";
//		File file=new File(filepath);
//		   try{
//	           BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
//	           String line = null;
//	          
//	           for(int j=0;j<n;j++) { 
//	        	   int linenum=0;//统计当前正在划分的worker中的顶点数目，如果数目大于739453/n则停止，表示完成了一次划分
//	        	   Worker newWorker=new Worker();
//	        	   newWorker.setId("worker"+j);
//	        	   ArrayList<Vertex> vertexs=new ArrayList<Vertex>();
//	        	    while((line = br.readLine())!=null){//使用readLine方法，一次读一行
//	                   linenum++;
//	                   if(linenum>739453/n) {
//	            		   break;
//	            	   }
//	            	   String[] linestring=line.split("\t");
//	            	   int vertexID=Integer.valueOf(linestring[0]);
//	            	   String[] outVertex=linestring[1].split(",");
//	            	   if(vertexClassName.equals("PageRankVertex")) {
//	            		   Vertex newVertex=new PageRankVertex();
//	            		   HashMap<Integer, Double> nextmsgs=new HashMap<Integer,Double>();
//	            		   for(int i=0;i<outVertex.length;i++) {
//	            			   nextmsgs.put(Integer.valueOf(outVertex[i]), 1.0/outVertex.length);
//	            		   }
//	            		   newVertex.setVertexID(vertexID);
//	            		//   newVertex.setTotleVertexNum(739453/n);
//	            		   newVertex.setState("active");
//	            		   newVertex.setValue(1);//初始时所有顶点的值为1
//	            		   newVertex.setNextmsgs(nextmsgs);
//	            		   vertexs.add(newVertex);
//	            	     }
//	            	     else if(vertexClassName.equals("ShortestPathVertex")) {
//	            		   
//	            	     }
//	            	     else {
//	            		   System.out.println("不存在这样的类，输入错误，请重新输入");
//	            		   System.exit(0);
//	            	    }
//	            	   
//	               }
//	        	    for(int k=0;k<vertexs.size();k++) {
//	        	    	vertexs.get(k).setTotleVertexNum(vertexs.size());
//	        	    }
//	        	    System.out.println(newWorker.getId()+":"+vertexs.size()+" vertexs");
//	        	    newWorker.setVertexs(vertexs);
//	        	    workers.add(newWorker);
//	           }
//	           
//	            br.close();    
//	        }catch(Exception e){
//	            e.printStackTrace();
//	        }
//	}
	
	public static void main(String[] args) throws IOException {
//		save(5);
		System.out.println("请输入参数："
				+ "第一个参数：1.PageRankVertex 2.ShortestPathVertex\n"
				+ "第二个参数：worker结点的个数");
		Scanner input=new Scanner(System.in);
		int nnn=input.nextInt();
		int n=input.nextInt();
		String file="";
		if(nnn==1) {
			load(n, "PageRankVertex");
			file="src/System/PageRankResult.txt";
		}else if(nnn==2) {
			load(n, "ShortestPathVertex");
			file="src/System/ShortestPathResult.txt";
//			ShortestPathVertex shortestPathVertex=(ShortestPathVertex) worker.get(0).getVertexs().get(0);
//			System.out.println(shortestPathVertex.prevVertex);
//			System.exit(0);
					}
		
		int unactiveworkernum=0;//记录已停止的worker节点的数量
		//初始化各worker节点的状态
		//workerState.size()=5;
		System.out.println(n);
		for(int i=0;i<n;i++) {
			workerState.add(1);//初始时每个worker都是活跃的
		}
		BufferedWriter out = new BufferedWriter(new FileWriter(file));
		while(true) {
			out.write("-----------superstep"+superStep+"---------------\n");
				System.out.print("-----------superstep"+superStep+"---------------\n");
			for(int i=0;i<workerState.size();i++) {
				//对每一个活跃的worker节点，在其中每个顶点执行compute
				if(workerState.get(i)==1) {
					worker.get(i).setSuperStep(superStep);;
					//对当前worker中的每个顶点执行compute(),并返回worker中活跃的结点数
					int activenum=worker.get(i).runcompute();
					//如果该worker中所有结点都为unactive且没有消息在传递，则该结点停止
					if(activenum==0) {
						workerState.set(i,0);//设置状态为不活跃,记为0
					}
					out.write(worker.get(i).getId()+":"+"active");
				//	System.out.print(worker.get(i).getId()+":"+"active");
				}
				//否则，跳过这个节点，并且unactiveworkernum+1
				else{
					unactiveworkernum++;
					out.write(worker.get(i).getId()+":"+"stop");
			//		System.out.print(worker.get(i).getId()+":"+"stop");
				}
				if(nnn==1) {
						for(Integer k:worker.get(i).getVertexs().keySet()) {
//					System.out.print("\n"+worker.get(i).getVertexs().get(k).getVertexID()+"\t"+worker.get(i).
//							getVertexs().get(k).getValue()+"\t"+worker.get(i).getVertexs().get(k).getState());
					out.write("\n"+worker.get(i).getVertexs().get(k).getVertexID()+"\t"+worker.get(i).
							getVertexs().get(k).getValue()+"\t"+worker.get(i).getVertexs().get(k).getState());
				   }
				}
				else if(nnn==2) {
					for(Integer k:worker.get(i).getVertexs().keySet()) {
						if(worker.get(i).getVertexs().get(k).getValue()!=Double.MAX_VALUE) {
							System.out.print("\n"+worker.get(i).getVertexs().get(k).getVertexID()+"\t"+worker.get(i).
								getVertexs().get(k).getValue()+"\t"+worker.get(i).getVertexs().get(k).getState());
						out.write("\n"+worker.get(i).getVertexs().get(k).getVertexID()+"\t"+worker.get(i).
								getVertexs().get(k).getValue()+"\t"+worker.get(i).getVertexs().get(k).getState());
						}						
					   }
				}
			
				out.write("\n");	
			//	System.out.print("\n");
			}
			//如果所有的worker结点都停止了，则执行完毕
			if(unactiveworkernum==workerState.size()) {
				//如果是求最短路径，在计算结束后，打印最短路径
				if(nnn==2) {
					for(int i=0;i<worker.size();i++) {
						for(Integer e:worker.get(i).getVertexs().keySet()) {
							if(worker.get(i).getVertexs().get(e).getValue()!=Double.MAX_VALUE) {
								 ShortestPathVertex shortestPathVertex=(ShortestPathVertex) worker.get(i).getVertexs().get(e);
						    System.out.print(shortestPathVertex.getValue()+":"+e);
						    out.write(shortestPathVertex.getValue()+":"+e);
						    while(shortestPathVertex.getPrevVertex()!=-1) {
						    	System.out.print("->"+shortestPathVertex.getPrevVertex());
						    	out.write("->"+shortestPathVertex.getPrevVertex());
						    	shortestPathVertex=(ShortestPathVertex) worker.get(shortestPathVertex.getPrevVertex()%n).getVertexs().get(shortestPathVertex.getPrevVertex());
						    }
						    System.out.print("\n");
						    out.write("\n");
							}
						   
						}
					}
				}
				break;
			}
			//否则，进行消息传递，并进入下一个超级轮
			else {
				boolean nochange=true;
				for(int i=0;i<worker.size();i++) {
					for(Integer e:worker.get(i).getVertexs().keySet()) {
						for(Integer f:worker.get(i).getVertexs().get(e).getNextmsgs().keySet()) {
							nochange=false;
							Message m=new Message(e, f, worker.get(i).getVertexs().get(e).getNextmsgs().get(f), superStep);
							worker.get(f%n).getVertexs().get(f).lastmsgs.add(m);
							worker.get(f%n).getVertexs().get(f).setState("active");
						}
						worker.get(i).getVertexs().get(e).getNextmsgs().clear();
					}
				}
				if(nochange) {
					if(nnn==2) {
						for(int i=0;i<worker.size();i++) {
							for(Integer e:worker.get(i).getVertexs().keySet()) {
								if(worker.get(i).getVertexs().get(e).getValue()!=Double.MAX_VALUE) {
									 ShortestPathVertex shortestPathVertex=(ShortestPathVertex) worker.get(i).getVertexs().get(e);
							    System.out.print(shortestPathVertex.getValue()+":"+e);
							    out.write(shortestPathVertex.getValue()+":"+e);
							    while(shortestPathVertex.getPrevVertex()!=-1) {
							    	System.out.print("->"+shortestPathVertex.getPrevVertex());
							    	out.write("->"+shortestPathVertex.getPrevVertex());
							    	shortestPathVertex=(ShortestPathVertex) worker.get(shortestPathVertex.getPrevVertex()%n).getVertexs().get(shortestPathVertex.getPrevVertex());
							    }
							    System.out.print("\n");
							    out.write("\n");
								}
							   
							}
						}
					}
					break;
				}
			superStep++;	
			}
		}
		out.close();
		input.close();
	}
}
