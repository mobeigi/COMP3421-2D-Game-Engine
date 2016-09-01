package ass1;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.glu.GLU;

/**
 * The camera is a ass1.GameObject that can be moved, rotated and scaled like any other.
 * 
 * TODO: You need to implement the setView() method.
 *       The methods you need to complete are at the bottom of the class
 *
 * @author malcolmr
 */
public class Camera extends GameObject {

    private float[] myBackground;

    public Camera(GameObject parent) {
        super(parent);

        myBackground = new float[4];
    }

    public Camera() {
        this(GameObject.ROOT);
    }
    
    public float[] getBackground() {
        return myBackground;
    }

    public void setBackground(float[] background) {
        myBackground = background;
    }

    // ===========================================
    // COMPLETE THE METHODS BELOW
    // ===========================================
   
    
    public void setView(GL2 gl) {
      // TODO 1. clear the view to the background colour
      float[] background = getBackground();
      gl.glClearColor(background[0], background[1], background[2], background[3]);
      gl.glClear(GL.GL_COLOR_BUFFER_BIT);
      
      // TODO 2. set the view matrix to account for the camera's position
      gl.glMatrixMode(GL2.GL_MODELVIEW);
      gl.glLoadIdentity();
      
      double[] globalPosition = getGlobalPosition();
      gl.glScaled(1.0f/getGlobalScale(), 1.0f/getGlobalScale(), 1);
      gl.glRotated(-getGlobalRotation(), 0, 0, 1);
      gl.glTranslated(-globalPosition[0], -globalPosition[1], 0);
    }

    public void reshape(GL2 gl, int x, int y, int width, int height) {
        
        // match the projection aspect ratio to the viewport
        // to avoid stretching
        
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();

        double top, bottom, left, right;
        
        if (width > height) {
            double aspect = (1.0 * width) / height;
            top = 1.0;
            bottom = -1.0;
            left = -aspect;
            right = aspect;            
        }
        else {
            double aspect = (1.0 * height) / width;
            top = aspect;
            bottom = -aspect;
            left = -1;
            right = 1;                        
        }        
        GLU myGLU = new GLU();
        // coordinate system (left, right, bottom, top)
        myGLU.gluOrtho2D(left, right, bottom, top);                
    }
}
