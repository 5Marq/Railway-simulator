import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class Route {
    private String source;
    private String destination;
    private double distance;

    public String getDestination() {
        return destination;
    }

    public String getSource() {
        return source;
    }

    public double getDistance() {
        return distance;
    }

    public static double getStopsDistance(String start, String end) {
        for (Route r : routes) {
            if (start.equals(r.source) && end.equals(r.destination)) {
                return r.getDistance();
            }
        }
        return 0;
    }

    public static HashMap<String, ArrayList<String>> connections;

    public static ArrayList<Route> getRoutes() { //save all routes to arraylist
        ArrayList<Route> routes = new ArrayList<>();
        for (String key : connections.keySet()) { //scan through all stations
            for (String val : connections.get(key)) {
                Route existing = null;
                for (Route r : routes) { //check if exists
                    if (r.getSource().equals(val) && r.getDestination().equals(key)) {
                        existing = r;
                    }
                }
                if (existing == null) {
                    routes.add(new Route(key, val, ThreadLocalRandom.current().nextDouble(10, 101))); //adding new route backwards with random distance if not exising
                } else {
                    routes.add(new Route(key, val, existing.getDistance()));
                }
            }
        }
        return routes;
    }

    public static ArrayList<Route> routes;

    public static HashMap<String, ArrayList<String>> getConnections(ArrayList<String> stations) {
        HashMap<String, ArrayList<String>> connections = new HashMap<>();

        for (String s : stations) {
            connections.put(s, new ArrayList<>());

            int num = ThreadLocalRandom.current().nextInt(1, 4);

            for (int i = 0; i < num; i++) {
                int mi = ThreadLocalRandom.current().nextInt(0, stations.size());

                String prop = stations.get(mi);

                while (prop.equals(s) || connections.get(s).contains(prop)) {
                    mi = ThreadLocalRandom.current().nextInt(0, stations.size());
                    prop = stations.get(mi);
                }

                connections.get(s).add(prop);
            }
        }

        for (String k : connections.keySet()) {
            for (String p : connections.get(k)) {
                if (!connections.get(p).contains(k)) {
                    connections.get(p).add(k);
                }
            }
        }
        return connections;
    }

    public static String findWay(HashMap<String, ArrayList<String>> connections, String start, String end, String route) {
        route += start + ",";

        if (start.equals(end)) {
            return route;
        } else {
            if (connections.get(start).contains(end)) {
                start = end;
                return findWay(connections, start, end, route);
            } else {
                int pi = ThreadLocalRandom.current().nextInt(0, connections.get(start).size());
                start = connections.get(start).get(pi);
                return findWay(connections, start, end, route);
            }
        }
    }

    public Route(String source, String destination, double distance) {
        this.destination = destination;
        this.distance = distance;
        this.source = source;
    }

    @Override
    public String toString() {
        return destination + "-" + distance;
    }
}
