/**
* @author Elad Jedwabe <jed.el.me@gmail.com>
*
* @param <T> the object type used.
*/
public interface Task<T> {
    /**
    * The method is in charge of running the task.
    *
    * @return the respond.
    */
   T run();
}