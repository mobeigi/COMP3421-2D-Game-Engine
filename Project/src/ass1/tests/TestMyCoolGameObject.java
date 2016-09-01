package ass1.tests;


import javax.swing.JFrame;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.util.FPSAnimator;

import ass1.*;

/**
 * A simple class to view MyCoolGameObject
 */
public class TestMyCoolGameObject {

	public static void createTestObjects(){
		
		//Should look good when we create using the default constructor
		MyCoolGameObject cgo = new MyCoolGameObject();
		
		// Should not break if we apply transformations for example
		// If we uncommented these lines (or wrote other transformations) 
		// it should not break your object
		/**
		cgo.translate(-0.2,0.2);
		cgo.rotate(45);
		cgo.scale(0.25);
		**/
	}
   
    /**
     * A simple test for MyCoolGameObject
     * 
     * @param args
     */
    public static void main(String[] args) {
        // Initialise OpenGL
        GLProfile glprofile = GLProfile.getDefault();
        GLCapabilities glcapabilities = new GLCapabilities(glprofile);
        
        // create a GLJPanel to draw on
        GLJPanel panel = new GLJPanel(glcapabilities);

        // Create a camera
        Camera camera = new Camera(GameObject.ROOT);
        camera.setBackground(new float[]{1,1,1,1}); //Added manually for a nice background for MyCoolGameObject
        createTestObjects();
        
        // Add the game engine
        GameEngine engine = new GameEngine(camera);
        panel.addGLEventListener(engine);

        // Add an animator to call 'display' at 60fps        
        FPSAnimator animator = new FPSAnimator(60);
        animator.add(panel);
        animator.start();

        // Put it in a window
        JFrame jFrame = new JFrame("Test Shapes");
        jFrame.add(panel);
        jFrame.setSize(600, 600);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
