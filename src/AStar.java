import java.util.Map;
import java.util.Set;
import java.util.Stack;

import learning.RouteLearningSampleData;

import uncertainty.Probabilities;


public class AStar {
	
	
	private static final double ROUTE_DESIRABILITY = 0.7;
	private boolean enableUncertainty = false;
	private boolean enableLearning = false;
	
	private RouteLearningSampleData learningSampleData = new RouteLearningSampleData();
	
	private City end;
	
	private FrontierQueue frontier = new FrontierQueue();
	
	
	public static void main(String[] args) {
		
		AStar astar = new AStar();
		astar.runAStar(Routes.Arad, Routes.Bucharest);
	}
	
	public void runAStar(City start, City end) {
		
		this.end = end;
		
		Node startNode = createNode(start, null, 0);
		if(goalTest(startNode)) {
			printRoute(startNode);
			return;
		}
		
		frontier.add(startNode);
		
		while(!frontier.isEmpty()) {
			Node node = frontier.remove();
			
			if(goalTest(node)) {
				printRoute(node);
				return;
			}
			
			System.out.println("A* : EXPANDING \""+node.getCity().getName()+ "\" chosen from frontier\n");
			
			City parentCity = node.getCity();
						
			Map<City, Integer> neighbours = parentCity.getNeighbours();
			
			Set<City> neighbouringCities = neighbours.keySet();
			
			printNeighboursToBeAdded(neighbouringCities);
			
			for(City neighbour : neighbouringCities) {
				
				if(enableUncertainty && !isTheRouteCertainlySafe(parentCity, neighbour)) {
					System.out.println("A* : SKIPPING \""+parentCity.getName()+"\"--\""+neighbour.getName()
							+"\" because its desirability is " +
							"less than "+ROUTE_DESIRABILITY+"\n");
					continue;
				}
				
				if(enableLearning && !isTheRouteSafeLearning(parentCity, neighbour)) {
					System.out.println("A* : SKIPPING \""+parentCity.getName()+"\"--\""+neighbour.getName()
							+"\" because its avoidable by" +
							"looking at learnt data\n");
					continue;
				}
				
				Node neighbourNode = createNode(neighbour, node, neighbours.get(neighbour));
				
				frontier.add(neighbourNode);
			}
		}

	}
	
	private boolean isTheRouteSafeLearning(City start,
			City dest) {
		City[] learntRoute = Routes.getLearnableRoutes();
		if(start == learntRoute[0] && dest == learntRoute[1]) {
			return learningSampleData.isTheRouteSafeByLearning();
		}
		return true;
	}

	private boolean isTheRouteCertainlySafe(City start, City dest) {
		City[] riskyRoute = Routes.getUncertainRoutes();
		if(start == riskyRoute[0] && dest == riskyRoute[1]) {
			double prob = Probabilities.getHappyProbability(Probabilities.pitesti);
			
			if(prob < ROUTE_DESIRABILITY)
				return false;
		}
		return true;
	}
	
	private void printNeighboursToBeAdded(Set<City> neighbours) {
		if(neighbours.size() == 0)
			return;
		System.out.print("A* : ADDING neighbours ");
		for(City city : neighbours) {
			System.out.print("\""+city.getName()+"\", ");
		}
		System.out.println("to frontier\n");
	}

	private void printRoute(Node node) {
		System.out.println("A* : Reached destination \""+end.getName()+"\" with route as following\n");
		Stack<String> routes = new Stack<String>();
		routes.add(node.getCity().getName());
		while(node.getParent() != null) {
			routes.add(node.getParent().getCity().getName());
			node = node.getParent();
		}
		
		while(!routes.isEmpty()) {
			String city = routes.pop();
			System.out.print(city+"--->");
		}
		System.out.println("(GOAL Reached)");
	}

	private boolean goalTest(Node startNode) {
		if(startNode.getCity().equals(end))
			return true;
		return false;
	}

	private Node createNode(City city, Node parent, int stepCost) {
		Node node = new Node();
		node.setCity(city);
		node.setParent(parent);
		int pathCost = stepCost;
		if(parent != null) {
			pathCost = pathCost + parent.getPathCost();
		}
		node.setPathCost(pathCost);
		node.setEvalCost(pathCost + Routes.getHeuristic(city));	
		return node;
	}
}
