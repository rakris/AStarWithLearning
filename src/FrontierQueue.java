import java.util.HashSet;
import java.util.Set;

public class FrontierQueue  {

	private Set<Node> queue = new HashSet<Node>();
	
	private Node next;
	
	public void add(Node node) {
		queue.add(node);
		
		findNext();
	}

	private void findNext() {
		Node tempNext = null;
		for(Node node : queue) {
			if(tempNext == null) {
				tempNext = node;
				continue;
			}
			if(tempNext.getEvalCost() > node.getEvalCost()) {
				tempNext = node;
			}
		}
		this.next = tempNext;
	}
	
	public Node peek() {
		return this.next;
	}
	
	public Node remove() {
		Node tempNext = this.next;
		
		queue.remove(this.next);
		
		findNext();
		
		return tempNext;
		
	}

	public boolean isEmpty() {
		return queue.size() == 0;
	}
	
}
