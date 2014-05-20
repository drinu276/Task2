package task2;

public class HazardousMapCreator extends MapGeneratorCreator {
	
	public MapGenerator createMap (int a){
		System.out.println("Hazardous MAP CREATOR");
		MapGenerator hMap = HazardousMap.getMapInstance(a);
		System.out.println("Hazardous MAP CREATOR OUT");
		return hMap;
	}
}
