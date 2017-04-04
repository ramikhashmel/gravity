package dal.gravity;

/**
 * Represents a pendulum
 */
public class RegularPendulum extends AbstractPendulum {
    private double delta, iterations = 0;
    private double dissipation;
    private double lastTheta, lastVel, lastAccel;
    public static final double GRAVITY = 9.80665;

    /**
     * Creates a new Pendulum instance 
     */
    public RegularPendulum (double inLength, double inMass, double inTheta0, 
		     double inDelta, double inDiss, GravityModel gm) {
	super (inLength, inMass, inTheta0, gm);
	delta=inDelta;
	dissipation = inDiss;
	lastVel = 0;
	lastTheta = this.getMaxAngularDisplacement ();
	lastAccel = -(this.getGravitationalField () / this.getStringLength ())*Math.sin (lastTheta);
    }

    public RegularPendulum (double inLength, double inMass, double inTheta0, 
		     double inDelta, GravityModel gm) {
	this (inLength, inMass, inTheta0, inDelta, 0, gm);
    }

    public void step () {
	
	lastAccel = - dissipation*lastVel - this.getGravitationalField () / this.getStringLength () * Math.sin (lastTheta);
    }

    public double getLastTheta () { return lastTheta; }
    public double getLastVelocity () { return lastVel; }
    public double getLastAcceleration () { return lastAccel; }
    public double getLastTime () { return iterations*delta; }
    public double getDissipationConstant () { return dissipation; }
    
    @Override
    public void setGravityModel(GravityModel gm){
    	super.setGravityModel(gm);
    	lastTheta = this.getMaxAngularDisplacement();
    	lastAccel = -(this.getGravitationalField () / this.getStringLength ())*Math.sin (lastTheta);
    }
    

}
