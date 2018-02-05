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
		String filename = args[2];
		Planet[] allPlanets = NBody.readPlanets(filename);
		double radius = NBody.readRadius(filename);
		StdDraw.setScale(-radius, radius);
		StdDraw.enableDoubleBuffering();
		double time = 0.0;
		double[] xForces = new double[allPlanets.length];
		double[] yForces = new double[allPlanets.length];
		while (time < T) {
			for (int i = 0; i < allPlanets.length; i++) {
				xForces[i] = allPlanets[i].calcNetForceExertedByX(allPlanets);
				yForces[i] = allPlanets[i].calcNetForceExertedByX(allPlanets);		
			}
			
			for (int i = 0; i < allPlanets.length; i++) {
				allPlanets[i].update(dt, xForces[i], yForces[i]);
			}
			
			StdDraw.picture(0, 0, "images/starfield.jpg");
			
			for (int i = 0; i < allPlanets.length; i++) {
				allPlanets[i].draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
			time = time + dt;
		}
		StdOut.printf("%d\n", allPlanets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < allPlanets.length; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n", allPlanets[i].xxPos, allPlanets[i].yyPos, allPlanets[i].xxVel, allPlanets[i].yyVel, allPlanets[i].mass, allPlanets[i].imgFileName);   
        }
	}
}