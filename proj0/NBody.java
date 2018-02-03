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
	
	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = new String();
		filename = args[2];
		Planet[] allPlanets = NBody.readPlanets(filename);
		double radius = NBody.readRadius(filename);
		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0, 0, "images/starfield.jpg");
		for (int i  = 0; i < allPlanets.length; i++) {
			allPlanets[i].draw();
		}
	}
	
	
}