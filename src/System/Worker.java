package System;
import java.util.HashMap;

public class Worker {
	public String id;//worker节点的id
	//public ArrayList<Vertex> vertexs;
	public HashMap<Integer,Vertex> vertexs;
	public int superStep;
//	public int activeVertexNum;//当前worker中活跃的顶点数量
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
   public HashMap<Integer, Vertex> getVertexs() {
		return vertexs;
	}
	public void setVertexs(HashMap<Integer, Vertex> vertexs) {
		this.vertexs = vertexs;
	}
	//	public ArrayList<Vertex> getVertexs() {
//		return vertexs;
//	}
//	public void setVertexs(ArrayList<Vertex> vertexs) {
//		this.vertexs = vertexs;
//	}
	public int getSuperStep() {
		return superStep;
	}
	public void setSuperStep(int superStep) {
		this.superStep = superStep;
	}
	
//@Override
//	public String toString() {
//		String string="";
//		for(int i=0;i<vertexs.size();i++) {
//			string="\n"+string+vertexs.get(i).getVertexID()+"\t"+vertexs.get(i).getValue()+"\t"+vertexs.get(i).getState();
//		}
//		return string;
//	}
	//	public int getActiveVertexNum() {
//		return activeVertexNum;
//	}
//	public void setActiveVertexNum(int activeVertexNum) {
//		this.activeVertexNum = activeVertexNum;
//	}
	//在每个超级轮中对worker中的每个顶点执行compute()
	//返回该worker在一个超级轮结束后active的结点的个数
	public int runcompute() {
		//compute
//			for(int i=0;i<vertexs.size();i++) {
		for(Integer i : vertexs.keySet()) {
				//如果当前结点活跃，则执行compute,否则不变
				if(vertexs.get(i).state.equals("active")) {
					vertexs.get(i).setTotleVertexNum(vertexs.size());
					vertexs.get(i).setSuperstep(superStep);
					vertexs.get(i).compute();
					vertexs.get(i).clearLastMessageQueue();//消息compute完之后，清空消息队列
				}
			}
	    //判断执行完成后当前活跃的顶点数
		int activeVertexNum=0;
		for(Integer i : vertexs.keySet()) {
			if(vertexs.get(i).state.equals("active")) {
				activeVertexNum++;
//			     for(int j=0;j<vertexs.size();j++) {
//				     //如果存在从顶点i到顶点j的边，则把消息加到顶点j的接收消息列表里
//				     if(vertexs.get(i).getNextmsgs().containsKey(vertexs.get(j).getVertexID())) {
//					     Message aMsg = new Message(vertexs.get(i).getVertexID(), vertexs.get(j).getVertexID(),
//					    		 vertexs.get(i).getNextmsgs().get(vertexs.get(j).getVertexID()), this.getSuperStep());
//					    vertexs.get(j).queueLastMessage(aMsg);
//				      }
//			      }
			}
//			vertexs.get(i).getNextmsgs().clear();//消息传递完之后，清空消息map
		}
		
		return activeVertexNum;
	}
}
