package learning;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Utility {

	private static double B(double num) {
		if(Double.isInfinite(num))
			return 1;
		return -1 * (num * log2(num) + (1-num) * log2(1-num));
	}
	
	private static double log2(double x) {
		if(x == 0) return 1;
		if(x == 1) return 0;
		return Math.log(x)/Math.log(2);
	}
	
	public static double informationGain(List<Attribute> column, List<Attribute> goalColumn) {
		
		double entropy = goalEntropy(goalColumn);
		double total = column.size();
		double positive = 0;
		double negative = 0;
		double positiveTrue = 0;
		double negativeTrue = 0;
		
		for (int i = 0; i < column.size(); i++) {
			Attribute attribute = column.get(i);
			Attribute goalAttribute = goalColumn.get(i);
			if(attribute.getValue()) {
				positive++;
				if(goalAttribute.getValue())
					positiveTrue++;
			}
			else {
				negative++;
				if(goalAttribute.getValue())
					negativeTrue++;
			}
		}
		
		double ig = entropy - ((positive/total)*B(positiveTrue/positive) 
				+ (negative/total)*B(negativeTrue/negative));
		
	//	System.out.println("Information gain = "+ig);
		
		return ig;
	}
	
	public static List<String> readFile(String sampleFile) {
		List<String> lines = new ArrayList<String>();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(
					sampleFile));
			String line = null;


			while ((line = reader.readLine()) != null) {
				lines.add(line);
			}
			reader.close();
		} catch (Exception e) {

		}
		return lines;
	}
	public static double goalEntropy(List<Attribute> rootColumn) {
		double positive = 0;
		double negative = 0;
		for(Attribute item : rootColumn ) {
			if(item.getValue()) {
				positive++;
			}
			else
				negative++;
		}
		return B(positive/(double)(positive+negative));
	}
}
