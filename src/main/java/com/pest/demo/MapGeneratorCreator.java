package task2;

public class MapGeneratorCreator {

	public MapGenerator createMap(int a , int type){
		
		MapGeneratorCreator map = typeSet(type);
		
		if ( map != null){
			System.out.println("YOUR MAP HAS BEEN CREATED!! ");
			return map.createMap(a);
		}
		else {
			return null;
		}
	}
	
	public MapGenerator createMap(int a){
		return null;
	}
	
	public MapGeneratorCreator typeSet ( int type ){
		if (type ==1 ){
			System.out.println("DAHAL SAFE MAP");
			return new SafeMapCreator();
		}
		else if(type==2){
			return new HazardousMapCreator(); 
		}
		else {
			System.out.println("This is not a correct Type");
			return null;
		}

	}
	
	
}
