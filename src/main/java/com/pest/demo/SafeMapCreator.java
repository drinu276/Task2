package task2;

public class SafeMapCreator extends MapGeneratorCreator {
	
	public MapGenerator createMap (int a){
		System.out.println("SAFE MAP CREATOR");
		MapGenerator sMap = SafeMap.getMapInstance(a);
		System.out.println("SAFE MAP CREATOR OUT");
		return sMap;
	}
}
