package ass1;

import com.jogamp.opengl.GL2;

/**
 * A game object that has a polygonal shape.
 *
 * This class extend ass1.GameObject to draw polygonal shapes.
 *
 * TODO: The methods you need to complete are at the bottom of the class
 *
 * @author malcolmr
 */
public class PolygonalGameObject extends GameObject {
  
  private double[] myPoints;
  private double[] myFillColour;
  private double[] myLineColour;
  
  /**
   * Create a polygonal game object and add it to the scene tree
   *
   * The polygon is specified as a list of doubles in the form:
   *
   * [ x0, y0, x1, y1, x2, y2, ... ]
   *
   * The line and fill colours can possibly be null, in which case that part of the object
   * should not be drawn.
   *
   * @param parent The parent in the scene tree
   * @param points A list of points defining the polygon
   * @param fillColour The fill colour in [r, g, b, a] form
   * @param lineColour The outline colour in [r, g, b, a] form
   */
  public PolygonalGameObject(GameObject parent, double points[],
                             double[] fillColour, double[] lineColour) {
    super(parent);
    
    myPoints = points;
    myFillColour = fillColour;
    myLineColour = lineColour;
  }
  
  /**
   * Get the polygon
   *
   * @return
   */
  public double[] getPoints() {
    return myPoints;
  }
  
  /**
   * Set the polygon
   *
   * @param points
   */
  public void setPoints(double[] points) {
    myPoints = points;
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
   * TODO: Draw the polygon
   *
   * if the fill colour is non-null, fill the polygon with this colour
   * if the line colour is non-null, draw the outline with this colour
   *
   * @see ass1.GameObject#drawSelf(javax.media.opengl.GL2)
   */
  @Override
  public void drawSelf(GL2 gl) {
    // TODO: Write this method
    double[] fillColour = getFillColour();
    double[] lineColour = getLineColour();
    
    if (fillColour != null) {
      gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_FILL);
      gl.glColor4d(fillColour[0], fillColour[1], fillColour[2], fillColour[3]);
      drawPolygon(gl);
    }
    
    if (lineColour != null) {
      gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_LINE);
      gl.glColor4d(lineColour[0], lineColour[1], lineColour[2], lineColour[3]);
      drawPolygon(gl);
      gl.glPolygonMode(GL2.GL_FRONT_AND_BACK, GL2.GL_FILL); //bug fix
    }
  }
  
  /**
   * Helper function to draw Polygon
   * @param gl GL2 object
   */
  private void drawPolygon(GL2 gl) {
    double[] points = getPoints();
    
    gl.glBegin(GL2.GL_POLYGON);
    //Each pair of points represent (x,y) coord
    for (int i = 0; i+1 < points.length; i+=2) {
      gl.glVertex2d(points[i], points[i+1]);
    }
    gl.glEnd();
  }
}
