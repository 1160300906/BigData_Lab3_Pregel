package System;
import java.util.HashMap;

public class Worker {
	public String id;//worker�ڵ��id
	//public ArrayList<Vertex> vertexs;
	public HashMap<Integer,Vertex> vertexs;
	public int superStep;
//	public int activeVertexNum;//��ǰworker�л�Ծ�Ķ�������
	
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
	//��ÿ���������ж�worker�е�ÿ������ִ��compute()
	//���ظ�worker��һ�������ֽ�����active�Ľ��ĸ���
	public int runcompute() {
		//compute
//			for(int i=0;i<vertexs.size();i++) {
		for(Integer i : vertexs.keySet()) {
				//�����ǰ����Ծ����ִ��compute,���򲻱�
				if(vertexs.get(i).state.equals("active")) {
					vertexs.get(i).setTotleVertexNum(vertexs.size());
					vertexs.get(i).setSuperstep(superStep);
					vertexs.get(i).compute();
					vertexs.get(i).clearLastMessageQueue();//��Ϣcompute��֮�������Ϣ����
				}
			}
	    //�ж�ִ����ɺ�ǰ��Ծ�Ķ�����
		int activeVertexNum=0;
		for(Integer i : vertexs.keySet()) {
			if(vertexs.get(i).state.equals("active")) {
				activeVertexNum++;
//			     for(int j=0;j<vertexs.size();j++) {
//				     //������ڴӶ���i������j�ıߣ������Ϣ�ӵ�����j�Ľ�����Ϣ�б���
//				     if(vertexs.get(i).getNextmsgs().containsKey(vertexs.get(j).getVertexID())) {
//					     Message aMsg = new Message(vertexs.get(i).getVertexID(), vertexs.get(j).getVertexID(),
//					    		 vertexs.get(i).getNextmsgs().get(vertexs.get(j).getVertexID()), this.getSuperStep());
//					    vertexs.get(j).queueLastMessage(aMsg);
//				      }
//			      }
			}
//			vertexs.get(i).getNextmsgs().clear();//��Ϣ������֮�������Ϣmap
		}
		
		return activeVertexNum;
	}
}
