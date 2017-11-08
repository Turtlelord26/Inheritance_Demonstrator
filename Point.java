/**
 * Class models a point in three-dimensional space
 * @author James Talbott
 */
public class Point extends PlanarObject {
  
  /**
   * Field stores the X-coordinate of the Point
   */
  private double xCoord;
  
  /**
   * Field stores the Y-coordinate of the Point
   */
  private double yCoord;
  
  /**
   * Field stores the Z-coordinate of the Point
   */
  private double zCoord;
  
  /**
   * Constructor initializes a Point given its three coordinates
   * @param x - the new Point's X-coordinate
   * @param y - the new Point's Y-coordinate
   * @param z - the new Point's Z-coordinate
   */
  public Point(double x, double y, double z) {
    xCoord = x;
    yCoord = y;
    zCoord = z;
  }
  
  /**
   * Method returns the x-coordinate of the Point
   * @return the value stored in the field xCoord
   */
  public double getX() {
    return xCoord;
  }
  
  /**
   * Method returns the y-coordinate of the Point
   * @return the value stored in the field yCoord
   */
  public double getY() {
    return yCoord;
  }
  
  /**
   * Method returns thez-coordinate of the Point
   * @return the value stored in the field zCoord
   */
  public double getZ() {
    return zCoord;
  }
  
  /**
   * Method returns the distance between two Points
   * @param p1 - The first Point
   * @param p2 - The second Point
   * @return the distance between the input Points
   */
  public static final double distanceBetweenPoints(Point p1, Point p2) {
    return Math.pow(Math.pow(p2.getX() - p1.getX(), 2) +
                    Math.pow(p2.getY() - p1.getY(), 2) +
                    Math.pow(p2.getZ() - p1.getZ(), 2), 0.5);
  }
  
  /**
   * Method overrides Object's inadequate equals method
   * @param obj - the Object to compare to the calling Point
   * @return whether the calling Point and input Object are identical
   */
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Point) {
      Point p = (Point) obj;
      return this.getX() == p.getX() && this.getY() == p.getY() && this.getZ() == p.getZ();
    }
    else
      return false;
  }
  
  /**
   * Method overrides Object's inadequate toString method
   * @return the Point as an ordered triplet
   */
  @Override
  public String toString() {
    return "(" + getX() + ", " + getY() + ", " + getZ() + ")";
  }
}