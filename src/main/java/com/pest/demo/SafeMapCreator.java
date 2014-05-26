package task2;

public class SafeMapCreator extends MapGeneratorCreator {

    @Override
    public MapGenerator createMap(int a) {
        MapGenerator sMap = SafeMap.getMapInstance(a);
        return sMap;
    }
}
