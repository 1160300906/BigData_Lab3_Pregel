package System;


public class PageRankVertex extends Vertex{

	@Override
	public void compute() {
		if(this.getSuperstep()>=1 ){
			double sum=0;
			for(Message m : this.getLastmsgs()){
				sum+=m.getMessageValue();
			}
			double newPageRank=0.15/this.getTotleVertexNum()+0.85*sum;
			if(Math.abs(newPageRank-this.getValue())<1 || this.getSuperstep()>5) {
				this.setState("unactive");//改变小于0.1,则把顶点设为unactive,并且不传递消息
			}else {
					this.setValue(newPageRank);
			        this.setState("active");
			        int numEdges=this.getOutgoingEdges().size();
			       for(int i=0;i<this.getOutgoingEdges().size();i++){
				       this.getNextmsgs().put(this.getOutgoingEdges().get(i), this.getValue()/numEdges);
			       }
			}
		
		}
	}

}
