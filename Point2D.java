/**
 * Class models a point in 2-dimensional space
 * @author James Talbott
 */
public class Point2D extends Point {
  
  /**
   * Constructor initiates a Point2D using an x and y coordinate pair
   * @param x - The x-coordinate of the Point2D
   * @param y - The y-coordinate of the Point2D
   */
  public Point2D(double x, double y) {
    super(x, y, 0);
  }
  
  //getX, getY, static distanceBetweenPoints, and equals methods found in immediate parent class.
  
  /**
   * Method returns whether two points have identical coordinates
   * @return the Point2D as a coordinate pair
   */
  @Override
  public String toString() {
    return "(" + getX() + ", " + getY() + ")";
  }
}