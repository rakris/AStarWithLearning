
public class Node {

	private City city;
	private Node parent;
	
	private int pathCost;
	
	private int evalCost;
	
	public City getCity() {
		return city;
	}
	public void setCity(City city) {
		this.city = city;
	}
	public Node getParent() {
		return parent;
	}
	public void setParent(Node parent) {
		this.parent = parent;
	}
	public int getEvalCost() {
		return evalCost;
	}
	public void setEvalCost(int pathCost) {
		this.evalCost = pathCost;
	}
	public int getPathCost() {
		return pathCost;
	}
	public void setPathCost(int pathCost) {
		this.pathCost = pathCost;
	}
	
	@Override
	public String toString() {
		return "Node : "+this.city.getName()+", EvalCost : "+this.evalCost;
	}
	
	
}
