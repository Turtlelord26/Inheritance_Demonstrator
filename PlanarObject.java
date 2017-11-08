/**
 * Class serves as the top of the geometric class hierarchy
 * @author James Talbott
 */
public abstract class PlanarObject extends Object {
  
  /**
   * Method stub forces subclasses to override Object's inadequate equals method
   * @param obj - the Object the calling object will be compared to
   */
  public abstract boolean equals(Object obj);
  
  /**
   * Method stub forces subclasses to override Object's inadequate toString method
   */
  public abstract String toString();
}
  
  