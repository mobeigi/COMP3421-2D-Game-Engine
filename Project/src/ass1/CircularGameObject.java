package ass1;

import com.jogamp.opengl.GL2;

/**
 * A game object that has a circular shape.
 *
 * This class extend ass1.GameObject to draw circular shapes.
 *
 */
public class CircularGameObject extends GameObject {
  private double radius;
  private double[] centre;
  private double[] myFillColour;
  private double[] myLineColour;
  
  //Create a CircularGameObject with centre 0,0 and radius 1
  public CircularGameObject(GameObject parent, double[] fillColour,
                                             double[] lineColour) {
    super(parent);
    this.radius = 1.0;
    centre = new double[]{0,0};
    myFillColour = fillColour;
    myLineColour = lineColour;
  }
  
  //Create a CircularGameObject with centre 0,0 and a given radius
  public CircularGameObject(GameObject parent, double radius,double[] fillColour,
                                                           double[] lineColour) {
    super(parent);
    this.radius = radius;
    centre = new double[]{0,0};
    myFillColour = fillColour;
    myLineColour = lineColour;
    
  }
  
  /**
   * Get the radius.
   *
   * @return radius
   */
  public double getRadius() {
    return radius;
  }
  
  /**
   * Set the radius
   */
  public void setRadius(double radius) {
    this.radius = radius;
  }
  
  /**
   * Get the centre.
   *
   * @return centre coordinates
   */
  public double[] getCentre() {
    return centre;
  }
  
  /**
   * Set the centre.
   *
   */
  public void setCentre(double[] centre) {
    this.centre = centre;
  }
  
  /**
   * Get the fill colour
   *
   * @return
   */
  public double[] getFillColour() {
    return myFillColour;
  }
  
  /**
   * Set the fill colour.
   *
   * Setting the colour to null means the object should not be filled.
   *
   * @param fillColour The fill colour in [r, g, b, a] form
   */
  public void setFillColour(double[] fillColour) {
    myFillColour = fillColour;
  }
  
  /**
   * Get the outline colour.
   *
   * @return
   */
  public double[] getLineColour() {
    return myLineColour;
  }
  
  /**
   * Set the outline colour.
   *
   * Setting the colour to null means the outline should not be drawn
   *
   * @param lineColour
   */
  public void setLineColour(double[] lineColour) {
    myLineColour = lineColour;
  }
  
  // ===========================================
  // COMPLETE THE METHODS BELOW
  // ===========================================
  
  
  /**
   * TODO: Draw the Circle
   *
   * if the fill colour is non-null, fill the circle with this colour
   * if the line colour is non-null, draw the circle with this colour
   *
   * @see ass1.GameObject#drawSelf(javax.media.opengl.GL2)
   */
  @Override
  public void drawSelf(GL2 gl) {
    // TODO: Write this method
    
    double radius = getRadius();
    double[] centre = getCentre();
    double[] fillColour = getFillColour();
    double[] lineColour = getLineColour();
    
    if (fillColour != null) {
      gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_FILL);
      gl.glColor4d(fillColour[0], fillColour[1], fillColour[2], fillColour[3]);
      drawCircle(gl);
    }
    
    if (lineColour != null) {
      gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_LINE);
      gl.glColor4d(lineColour[0], lineColour[1], lineColour[2], lineColour[3]);
      drawCircle(gl);
      gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_FILL); //bug fix
    }
    
  }
  
  /**
   * Helper function to draw Circle
   * @param gl GL2 object
   */
  private void drawCircle(GL2 gl) {
    //Draw circle
    gl.glBegin(GL2.GL_POLYGON);
    
    double[] centre = getCentre();
    double angle = 0;
    double angleIncrement = 2*Math.PI/32; //Using 32 sides
    for(int i=0; i <= 32; i++){
      angle = i * angleIncrement;
      double x = radius * Math.cos(angle);
      double y = radius * Math.sin(angle);
      gl.glVertex2d(x + centre[0], y + centre[1]);  //offset x and y by centre
    }
    gl.glEnd();
  }
}
