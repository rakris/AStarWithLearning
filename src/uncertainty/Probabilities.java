package uncertainty;


public class Probabilities {

	public static final int happy = 0;
	public static final int notHappy = 1;
	public static final int pitesti = 0;
	public static final int notPitesti = 1;
	public static final int traffic = 0;
	public static final int notTraffic = 1;
	
	private static double[][][] probability = new double[2][][];
		
	static {
		probability[happy] = new double[2][];
		probability[notHappy] = new double[2][];
		
		probability[happy][pitesti] = new double[2];
		probability[happy][notPitesti] = new double[2];
		probability[notHappy][pitesti] = new double[2];
		probability[notHappy][notPitesti] = new double[2];
		
		probability[happy][pitesti][traffic] = 0.108;
		probability[happy][pitesti][notTraffic] = 0.012;
		probability[happy][notPitesti][traffic] = 0.072;
		probability[happy][notPitesti][notTraffic] = 0.008;
		probability[notHappy][pitesti][traffic] = 0.016;
		probability[notHappy][pitesti][notTraffic] = 0.064;
		probability[notHappy][notPitesti][traffic] = 0.144;
		probability[notHappy][notPitesti][notTraffic] = 0.576;
	}
	
	public static double getHappyProbability(int condition) {
		
		if(condition == pitesti) {
			double happyAndPitesti = probability[happy][pitesti][traffic] + 
					probability[happy][pitesti][notTraffic];
			double cityPitestiProb = probability[happy][pitesti][traffic] + 
					probability[happy][pitesti][notTraffic] + 
					probability[notHappy][pitesti][traffic] +
					probability[notHappy][pitesti][notTraffic];
			
			return happyAndPitesti / cityPitestiProb;
		}
		else if (condition == notPitesti) {
			double happyAndCraivoa = probability[happy][notPitesti][traffic] +
					probability[happy][notPitesti][notTraffic];
			double cityCraiovaProb = probability[happy][notPitesti][traffic] +
					probability[happy][notPitesti][notTraffic] + 
					probability[notHappy][notPitesti][traffic] +
					probability[notHappy][notPitesti][notTraffic];
					
			return happyAndCraivoa / cityCraiovaProb;
		}
		
		return 0;
	}

}
