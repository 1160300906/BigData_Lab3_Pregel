package System;

public class Message {
	private int sourceVertexID;
	private int destVertexID;	
	private double msgValue;
	private int superStep;
	
	/**
	 * 
	 * @param sourceVertexID Vertex ID of the source vertex generating this message
	 * @param destVertexID Vertex ID of the destination vertex consuming this message
	 * @param msgValue Value contained in the message
	 * @param superStep Superstep number
	 */
	public Message(int sourceVertexID, int destVertexID, double msgValue, int superStep){
	
		this.sourceVertexID=sourceVertexID;
		this.destVertexID=destVertexID;		
		this.msgValue=msgValue;
		this.superStep=superStep;
	}
	/**
	 *
	 * @return  Returns the source vertex ID sending the message
	 */
	public int getSourceVertexID() {
		return sourceVertexID;
	}
	
	
	/**
	 * 
	 * @return  Returns the destination vertex ID receiving the message
	 */
	public int getDestVertexID() {
		return destVertexID;
	}
	
	
	
	/**
	 * 
	 * @return Returns the value contained in the message
	 */
	public double getMessageValue() {
		return msgValue;
	}
	/**
	 * 
	 * @return Returns the superstep number during which the message was generated
	 */
	public int getSuperStep(){
		return this.superStep;
	}
		
	/**
	 * Returns a string representation of the message
	 */
	@Override
	public String toString(){
		StringBuffer strBuf=new StringBuffer();
		strBuf.append("\n\nSource Vertex ID : "+getSourceVertexID()+"\n");
		strBuf.append("Destination Vertex ID : "+getDestVertexID()+"\n");
		strBuf.append("Message Value : "+getMessageValue()+"\n");
		strBuf.append("Source Superstep : "+getSuperStep()+"\n\n");
		return strBuf.toString();
		
	}
}
