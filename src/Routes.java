import java.util.HashMap;
import java.util.Map;



public class Routes {
	
	
	
	static City Oradea = new City("Oradea");
	static City Zerind = new City("Zerind");
	static City Arad = new City("Arad");
	static City Sibiu = new City("Sibiu");
	static City Timisoara = new City("Timisoara");
	static City Lugoj = new City("Lugoj");
	static City Mehadia = new City("Mehadia");
	static City Drobeta = new City("Drobeta");
	static City Craiova = new City("Craiova");
	static City RimnicuVilcea = new City("Rimnicu Vilcea");
	static City Fagarus = new City("Fagarus");
	static City Pitesti = new City("Pitesti");
	static City Bucharest = new City("Bucharest");
	static City Giurgiu = new City("Giurgiu");
	static City Urziceni = new City("Urziceni");
	static City Neamt = new City("Neamt");
	static City Iasi = new City("Iasi");
	static City Vaslui = new City("Vaslui");
	static City Hirsova = new City("Hirsova");
	static City Eforie = new City("Eforia");
	
	static Map<City, Integer> heuristics = new HashMap<City, Integer>();
	
	static {

		constructRoute();
		
		constructHeuristics();
	}

	public static City getStartingCity() {
		return Arad;
	}
	
	public static int getHeuristic(City city) {
		return heuristics.get(city);
	}
	
	public static City[] getUncertainRoutes() {
		City[] cities = new City[2];
		cities[0] = Routes.RimnicuVilcea;
		cities[1] = Routes.Pitesti;
		return cities;
	}
	
	public static City[] getLearnableRoutes() {
		City[] cities = new City[2];
		cities[0] = Routes.Sibiu;
		cities[1] = Routes.Fagarus;
		return cities;
	}

	private static void constructHeuristics() {
		heuristics.put(Arad, 366 );
		heuristics.put(Bucharest , 0 );
		heuristics.put(Craiova, 160 );
		heuristics.put(Drobeta, 242 );
		heuristics.put(Eforie, 161 );
		heuristics.put(Fagarus, 176 );
		heuristics.put(Giurgiu, 77 );
		heuristics.put(Hirsova, 151 );
		heuristics.put(Iasi, 226 );
		heuristics.put(Lugoj, 244 );
		heuristics.put(Mehadia, 241 );
		heuristics.put(Neamt, 234 );
		heuristics.put(Oradea, 380 );
		heuristics.put(Pitesti, 100 );
		heuristics.put(RimnicuVilcea, 193 );
		heuristics.put(Sibiu, 253 );
		heuristics.put(Timisoara, 329);
		heuristics.put(Urziceni, 80);
		heuristics.put(Vaslui, 199);
		heuristics.put(Zerind, 374);
	}

	private static void constructRoute() {
		Oradea.addNeighbour(Zerind, 71);
		Oradea.addNeighbour(Sibiu, 151);
		
		Zerind.addNeighbour(Oradea, 71);
		Zerind.addNeighbour(Arad, 75);
		
		Arad.addNeighbour(Zerind, 75);
		Arad.addNeighbour(Sibiu, 140);
		Arad.addNeighbour(Timisoara, 118);
		
		Timisoara.addNeighbour(Arad, 118);
		Timisoara.addNeighbour(Lugoj, 111);
		
		Lugoj.addNeighbour(Timisoara, 111);
		Lugoj.addNeighbour(Mehadia, 70);
		
		Mehadia.addNeighbour(Lugoj, 70);
		Mehadia.addNeighbour(Drobeta, 75);
		
		Drobeta.addNeighbour(Mehadia, 75);
		Drobeta.addNeighbour(Craiova, 120);
		
		Craiova.addNeighbour(Drobeta, 120);
		Craiova.addNeighbour(RimnicuVilcea, 146);
		Craiova.addNeighbour(Pitesti, 138);
		
		RimnicuVilcea.addNeighbour(Craiova, 146);
		RimnicuVilcea.addNeighbour(Sibiu, 80);
		RimnicuVilcea.addNeighbour(Pitesti, 97);
		
		Sibiu.addNeighbour(RimnicuVilcea, 80);
		Sibiu.addNeighbour(Oradea, 151);
		Sibiu.addNeighbour(Arad, 140);
		Sibiu.addNeighbour(Fagarus, 99);
		
		Fagarus.addNeighbour(Sibiu, 99);
		Fagarus.addNeighbour(Bucharest, 211);
		
		Pitesti.addNeighbour(RimnicuVilcea, 97);
		Pitesti.addNeighbour(Craiova, 138);
		Pitesti.addNeighbour(Bucharest, 101);
		
		Bucharest.addNeighbour(Fagarus, 211);
		Bucharest.addNeighbour(Pitesti, 101);
		Bucharest.addNeighbour(Giurgiu, 90);
		Bucharest.addNeighbour(Urziceni, 85);
		
		Urziceni.addNeighbour(Bucharest, 85);
		Urziceni.addNeighbour(Hirsova, 98);
		Urziceni.addNeighbour(Vaslui, 142);
		
		Hirsova.addNeighbour(Urziceni, 98);
		Hirsova.addNeighbour(Eforie, 86);
		
		Vaslui.addNeighbour(Urziceni, 142);
		Vaslui.addNeighbour(Iasi, 92);
		
		Iasi.addNeighbour(Vaslui, 92);
		Iasi.addNeighbour(Neamt, 87);
		
		Neamt.addNeighbour(Iasi, 87);
		
		Giurgiu.addNeighbour(Bucharest, 90);
		
		Eforie.addNeighbour(Hirsova, 86);
	}
}