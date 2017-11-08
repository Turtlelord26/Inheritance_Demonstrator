/**
 * Class models a line in three-dimensional space
 * @author James Talbott
 */
public class Line extends Vector {
  
  /**
   * Field stores a point to anchor a vector, thus defining a Line
   */
  private Point point;
  
  /**
   * Constructor initializes a Line through two given Points
   * @param p1 - An arbitrary Point
   * @param p2 - Another arbitrary Point
   */
  public Line(Point p1, Point p2) {
    super(p2.getX() - p1.getX(), p2.getY() - p1.getY(), p2.getZ() - p1.getZ());
    this.point = p1;
  }
  
  /**
   * Constructor initializes a Line from a given Vector, with a given Point to anchor it
   * @param p - The point to which to anchor the Vector
   * @param v - The vector pointing in the direction the Line will go
   */
  public Line(Point p, Vector v) {
    super(v.getXStep(), v.getYStep(), v.getZStep());
    this.point = p;
  }
  
  /**
   * Method allows access to the Line's anchor Point
   * @return the Point used to initialize the Line
   */
  protected Point getPoint() {
    return point;
  }
  
  /**
   * Method returns a Vector parallel to the Line
   * @return the Vector used to initialize the Line
   */
  public Vector getVector() {
    return new Vector(getXStep(), getYStep(), getZStep());
  }
  
  /**
   * Method returns whether a given Point lies on a given Line
   * @param p - The point that might be on the Line;
   * @return whether the input point lies on the calling Line
   */
  public boolean contains(Point p) {
    //if statement avoids division by zero errors
    double step = 0;
    if (getXStep() != 0)
      step = (p.getX() - getPoint().getX()) / getXStep();
    else if (getYStep() != 0)
      step = (p.getX() - getPoint().getY()) / getYStep();
    else if (getZStep() != 0)
      step = (p.getX() - getPoint().getZ()) / getZStep();
    else
      return false;
    return ((p.getX() - getPoint().getX()) == getXStep() * step &&
            (p.getY() - getPoint().getY()) == getYStep() * step &&
            (p.getZ() - getPoint().getZ()) == getZStep() * step);
  }
  
  /**
   * Method returns whether two given Lines are parallel
   * @param l1 - An arbitrary Line
   * @param l2 - Another arbitrary Line
   * @return true if the Lines are parallel and false elsewise
   */
  public static boolean isParallel(Line l1, Line l2) {
    return Vector.isParallel(l1.getVector(), l2.getVector());
  }
  
  /**
   * Method returns the intersection of two lines, or null if there is no intersection
   * @param l1 - An arbitrary line
   * @param l2 - Another arbitrary line
   * @return the intersection between l1 and l2, if it exists. Returns null if the lines are coincident.
   */
  public static Point intersection(Line l1, Line l2) {
    if (l1.equals(l2))
      return null;
    double step = 0;
    //if statement avoids division by zero errors
    if (l1.getXStep() != 0)
      step = (l2.getPoint().getX() - l1.getPoint().getX()) / l1.getXStep();
    else if (l1.getYStep() != 0)
      step = (l2.getPoint().getY() - l1.getPoint().getY()) / l1.getYStep();
    else if (l1.getZStep() != 0)
      step = (l2.getPoint().getZ() - l1.getPoint().getZ()) / l1.getZStep();
    else
      return null;
    if (l2.getPoint().getX() - l1.getPoint().getX() == step * l1.getXStep() &&
        l2.getPoint().getY() - l1.getPoint().getY() == step * l1.getYStep() &&
        l2.getPoint().getZ() - l1.getPoint().getZ() == step * l1.getZStep())
      return new Point(l1.getPoint().getX() + step * l1.getXStep(), l1.getPoint().getY() + step * l1.getYStep(),
                       l1.getPoint().getZ() + step * l1.getZStep());
    else
      return null;
  }
  
  /**
   * Method overrides Vector's magnitude method as Lines do not have finite length
   * @return Infinity
   */
  @Override
  public double magnitude() {
    return Double.MAX_VALUE * 2;
  }
  
  /**
   * Method overrides Vector's unitVector method to to avoid division by Infinity
   * @return null
   */
  @Override
  public Vector unitVector() {
    return getVector().unitVector();
  }
  
  /**
   * Method overrides Vector's equals method
   * @param obj - The Object to compare to the Line calling equals
   * @return - true if obj is a Line and is coincident with the Line calling equals
   */
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Line) {
      Line l = (Line) obj;
      return contains(l.getPoint()) && contains(new Point(l.getPoint().getX() + l.getXStep(),
                                                          l.getPoint().getY() + l.getYStep(),
                                                          l.getPoint().getZ() + l.getZStep()));
    }
    else
      return false;
  }
  
  /**
   * Method overrides Vector's toString method
   * @return a String containing the three defining parametric equations of the Line
   */
  @Override
  public String toString() {
    return "x = " + getPoint().getX() + " + " + getXStep() + "t\ny = " + getPoint().getY() + " + " + getYStep() +
      "t\nz = " + getPoint().getZ() + " + " + getZStep() + "t";
  }
}