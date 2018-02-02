import java.io.File;

public class NBody {
	public static double readRadius(String planetsTxtPath) {
		File file = new File(planetsTxtPath);
		In in = new In(file);
		int planetsNumbers = in.readInt();
		double radius = in.readDouble();
		return radius;
	}
	
	public static Planet[] readPlanets(String planetsTxtPath) {
		File file = new File(planetsTxtPath);
		In in = new In(file);
		int planetsNumbers = in.readInt();
		double radius = in.readDouble();
		Planet[] allPlanets = new Planet[planetsNumbers];
		for (int i = 0; i < planetsNumbers; i++) {
			allPlanets[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
		}
		return allPlanets;
	}
}