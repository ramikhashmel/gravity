package dal.gravity;

import java.text.NumberFormat;

/** 
 * compares the values of a simple pendulum using the harmonic motion equation
 * versus the Euler algorithm approximation
 */
public class PendulumRunner {

    public static void main (String [] args) {
	NumberFormat nf = NumberFormat.getInstance ();
	nf.setMaximumFractionDigits (3);
	
	GravityConstant EarthGravity = new GravityConstant(9.80665);
	GravityConstant Jupiter = new GravityConstant(25);

	double delta = (args.length == 0) ? .1 : Double.parseDouble (args[0]);
	double sLen = 10, pMass = 10, theta0 = Math.PI/30;
	RegularPendulum rp = new RegularPendulum (sLen, pMass, theta0, delta, EarthGravity);
	SimplePendulum sp = new SimplePendulum (sLen, pMass, theta0, EarthGravity);
	RegularPendulum rpCoarse = 
	    new RegularPendulum (sLen, pMass, theta0, .1, EarthGravity);

	// print out difference in displacement in 1 second intervals
	// for 20 seconds
	int iterations = (int) (1/delta);
	System.out.println ("analytical vs. numerical displacement (fine, coarse)");
	System.out.println("Earth: ");
	for (int second = 1; second <= 10; second++) {
	    for (int i = 0; i < iterations; i++) rp.step ();
	    for (int i = 0; i < 10; i++) rpCoarse.step (); 
	    System.out.println ("t=" + second + "s: \t" + 
				nf.format (Math.toDegrees (sp.getTheta (second))) 
				+ "\t" +
				nf.format (Math.toDegrees (rp.getLastTheta ()))
				+ "\t" + 
				nf.format (Math.toDegrees (rpCoarse.getLastTheta ())));
	}
	rp.setGravityModel(Jupiter);
	sp.setGravityModel(Jupiter);
	rpCoarse.setGravityModel(Jupiter);
	System.out.println("Jupiter: ");
	for (int second = 1; second <= 10; second++) {
	    for (int i = 0; i < iterations; i++) rp.step ();
	    for (int i = 0; i < 10; i++) rpCoarse.step (); 
	    System.out.println ("t=" + second + "s: \t" + 
				nf.format (Math.toDegrees (sp.getTheta (second))) 
				+ "\t" +
				nf.format (Math.toDegrees (rp.getLastTheta ()))
				+ "\t" + 
				nf.format (Math.toDegrees (rpCoarse.getLastTheta ())));
	}
	
    }
}

