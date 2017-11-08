/**
 * Class models a plane
 * @author James Talbott
 */
public class Plane extends PlanarObject {
  
  /**
   * Field stores a Point that lies on the Plane
   */
  private Point point;
  
  /**
   * Field stores the defining normal Vector of the Plane
   */
  private Vector normal;
  
  /**
   * Constructor initializes a Plane based on a Point and the defining normal Vector
   * @param p - a Point to anchor the normal Vector
   * @param v - a Vector perpendicular to the initializing Plane
   */
  public Plane(Point p, Vector v) {
    point = p;
    normal = v;
  }
  
  /**
   * Constructor initializes a Plane using three Points to find the defining normal Vector
   * @param p1 - the point on which the two generated Vectors are based, is also the point used to anchor the normal
   * @param p2 - the head of one of the Vectors used to find the normal Vector
   * @param p3 - the head of the other Vector used to find the normal Vector
   */
  public Plane(Point p1, Point p2, Point p3) {
    this(p1, Vector.crossProduct(new Vector(p2.getX() - p1.getX(), p2.getY() - p1.getY(), p2.getZ() - p1.getZ()),
                                 new Vector(p3.getX() - p1.getX(), p3.getY() - p1.getY(), p3.getZ() - p1.getZ())));
  }
  
  /**
   * Method calculates the d value of the general equation for a Plane ax + by + cz + d = 0
   * @param e - The Plane for which to calculate the d value
   * @param p - The point to use in the calculation
   * @return the value of d in Plane e's general equation
   */
  private static double dValue(Plane e, Point p) {
    return -1 * (e.getNormal().getXStep() * p.getX() +
                 e.getNormal().getYStep() * p.getY() +
                 e.getNormal().getZStep() * p.getZ());
  }
  
  /**
   * Method returns a point known to be on the plane
   * @return the Point used to define the Plane
   */
  private Point getPoint() {
    return point;
  }
  
  /**
   * Method returns the defining normal Vector of the Plane
   * @return the Vector used to define the Plane
   */
  public Vector getNormal() {
    return normal;
  }
  
  /**
   * Method returns whether the plane contains the input Point
   * @param p - the Point to test
   * @return whether the input Point lies on the calling Plane
   */
  public boolean contains(Point p) {
    return Plane.dValue(this, getPoint()) == Plane.dValue(this, p);
  }
  
  /**
   * Method returns whether two Planes are parallel
   * @param e1 - the first Plane
   * @param e2 - the second Plane
   * @return whether the Planes' normal Vectors are parallel
   */
  public static boolean isParallel(Plane e1, Plane e2) {
    return Vector.isParallel(e1.normal, e2.normal);
  }
  
  /**
   * Method returns whether two Planes are perpendicular
   * @param e1 - the first Plane
   * @param e2 - the second Plane
   * @return whether the Planes' normal Vectors are perpendicular
   */
  public static boolean isOrthogonal(Plane e1, Plane e2) {
    return Vector.isOrthogonal(e1.normal, e2.normal);
  }
  
  /**
   * Method returns the Line defining the intersection of the two input Planes
   * @param e1 - the first Plane
   * @param e2 - the second Plane
   * @return the Line defining the intersection of the two Planes
   */
  public static Line intersection(Plane e1, Plane e2) {
    if (Vector.isParallel(e1.getNormal(), e2.getNormal()))
      return null;
    else {
      double d1 = Plane.dValue(e1, e1.getPoint());
      double d2 = Plane.dValue(e2, e2.getPoint());
      Vector v = Vector.crossProduct(e1.getNormal(), e2.getNormal());
      Point p = null;
      if (v.getXStep() != 0) {
        double x = -1 * (d1 - d2) / v.getXStep();
        p = new Point(x, 0, 0);
      }
      else if (v.getYStep() != 0) {
        double y = -1 * (d1 - d2) / v.getYStep();
        p = new Point(0, y, 0);
      }
      else if (v.getZStep() != 0) {
        double z = -1 * (d1 - d2) / v.getZStep();
        p = new Point(0, 0, z);
      }
      else
        return null;
      return new Line(p, v);
    }
  }
  
  /**
   * Method returns the distance between a Point and a Plane
   * @param p - The Point under consideration
   * @param e - The Plane under consideration
   * @return the shortest distance between the Point and the Plane
   */
  public static final double distanceBetweenPointAndPlane(Point p, Plane e) {
    return (e.getNormal().getXStep() * p.getX() + e.getNormal().getYStep() * p.getY() +
            e.getNormal().getZStep() * p.getZ() + Plane.dValue(e, e.getPoint())) /
      Math.pow(Math.pow(e.getNormal().getXStep(), 2) + Math.pow(e.getNormal().getYStep(), 2) +
               Math.pow(e.getNormal().getZStep(), 2), 0.5);
  }
  
  /**
   * Method overrides the equals method of PlanarObject
   * @param obj - the Object to which to compare the calling Plane
   * @return whether the calling Plane and input Object are identical
   */
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Plane) {
      Plane e = (Plane) obj;
      return Vector.isParallel(getNormal(), e.getNormal()) && contains(e.getPoint());
    }
    else
      return false;
  }
  
  /**
   * Method overrides the toString method of PlanarObject
   * @return the general equation of the calling Plane
   */
  @Override
  public String toString() {
    return getNormal().getXStep() + "x + " + getNormal().getYStep() + "y + " + getNormal().getZStep() + "z + " +
      Plane.dValue(this, getPoint()) + " = 0";
  }
}