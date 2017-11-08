/**
 * Class models a vector in three-dimensional space
 * @author James Talbott
 */
public class Vector extends PlanarObject {
  
  /**
   * Field stores the Vector's x-coordinate
   */
  private double xStep;
  
  /**
   * Field stores the Vector's y-coordinate
   */
  private double yStep;
  
  /**
   * Field stores the Vector's z-coordinate
   */
  private double zStep;
  
  /**
   * Constructor initializes a Vector given its three coordinates
   * @param x - the x-coordinate of the new Vector
   * @param y - the y-coordinate of the new Vector
   * @param z - the z-coordinate of the new Vector
   */
  public Vector(double x, double y, double z) {
    xStep = x;
    yStep = y;
    zStep = z;
  }
  
  /**
   * Constructor initializes the position Vector of the input Point
   * @param p - the Point that the new Vector willcopy coordinates from
   */
  public Vector(Point p) {
    this(p.getX(), p.getY(), p.getZ());
  }
  
  /**
   * Constructor initializes a new vector parallel to the input Vector but of length length
   * @param v - the Vector the new Vector will be parallel to
   * @param length - the length of the new Vector
   */
  public Vector(Vector v, double length) {
    this(length * v.getXStep() / v.magnitude(), length * v.getYStep() / v.magnitude(),
         length * v.getZStep() / v.magnitude());
  }
  
  /**
   * Method returns the x-coordinate of the Point
   * @return the value stored in the field xStep
   */
  public double getXStep() {
    return xStep;
  }
  
  /**
   * Method returns the y-coordinate of the Point
   * @return the value stored in the field yStep
   */
  public double getYStep() {
    return yStep;
  }
  
  /**
   * Method returns the z-coordinate of the Point
   * @return the value stored in the field zStep
   */
  public double getZStep() {
    return zStep;
  }
  
  /**
   * Method calculates the length of the calling Vector
   * @return the magnitude of the calling Vector
   */
  public double magnitude() {
    return Point.distanceBetweenPoints(new Point(0, 0, 0), new Point(getXStep(), getYStep(), getZStep()));
  }
  
  /**
   * Method returns a unit vector parallel to the calling Vector
   * @return a new Vector of magnitude 1 parallel to the calling Vector
   */
  public Vector unitVector() {
    return new Vector(getXStep() / magnitude(), getYStep() / magnitude(), getZStep() / magnitude());
  }
  
  /**
   * Method returns a new Vector that is the sum of two input Vectors
   * @param v1 - The first Vector
   * @param v2 - The second Vector
   * @return a new Vector with coordinates that are the sum of the relevant coordinates from the parameters
   */
  public static Vector sum(Vector v1, Vector v2) {
    return new Vector(v1.getXStep() + v2.getXStep(), v1.getYStep() + v2.getYStep(), v1.getZStep() + v2.getZStep());
  }
  
  /**
   * Method returns a new Vector equivalent to the input Vector multiplied by the input scalar
   * @param v - the input Vector
   * @param scalar - the input scalar
   * @return a new Vector equivalent to the input Vector multiplied by the input scalar
   */
  public static Vector scale(Vector v, double scalar) {
    return new Vector(v, v.magnitude() * scalar);
  }
  
  /**
   * Method returns the dot product of the two input Vectors
   * @param v1 - the first Vector
   * @param v2 - the second Vector
   * @return the dot product of the input Vectors
   */
  public static double dotProduct(Vector v1, Vector v2) {
    return v1.getXStep() * v2.getXStep() + v1.getYStep() * v2.getYStep() + v1.getZStep() * v2.getZStep();
  }
  
  /**
   * Method returns the cross product of the two input Vectors
   * @param v1 - the first Vector
   * @param v2 - the second Vector
   * @return a new Vector perpendicular to both input Vectors
   */
  public static final Vector crossProduct(Vector v1, Vector v2) {
    return new Vector(Vector.matrixDet2x2(v1.getYStep(), v2.getYStep(), v1.getZStep(), v2.getZStep()),
                      Vector.matrixDet2x2(v1.getZStep(), v2.getZStep(), v1.getXStep(), v2.getXStep()),
                      Vector.matrixDet2x2(v1.getXStep(), v2.getXStep(), v1.getYStep(), v2.getYStep()));
  }
  
  /**
   * Method calculates the acute angle between two Vectors
   * @param v1 - the first Vector
   * @param v2 - the second Vector
   * @return the acute angle between the two input Vectors
   */
  public static double angle(Vector v1, Vector v2) {
    return Math.acos(Vector.dotProduct(v1, v2) / (v1.magnitude() * v2.magnitude()));
  }
  
  /**
   * Method returns whether two Vectors are orthogonal
   * @param v1 - the first Vector
   * @param v2 - the second Vector
   * @return whether the two input Vectors are perpendicular
   */
  public static boolean isOrthogonal(Vector v1, Vector v2) {
    return Vector.dotProduct(v1, v2) == 0;
  }
  
  /**
   * Method returns whether two Vectors are parallel
   * @param v1 - the first Vector
   * @param v2 - the second Vector
   * @return whether the two input Vectors are parallel
   */
  public static boolean isParallel(Vector v1, Vector v2) {
    double scalar = 0;
    if (v2.getXStep() != 0)
      scalar = v1.getXStep() / v2.getXStep();
    else if (v2.getYStep() != 0)
      scalar = v1.getYStep() / v2.getYStep();
    else if (v2.getZStep() != 0)
      scalar = v1.getZStep() / v2.getZStep();
    else
      return false;
    if (Vector.scale(v2, scalar).equals(v1))
      return true;
    else
      return false;
  }
  
  /**
   * Method returns the determinant of a 2x2 matrix that the inputs fill left to right, top to bottom.
   * @param a - The top-left element of the matrix
   * @param b - The top-right element of the matrix
   * @param c - The lower-left element of the matrix
   * @param d - The lower-right element of the matrix
   * @return the determinant of the defined matrix
   */
  private static final double matrixDet2x2(double a, double b, double c, double d) {
    return a * d - b * c;
  }
  
  /**
   * Method overrides Object's inadequate equals method
   * @param obj - the Object that the calling Vector will be compared to
   * @return whether the input Object and calling Vector are identical
   */
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Vector) {
      Vector v = (Vector) obj;
      return this.getXStep() == v.getXStep() && this.getYStep() == v.getYStep() && this.getZStep() == v.getZStep();
    }
    else
      return false;
  }
  
  /**
   * Method overrides Object's inadequate toString method
   * @return the calling Vector as a vector coordinate triplet
   */
  @Override
  public String toString() {
    return "<" + getXStep() + ", " + getYStep() + ", " + getZStep() + ">";
  }
}