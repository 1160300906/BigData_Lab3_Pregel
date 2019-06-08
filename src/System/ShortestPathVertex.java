package System;

public class ShortestPathVertex extends Vertex{

	Double INFINITY = Double.MAX_VALUE;
	public int prevVertex=-1;

	
	public int getPrevVertex() {
		return prevVertex;
	}

	public void setPrevVertex(int prevVertex) {
		this.prevVertex = prevVertex;
	}

	public ShortestPathVertex() {
		this.prevVertex = -1;
	}

	private boolean isSourceVertex() {
		if (this.getVertexID() == 0) {
			return true;
		}
		return false;
	}
	@Override
	public void compute() {
		// TODO Auto-generated method stub
	//	if(this.getSuperstep()>=1){
			double minDist = isSourceVertex() ? 0 : INFINITY;
		int newPrevVertexID = -1;
		for (Message aMsg : this.getLastmsgs()) {
			if (aMsg.getMessageValue() < minDist) {
				minDist = aMsg.getMessageValue();
				newPrevVertexID = aMsg.getSourceVertexID();
			}
		}

		if (minDist < this.getValue()) {
			this.prevVertex = newPrevVertexID;
			this.setValue(minDist);
			//System.out.println(minDist);
			  for(int i=0;i<this.getOutgoingEdges().size();i++){
			       this.getNextmsgs().put(this.getOutgoingEdges().get(i), minDist + 1);
		       }
			this.setState("unactive");
		}else if(this.getValue()!=INFINITY){
			this.setState("unactive");
		}
		else {
			this.setState("active");
		 }
		}
		
	//}


}
