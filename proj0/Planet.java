public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	String imgFileName;
	static final double G = 6.67e-11;
	
	public Planet(double xP, double yP, double xV, double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
	
	public Planet(Planet p) {
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}
	
	public double calcDistance(Planet p) {
		double distance = Math.sqrt((xxPos - p.xxPos) * (xxPos - p.xxPos) + (yyPos - p.yyPos) * (yyPos - p.yyPos));
		return distance;
	}
	
	public double calcForceExertedBy(Planet p) {
		double force = (G * mass * p.mass) / (calcDistance(p) * calcDistance(p));
		return force;
	}
	
	public double calcForceExertedByX(Planet p) {
		double xForce = calcForceExertedBy(p) * (p.xxPos - xxPos) / calcDistance(p);
		return xForce;
	}
	
	public double calcForceExertedByY(Planet p) {
		double yForce = calcForceExertedBy(p) * (p.yyPos - yyPos) / calcDistance(p);
		return yForce;
	}
	
	public double calcNetForceExertedByX(Planet[] allPlanets) {
		double netXForce = 0;
		for (int i = 0; i < allPlanets.length; i++) {
			if (equals(allPlanets[i])) {
				continue;
			}
			
			netXForce = netXForce + calcForceExertedByX(allPlanets[i]);
		}
		return netXForce;
	}
	
	public double calcNetForceExertedByY(Planet[] allPlanets) {
		double netYForce = 0;
		for (int i = 0; i < allPlanets.length; i++) {
			if (equals(allPlanets[i])) {
				continue;
			}
			
			netYForce = netYForce + calcForceExertedByY(allPlanets[i]);
		}
		return netYForce;
	}
	
	public void update(double dt, double fX, double fY) {
		xxVel = (xxVel + dt * fX / mass) ;
		yyVel = (yyVel + dt * fY / mass) ;
		xxPos = xxPos + xxVel * dt;
		yyPos = yyPos + yyVel * dt;
	}
	
	public void draw() {
		String imagePath = "images/" + imgFileName;
		StdDraw.picture(xxPos, yyPos, imagePath);
	}
} 