package ass1;

import com.jogamp.opengl.GL2;

/**
 * A game object that has a circular shape.
 *
 * This class extend ass1.GameObject to draw circular shapes.
 *
 */
public class LineGameObject extends GameObject {
  
  private double point1[];
  private double point2[];
  private double[] myLineColour;
  
  //Create a LineGameObject from (0,0) to (1,0)
  public LineGameObject(GameObject parent, double[] lineColour) {
    super(parent);
    
    this.point1 = new double[]{0,0};
    this.point2 = new double[]{1,0};
    myLineColour = lineColour;
  }
  
  //Create a LineGameObject from (x1,y1) to (x2,y2)
  public LineGameObject(GameObject parent,  double x1, double y1,
                        double x2, double y2,
                        double[] lineColour) {
    super(parent);
    this.point1 = new double[]{x1, y1};
    this.point2 = new double[]{x2, y2};
    myLineColour = lineColour;
  }
  
  //Point getters and setters
  public double[] getPoint1() {
    return point1;
  }
  
  public void setPoint1(double[] point1) {
    this.point1 = point1;
  }
  
  public double[] getPoint2() {
    return point2;
  }
  
  public void setPoint2(double[] point2) {
    this.point2 = point2;
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
   * if the line colour is non-null, draw the line with this colour
   *
   * @see ass1.GameObject#drawSelf(javax.media.opengl.GL2)
   */
  @Override
  public void drawSelf(GL2 gl) {
    // TODO: Write this method
    double[] lineColour = getLineColour();
    
    if (lineColour != null) {
      gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_LINE);
      gl.glColor4d(lineColour[0], lineColour[1], lineColour[2], lineColour[3]);
      drawLine(gl);
      gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_FILL); //bug fix
    }
    
  }
  
  /**
   * Helper function to draw Line
   * @param gl GL2 object
   */
  private void drawLine(GL2 gl) {
    //Draw Line
    gl.glBegin(GL2.GL_LINES);
      double[] point1 = getPoint1();
      double[] point2 = getPoint2();
    
      gl.glVertex2d(point1[0], point1[1]);
      gl.glVertex2d(point2[0], point2[1]);
    gl.glEnd();
  }
}
