public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	String imgFileName = new String();
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
		double distance = Math.sqrt((this.xxPos - p.xxPos) * (this.xxPos - p.xxPos) + (this.yyPos - p.yyPos) * (this.yyPos - p.yyPos));
		return distance;
	}
	
	public double calcForceExertedBy(Planet p) {
		double force = (G * this.mass * p.mass) / (this.calcDistance(p) * this.calcDistance(p));
		return force;
	}
	
	public double calcForceExertedByX(Planet p) {
		double yForce = this.calcForceExertedBy(p) * (p.xxPos - this.xxPos) / this.calcDistance(p);
		return yForce;
	}
	
	public double calcForceExertedByY(Planet p) {
		double xForce = this.calcForceExertedBy(p) * (p.yyPos - this.yyPos) / this.calcDistance(p);
		return xForce;
	}
	
	public double calcNetForceExertedByX(Planet[] allPlanets) {
		double netXForce = 0;
		for (int i = 0; i < allPlanets.length; i++) {
			if (this.equals(allPlanets[i])) {
				continue;
			}
			
			netXForce = netXForce + this.calcForceExertedByX(allPlanets[i]);
		}
		return netXForce;
	}
	
	public double calcNetForceExertedByY(Planet[] allPlanets) {
		double netYForce = 0;
		for (int i = 0; i < allPlanets.length; i++) {
			if (this.equals(allPlanets[i])) {
				continue;
			}
			
			netYForce = netYForce + this.calcForceExertedByY(allPlanets[i]);
		}
		return netYForce;
	}
	
	public void update(double dt, double fX, double fY) {
		this.xxVel = (this.xxVel + dt * fX / this.mass) ;
		this.yyVel = (this.yyVel + dt * fY / this.mass) ;
		this.xxPos = this.xxPos + this.xxVel * dt;
		this.yyPos = this.yyPos + this.yyVel * dt;
	}
	
	public void draw() {
		String imageName = this.imgFileName;
		String imagePath = "images/" + imageName;
		StdDraw.picture(this.xxPos, this.yyPos, imagePath);
	}
} 