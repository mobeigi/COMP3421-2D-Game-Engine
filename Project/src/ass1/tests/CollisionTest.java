package ass1.tests;

import ass1.*;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.util.FPSAnimator;
import org.junit.Test;
import junit.framework.TestCase;

import javax.swing.*;


/**
 * Class to test collision detection in LineGameObjects, CircularGameObject, and PolygonalGameObject
 *
 */
public class CollisionTest extends TestCase
{
  private static double white[] = {1,1,1,1};
  
  @Test
  public void testLineCollision0() {
    //Line from (0,0) to (1,1)
    //Point (0,0) lies on this line
    LineGameObject lgo = new LineGameObject(GameObject.ROOT, 0, 0, 1, 1, white);
    double point[] = {0, 0};
    
    assertTrue(lgo.collision(point));
  }
  
  @Test
  public void testLineCollision1() {
    //Line from (-1,-1) to (1,1)
    //Point (0,0) lies on this line
    LineGameObject lgo = new LineGameObject(GameObject.ROOT, -1, -1, 1, 1, white);
    double point[] = {0, 0};
    
    assertTrue(lgo.collision(point));
  }
  
  @Test
  public void testLineCollision2() {
    //Line from (0,0) to (5,0), rotated 45% (ie line through origin)
    //Point (3,3) lies on this line
    LineGameObject lgo = new LineGameObject(GameObject.ROOT, 0, 0, 5, 0, white);
    lgo.setRotation(45);
    double point[] = {3, 3};
    
    assertTrue(lgo.collision(point));
  }
  
  @Test
  public void testLineCollision3() {
    //Line from (2,2) to (4,0)
    //Point (1,3) does not lie on the line (even though it would on an extrapolated line)
    LineGameObject lgo = new LineGameObject(GameObject.ROOT, 2, 2, 4, 0, white);
    double point[] = {1, 3};
    
    assertFalse(lgo.collision(point));
  }
  
  @Test
  public void testLineCollision4() {
    //Line from (2,2) to (4,0)
    //Point (1,3) does lie on the line after scaling
    LineGameObject lgo = new LineGameObject(GameObject.ROOT, 2, 2, 4, 0, white);
    lgo.setScale(2.0);
    double point[] = {1, 3};
    
    assertFalse(lgo.collision(point));
  }
  
  @Test
  public void testLineCollision5() {
    //Line from (2,2) to (4,0)
    //Various points lie on the line after scaling (as line moves + becomes larger)
    LineGameObject lgo = new LineGameObject(GameObject.ROOT, 2, 2, 4, 0, white);
    lgo.setScale(2.0);
    double point1[] = {4, 4};
    double point2[] = {5, 3};
    double point3[] = {6, 2};
    double point4[] = {7, 1};
    double point5[] = {8, 0};
    
    assertTrue(lgo.collision(point1));
    assertTrue(lgo.collision(point2));
    assertTrue(lgo.collision(point3));
    assertTrue(lgo.collision(point4));
    assertTrue(lgo.collision(point5));
  }
  
  @Test
  public void testCircularCollision0() {
    //Circle at centre (0,0)
    //Point (0.5, 0) is within circle
    CircularGameObject cgo = new CircularGameObject(GameObject.ROOT, 1, white, white);
    double point[] = {0.5, 0};
  
    assertTrue(cgo.collision(point));
  }
  
  @Test
  public void testCircularCollision1() {
    //Circle at centre (0,0)
    //Point (0, 1) is on outline of circle (thus colliding)
    CircularGameObject cgo = new CircularGameObject(GameObject.ROOT, 1, white, white);
    double point[] = {0, 1};
    
    assertTrue(cgo.collision(point));
  }
  
  @Test
  public void testCircularCollision2() {
    //Circle at centre (0,0)
    //Point (0, 1.1) is not colliding
    CircularGameObject cgo = new CircularGameObject(GameObject.ROOT, 1, white, white);
    double point[] = {0, 1.1};
    
    assertFalse(cgo.collision(point));
  }
  
  @Test
  public void testCircularCollision3() {
    //Circle at centre (0,0), them moved to centre (5,5)
    //Point (5, 5) originally not colliding, then collides after position change
    CircularGameObject cgo = new CircularGameObject(GameObject.ROOT, 1, white, white);
    double point[] = {5, 5};
    assertFalse(cgo.collision(point));
    cgo.setPosition(5, 5);
    assertTrue(cgo.collision(point));
  }
  
  @Test
  public void testPolygonalCollision0()
  {
    //Point (2,2) does not lie within polygon
    double points[] = {0,0, 1,0, 1,1, 0,1};
    PolygonalGameObject obj = new PolygonalGameObject(GameObject.ROOT, points, null, white);
    double point[] = {2, 2};
    
    assertFalse(obj.collision(point));
  }
  
  @Test
  public void testPolygonalCollision1()
  {
    //Point (-1,0) does not lie within polygon
    double points[] = {0,0, 1,0, 1,1, 0,1};
    PolygonalGameObject obj = new PolygonalGameObject(GameObject.ROOT, points, null, white);
    double point[] = {-1, 0};
    
    assertFalse(obj.collision(point));
  }
  
  @Test
  public void testPolygonalCollision2()
  {
    //Point (0.5,0.5) does lie within polygon
    double points[] = {0,0, 1,0, 1,1, 0,1};
    PolygonalGameObject obj = new PolygonalGameObject(GameObject.ROOT, points, null, white);
    double point[] = {0.5, 0.5};
    
    assertTrue(obj.collision(point));
  }
  
  @Test
  public void testPolygonalCollision3()
  {
    //Point (0,0.5) does lie within polygon on edge
    double points[] = {0,0, 1,0, 1,1, 0,1};
    PolygonalGameObject obj = new PolygonalGameObject(GameObject.ROOT, points, null, white);
    double point[] = {0, 0.5};
    
    assertTrue(obj.collision(point));
  }
  
  @Test
  public void testPolygonalCollision4()
  {
    //Point (1,0.5) does lie within polygon on edge
    double points[] = {0,0, 1,0, 1,1, 0,1};
    PolygonalGameObject obj = new PolygonalGameObject(GameObject.ROOT, points, null, white);
    double point[] = {1, 0.5};
    
    assertTrue(obj.collision(point));
  }
}