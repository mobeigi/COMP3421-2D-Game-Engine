package ass1;

/**
 * MyCoolGameObject - Pikachu!
 *
 * !!!!READ ME!!!!
 * Use with white background for camera: camera.setBackground(new float[]{1,1,1,1});
 * Add line in TestMyCoolGameObject before createTestObjects();
 */

public class MyCoolGameObject extends GameObject {
  
	public MyCoolGameObject() {
		super(GameObject.ROOT);
    
    //Colours
    double[] pikachuYellow = {255/255f, 230/255f, 45/255f, 1};
    double[] black = {0,0,0,1};
    double[] white = {1,1,1,1};
    double[] pikachuRed = {233/255f, 41/255f, 41/255f, 1};
    double[] pikachuBrown = {92/255f, 54/255f, 19/255f, 1};
    
    //Create main Pikachu head/body base, also includes feet
    PolygonalGameObject headBody = new PolygonalGameObject(this,
      new double[]{6, -1.5, 5, -2.5, 6, -1.5, 6.5, -1, 7, 0, 7, 1, 6.5, 3, 6, 4, 5, 5.5, 4.5, 7, 3, 8.5, 1, 9, -1, 9,
                   -3, 8.5, -5, 8, -6, 7, -7.5, 5.5, -8, 3, -8.5, 1, -8.5, -1, -8, -2, -7.5, -2.5, -6, -3, -7.5, -2.5,
                   -8, -5, -8.5, -8, -8.5, -10, -7.5, -12, -7, -13, -6, -14, -5, -14.5, -4.5, -14.5, -3.5, -14.5, -4.5, -14.5,
                   -5.5, -16, -4.5, -15.5, -4.5, -16, -4, -15.5, -4, -16.5, -2.5, -14.5, -0.5, -13.5, 3, -14.5,
                   5, -17, 4.5, -15.5, 5.5, -16, 5, -15.5, 6, -15.5, 4.5, -14, 4, -14, 4.5, -14, 5, -14, 6, -13.5, 6.5, -13,
                   7, -12, 7.5, -10, 7.5, -7.5, 7.5, -5, 7, -3},
      pikachuYellow,
      black);
    
    
    //Eyes and Pupils
    CircularGameObject leftEye = new CircularGameObject(headBody, 1.25, black, null);
    leftEye.setPosition(-5, 3.5);
    
    CircularGameObject rightEye = new CircularGameObject(headBody, 1.25, black, null);
    rightEye.setPosition(3.5, 3.5);
    
    CircularGameObject leftEyePupil = new CircularGameObject(rightEye, 0.5, white, null);
    CircularGameObject rightEyePupil = new CircularGameObject(leftEye, 0.5, white, null);
    
    //Nose
    PolygonalGameObject nose = new PolygonalGameObject(headBody,
      new double[]{-0.5, 1, 0.5, 1.5, -1.5, 1.5},
      black,
      null);
    
    //Polkadots on cheeks
    CircularGameObject leftCheekPolkaDot = new CircularGameObject(headBody, 1.25, pikachuRed, null);
    leftCheekPolkaDot.setPosition(-6.5, -0.5);
    CircularGameObject rightCheekPolkaDot = new CircularGameObject(headBody, 1.25, pikachuRed, null);
    rightCheekPolkaDot.setPosition(4.5, 0);
    
    //Mouth (as a series of lines)
    GameObject mouth = new GameObject(headBody);
    LineGameObject mouth1 = new LineGameObject(mouth, -3, -0.5, -2, -1, black);
    LineGameObject mouth2 = new LineGameObject(mouth, -2, -1, -1, -0.5, black);
    LineGameObject mouth3 = new LineGameObject(mouth, -1, -0.5, -0.5, -0.5, black);
    LineGameObject mouth4 = new LineGameObject(mouth, -0.5, -0.5, 1, -1, black);
    LineGameObject mouth5 = new LineGameObject(mouth, 1, -1, 2, -0.5, black);
    
    //Arms
    GameObject leftArm = new GameObject(headBody);
    LineGameObject leftArmLine1 = new LineGameObject(leftArm, -5.5, -4.5, -3, -9, black);
    LineGameObject leftArmLine2 = new LineGameObject(leftArm, -3, -9, -3, -10, black);
    LineGameObject leftArmLine3 = new LineGameObject(leftArm, -3, -10, -3.5, -9.5, black);
    LineGameObject leftArmLine4 = new LineGameObject(leftArm, -3.5, -9.5, -3.5, -10.5, black);
    LineGameObject leftArmLine5 = new LineGameObject(leftArm, -3.5, -10.5, -4, -10, black);
    LineGameObject leftArmLine6 = new LineGameObject(leftArm, -4, -10, -4, -11, black);
    LineGameObject leftArmLine7 = new LineGameObject(leftArm, -4, -11, -7, -8, black);
    
    GameObject rightArm = new GameObject(headBody);
    LineGameObject rightArmLine1 = new LineGameObject(rightArm, 3.5, -5, 2.5, -9.5, black);
    LineGameObject rightArmLine2 = new LineGameObject(rightArm, 2.5, -9.5, 3, -9, black);
    LineGameObject rightArmLine3 = new LineGameObject(rightArm, 3, -9, 3, -10, black);
    LineGameObject rightArmLine4 = new LineGameObject(rightArm, 3, -10, 4, -9, black);
    LineGameObject rightArmLine5 = new LineGameObject(rightArm, 4, -9, 4, -10, black);
    LineGameObject rightArmLine6 = new LineGameObject(rightArm, 4, -10, 6.5, -6, black);
    
    //Ears
    GameObject leftEar = new GameObject(headBody);
    PolygonalGameObject leftEarBone = new PolygonalGameObject(leftEar,
      new double[]{-7.5, 5.5, -10, 8, -11.5, 10, -13.5, 13, -11, 12, -7, 10, -5, 8},
      pikachuYellow,
      black);
    
    PolygonalGameObject leftEarTip = new PolygonalGameObject(leftEar,
      new double[]{-10, 8, -11.5, 10, -13.5, 13, -11, 12},
      black,
      null);
    
    GameObject rightEar = new GameObject(headBody);
    PolygonalGameObject rightEarBone = new PolygonalGameObject(rightEar,
      new double[]{3, 8.5, 7, 9.5, 11, 10, 13, 9.5, 8, 6.5, 5, 5.5},
      pikachuYellow,
      black);
    
    PolygonalGameObject rightEarTip = new PolygonalGameObject(rightEar,
      new double[]{8, 6.5, 11, 10, 13, 9.5},
      black,
      null);
    
    //Tail
    GameObject tail = new GameObject(headBody);
    
    PolygonalGameObject tailBack = new PolygonalGameObject(tail,
      new double[]{7, -12, 7.5, -10, 7.5, -7.5, 9, -7, 7.5, -5, 9.5, -3.5, 7.5, -1, 9, 2,
                   14, 8, 14, 0.5, 11.5, -1.5, 13, -3.5, 9.5, -5.5, 10.5, -7.5, 9, -9.5},
      pikachuYellow,
      black);
    PolygonalGameObject tailCover = new PolygonalGameObject(tail,
      new double[]{7, -12, 7.5, -10, 7.5, -7.5, 9, -7, 9, -6.5, 10.5, -7.5, 9, -9.5},
      pikachuBrown,
      black);
    
		//Scale to fit default camera
		this.setScale(0.03);
		this.setPosition(0, 0.2);
	}

}