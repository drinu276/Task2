package task2;

public class HazardousMapCreator extends MapGeneratorCreator {

    @Override
    public MapGenerator createMap(int a) {
        MapGenerator hMap = HazardousMap.getMapInstance(a);
        return hMap;
    }
}
