package task2;

public class MapGeneratorCreator {

    public MapGenerator createMap(int size, int type) {

        MapGeneratorCreator map = typeSet(type);
        if (map != null) {
            return map.createMap(size);
        } else {
            return null;
        }
    }

    public MapGenerator createMap(int a) {
        return null;
    }

    public MapGeneratorCreator typeSet(int type) {
        if (type == 1) {
            return new SafeMapCreator();
        } else if (type == 2) {
            return new HazardousMapCreator(); 
        } else {

            return null;
        }
    }
}
