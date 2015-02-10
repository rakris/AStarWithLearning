package learning;

public class DecisionNode {

	public AttributeType type;
	public DecisionNode trueNode;
	public DecisionNode falseNode;
	
	public boolean isLeafNode = false;
	public boolean goalState;
}
