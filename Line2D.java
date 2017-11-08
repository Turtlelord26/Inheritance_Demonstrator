/**
 * Class models a line in two-dimensional space
 * @author James Talbott
 */
public class Line2D extends Line {
  
  /**
   * Constructor initializes a Line2D based on two points
   * @param p1 - The first Point2D used to define the Line2D
   * @param p2 - The second Point2D used to define the Line2D
   */
  public Line2D(Point2D p1, Point2D p2) {
    super(p1, p2);
  }
  
  //isParallel, intersection, and equals methods found in immediate parent class
  
  /**
   * Method returns the distance between a Point and a Line
   * @param p - the Point2D under consideration
   * @param l - The Line2D under consideration
   * @return the shortest distance from the Point2d to the Line 2D
   */
  public static final double distanceBetweenPoint2DAndLine2D(Point2D p, Line2D l) {
    return (l.getXStep() * p.getX() + l.getYStep() * p.getY() + l.getPoint().getX() * l.getYStep()) /
      (Math.hypot(l.getXStep(), l.getYStep()));
  }
  
  /**
   * Method overrides Line's toString method
   * @return the Line2D's equation in slope-intercept form
   */
  @Override
  public String toString() {
    return "y= " + getYStep() / getXStep() + "x + " + (getPoint().getY() +
      (-1 * getPoint().getX() * getYStep()) / getXStep());
  }
}