package learning;

import java.util.HashMap;
import java.util.Map;

public class RouteLearningSampleData {
	
	private static String currentEnvironment = "Rainy=Yes,Mood=Happy,Traffic=No,Vehicle=Bike";
	private static String trainingSample = "bin/TrainingSample";
	
	Map<String, String> parsedEnvironment = new HashMap<String, String>();
	
	public RouteLearningSampleData() {
		parseCurrentEnvironment();
	}
	
	private void parseCurrentEnvironment() {
		String[] attrs = currentEnvironment.split(",");
		for(String attr : attrs) {
			String[] values = attr.split("=");
			parsedEnvironment.put(values[0], values[1]);
		}
	}

	public boolean isTheRouteSafeByLearning() {
		
		DecisionTree tree = new DecisionTree();
		
		DecisionNode root = tree.getDecisionTreeRoot(trainingSample);
		
		while(!root.isLeafNode) {
			String value = parsedEnvironment.get(root.type.name);
			if(value.equals(root.type.trueString)) {
				root = root.trueNode;
			}
			else
				root = root.falseNode;
		}
		
		return root.goalState;
	}
}
