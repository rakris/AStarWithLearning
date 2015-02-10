import java.util.HashMap;
import java.util.Map;


public class City {
	private String name;
	private Map<City, Integer> neighbours = new HashMap<City, Integer>();
	
	public City(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void addNeighbour(City city, int cost) {
		neighbours.put(city, cost);
	}
	
	public Map<City, Integer> getNeighbours() {
		return neighbours;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof City) {
			City city = (City) obj;
			if(city.name.equals(this.name)) 
				return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.name.hashCode();
	}
	
	@Override
	public String toString() {
		return "City : "+this.name;
	}
}
