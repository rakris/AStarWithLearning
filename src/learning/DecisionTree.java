package learning;
import java.util.ArrayList;
import java.util.List;


public class DecisionTree {
	
	private List<AttributeType> mTypes = new ArrayList<AttributeType>();
	private List<List<Attribute>> mRows = new ArrayList<List<Attribute>>();

	
	
	public void parseFile(String sampleFile) {
		List<String> lines = Utility.readFile(sampleFile);
		String firstLine = lines.get(0);
		String[] attrs = firstLine.split("\\|");
		for (int i = 0; i < attrs.length; i++) {
			String attr = attrs[i].trim();
			AttributeType attrType = new AttributeType();
			attrType.name = attr.split(":")[0];
			attrType.trueString = attr.split(":")[1].substring(1);
			attrType.falseString = attr.split(":")[2].substring(0, attr.split(":")[2].length()-1);
			mTypes.add(attrType);
		}
		
		for(int i = 1; i < lines.size(); i++) {
			String line = lines.get(i).trim();
			if(line.isEmpty())
				continue;
			String[] rowString = line.split("\\|");
			
			List<Attribute> row = new ArrayList<Attribute>();
			for (int j = 0; j < rowString.length; j++) {
				Attribute rowAttribute = new Attribute();
				rowAttribute.setType(mTypes.get(j));
				rowAttribute.setValue(rowString[j].trim());
				
				row.add(rowAttribute);
			}
			this.mRows.add(row);
		}
	}
	
	
	private List<Attribute> getColumn(List<List<Attribute>> rows, int index) {
		List<Attribute> cols = new ArrayList<Attribute>();
		for(List<Attribute> row : rows) {
			Attribute attribute = row.get(index);
			cols.add(attribute);
		}
		return cols;
	}
	
	
	public DecisionNode runDecisionTreeAlgorithm(List<AttributeType> types, List<List<Attribute>> rows ) {
		
		List<Attribute> goalColumn = getColumn(rows, types.size()-1);
		double goalEntropy = Utility.goalEntropy(goalColumn);
		if(goalEntropy <= 0) 
			return null;
		
		AttributeType rootType = getNextMostImportantType(types, rows);

		List<AttributeType> newTypes = getFilteredTypes(types, rootType);
		List<List<Attribute>> trueRows = getFilteredRows(rows, rootType, true);
		List<List<Attribute>> falseRows = getFilteredRows(rows, rootType, false);
		
		
		DecisionNode root = new DecisionNode();
		
		root.type = rootType;
		
		
		DecisionNode trueNode = runDecisionTreeAlgorithm(newTypes, trueRows);
		DecisionNode falseNode = runDecisionTreeAlgorithm(newTypes, falseRows);
		
		if(trueNode == null) 
			trueNode = constructLeafNode(trueRows);
		if(falseNode == null) 
			falseNode = constructLeafNode(falseRows);
		
		root.trueNode = trueNode;
		root.falseNode = falseNode;
		
		return root;
	}
	

	private AttributeType getNextMostImportantType(List<AttributeType> types, List<List<Attribute>> rows) {
		List<Attribute> goalColumn = getColumn(rows, types.size()-1);
		
		double maxInfGain = 0;
		
		AttributeType rootType = null;
	
		for (int i = 0; i < types.size()-1; i++) {
			AttributeType type = types.get(i);
			int typeIndex = i;
			
			List<Attribute> attrColumn = getColumn(rows, typeIndex);
			
			double infGain = Utility.informationGain(attrColumn, goalColumn);
			if(maxInfGain == 0 || infGain > maxInfGain) {
				maxInfGain = infGain;
				rootType = type;
			}
		}
		return rootType;
	}
	
	
	
	private List<List<Attribute>> getFilteredRows(List<List<Attribute>> rows,
			AttributeType rootType, boolean value) {
		List<List<Attribute>> newRows = new ArrayList<List<Attribute>>();
		int typeIndex= -1;
		for(int i = 0; i< rows.size(); i++) {
			List<Attribute> row = rows.get(i);
			if(typeIndex == -1)
				typeIndex= indexOfAttributeType(rootType, row);
			if(value == row.get(typeIndex).getValue()) {
				List<Attribute> newRow = new ArrayList<Attribute>();
				for(Attribute item : row) {
					if(!item.getType().equals(rootType)) {
						newRow.add(item);
					}
				}
				newRows.add(newRow);
			}
		}
		return newRows;
	}
	
	private int indexOfAttributeType(AttributeType type, List<Attribute> row) {
		for (int i = 0; i < row.size(); i++) {
			if(type.equals(row.get(i).getType()))
				return i;
		}
		return 0;
	}
	
	private List<AttributeType> getFilteredTypes(List<AttributeType> types, AttributeType rootType) {
		List<AttributeType> newTypes = new ArrayList<AttributeType>();
		for(AttributeType type : types) {
			if(!type.name.equals(rootType.name)) {
				newTypes.add(type);
			}
		}
		return newTypes;
	}
	
	
	private boolean isPositiveResult(List<List<Attribute>> rows) {
		for(List<Attribute> row : rows) {
			int index = row.size()-1;
			if(!row.get(index).getValue()) {
				return false;
			}
		}
		return true;
	}
	
	private DecisionNode constructLeafNode(List<List<Attribute>> rows) {
		DecisionNode node = new DecisionNode();
		node.isLeafNode = true;
		
		node.goalState = isPositiveResult(rows);
		return node;
	}
	

	public DecisionNode getDecisionTreeRoot(String sampleFile) {
		parseFile(sampleFile);
		return runDecisionTreeAlgorithm(mTypes, mRows);
	}
	
	
}
