package ass1;

import com.jogamp.opengl.GL2;

/**
 * A game object that has a polygonal shape.
 *
 * This class extend ass1.GameObject to draw polygonal shapes.
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
   * @return get polygon points
   */
  public double[] getPoints() {
    return myPoints;
  }
  
  /**
   * Set the polygon
   *
   * @param points polygon points to set
   */
  public void setPoints(double[] points) {
    myPoints = points;
  }
  
  /**
   * Get the fill colour
   *
   * @return fill colour vector
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
   * @return line colour vector
   */
  public double[] getLineColour() {
    return myLineColour;
  }
  
  /**
   * Set the outline colour.
   *
   * Setting the colour to null means the outline should not be drawn
   *
   * @param lineColour line colour to set
   */
  public void setLineColour(double[] lineColour) {
    myLineColour = lineColour;
  }
  
  // ===========================================
  // COMPLETE THE METHODS BELOW
  // ===========================================
  
  
  /**
   * Draw the polygon
   *
   * if the fill colour is non-null, fill the polygon with this colour
   * if the line colour is non-null, draw the outline with this colour
   *
   * @see ass1.GameObject#drawSelf(javax.media.opengl.GL2)
   */
  @Override
  public void drawSelf(GL2 gl) {
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
  
  /**
   * Collision detection for PolygonalGameObject
   *
   * @param point point in world coordinates
   * @return true if point is inside of polygonal game object
   */
  @Override
  public boolean collision(double[] point) {
    //Convert point from world coordinate system to local coordinate system
    double completePoint[] = {point[0], point[1], 1}; //add missing 1
    double localPoint[] = MathUtil.multiply(computeInverseModelViewMatrix(), completePoint);

    //Compute total number of vertices (note length of points should be even)
    int nvert = getPoints().length / 2;
    
    //Store each x,y coordinate pair in two arrays
    double[] vertx = new double[nvert];
    double[] verty = new double[nvert];
    double[] points = getPoints();
    
    for (int i = 0, numx = 0, numy = 0; i+1 < points.length; i+=2) {
      vertx[numx++] = points[i];
      verty[numy++] = points[i+1];
    }
    
    //Use helper function to complete test using local coordinates for our test point
    return pnpoly(nvert, vertx, verty, localPoint[0], localPoint[1]);
  }
  
  /**
   * Returns true if test point is inside of polygon.
   * Functions by testing number of edges crossed by scaling x axis infinitely. An odd number of edges crossed
   * indicates a collision has occurred (point within polygon).
   *
   * Limitation: Does not correctly classify all points exactly on boundary/edge. This functionality was not added
   * for the purposes of this assignment. To help, the inequalities were all changes to include comparison (=)
   * in the function below.
   *
   * Method by W. Randolph Franklin. See link below for code source.
   *
   * @param nvert total number of vertices in polygon
   * @param vertx list of all x coordinates for polygons vertices
   * @param verty list of all y coordinates for polygons vertices
   * @param testx the x coordinate of point being tested
   * @param testy the y coordinate of point being tested
   * @see <a href="https://www.ecse.rpi.edu/Homepages/wrf/Research/Short_Notes/pnpoly.html">PNPOLY - Point Inclusion in Polygon Test</a>
   * @return true if test point (testx, tesy) collide with polygon
   */
  public boolean pnpoly(int nvert, double[] vertx, double[] verty, double testx, double testy)
  {
    boolean c = false;
    int i, j = 0;
    for (i = 0, j = nvert-1; i < nvert; j = i++) {
      if ( ((verty[i]>=testy) != (verty[j]>=testy)) &&
        (testx <= (vertx[j]-vertx[i]) * (testy-verty[i]) / (verty[j]-verty[i]) + vertx[i]) )
        c = !c;
    }
    
    return c;
  }
}
