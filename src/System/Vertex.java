package System;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Vertex {
	public int vertexID;
	public ArrayList<Message> lastmsgs=new ArrayList<Message>();//上一轮接收的消息
	public HashMap<Integer,Double> nextmsgs=new HashMap<Integer,Double>();//当前轮要发送给下一个顶点的消息
	public ArrayList<Integer> outgoingEdges=new ArrayList<Integer>(); //出边的集合
	public double value;
//	public double msgvalue;//要传递给下一个顶点的消息的值
	public String state;//当前顶点的状态,初始化为active,当不需要执行进一步计算则设置为unactive
	public int totleVertexNum;
	public int superstep;
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getVertexID() {
		return vertexID;
	}
	public void setVertexID(int vertexID) {
		this.vertexID = vertexID;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
//	public double getMsgvalue() {
//		return msgvalue;
//	}
//	public void setMsgvalue(double msgvalue) {
//		this.msgvalue = msgvalue;
//	}
	public ArrayList<Message> getLastmsgs() {
		return lastmsgs;
	}
	public void setLastmsgs(ArrayList<Message> lastmsgs) {
		this.lastmsgs = lastmsgs;
	}
    public int getTotleVertexNum() {
		return totleVertexNum;
	}
	public void setTotleVertexNum(int totleVertexNum) {
		this.totleVertexNum = totleVertexNum;
	}
	public int getSuperstep() {
		return superstep;
	}
	public void setSuperstep(int superstep) {
		this.superstep = superstep;
	}
	public HashMap<Integer, Double> getNextmsgs() {
		return nextmsgs;
	}
	public void setNextmsgs(HashMap<Integer, Double> nextmsgs) {
		this.nextmsgs = nextmsgs;
	}
	//	public ArrayList<Message> getNextmsgs() {
//		return nextmsgs;
//	}
//	public void setNextmsgs(ArrayList<Message> nextmsgs) {
//		this.nextmsgs = nextmsgs;
//	}	
	public ArrayList<Integer> getOutgoingEdges() {
		return outgoingEdges;
	}
	public void setOutgoingEdges(ArrayList<Integer> outgoingEdges) {
		this.outgoingEdges = outgoingEdges;
	}
	
	//将在图分析算法中重写
	public abstract void compute();
	//在当前轮的消息队列中添加消息
	public void queueLastMessage(Message msg) {
		this.lastmsgs.add(msg);

	}
//	//在每次进行compute()之后，要清空当前消息队列
//	public void initCompute() {
//		compute();
//		this.nextmsgs.clear();
//	}
	//清空当前消息队列
//	public void clearNextMessageQueue() {
//		this.nextmsgs.clear();
//
//	}
	//清空接收到的上一轮消息队列
		public void clearLastMessageQueue() {
			this.lastmsgs.clear();

		}
}
