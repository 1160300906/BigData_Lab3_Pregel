package System;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Vertex {
	public int vertexID;
	public ArrayList<Message> lastmsgs=new ArrayList<Message>();//��һ�ֽ��յ���Ϣ
	public HashMap<Integer,Double> nextmsgs=new HashMap<Integer,Double>();//��ǰ��Ҫ���͸���һ���������Ϣ
	public ArrayList<Integer> outgoingEdges=new ArrayList<Integer>(); //���ߵļ���
	public double value;
//	public double msgvalue;//Ҫ���ݸ���һ���������Ϣ��ֵ
	public String state;//��ǰ�����״̬,��ʼ��Ϊactive,������Ҫִ�н�һ������������Ϊunactive
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
	
	//����ͼ�����㷨����д
	public abstract void compute();
	//�ڵ�ǰ�ֵ���Ϣ�����������Ϣ
	public void queueLastMessage(Message msg) {
		this.lastmsgs.add(msg);

	}
//	//��ÿ�ν���compute()֮��Ҫ��յ�ǰ��Ϣ����
//	public void initCompute() {
//		compute();
//		this.nextmsgs.clear();
//	}
	//��յ�ǰ��Ϣ����
//	public void clearNextMessageQueue() {
//		this.nextmsgs.clear();
//
//	}
	//��ս��յ�����һ����Ϣ����
		public void clearLastMessageQueue() {
			this.lastmsgs.clear();

		}
}
