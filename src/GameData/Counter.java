/**
* @author Elad Jedwabe <jed.el.me@gmail.com>
*/
public class Counter {
    private int count;
    /**
     * Constructor for Counter.
     *
     * @param num for count value.
     */
    public Counter(int num) {
        this.count = num;
    }
   /**
    * The method is in charge of adding a given number to the current count.
    *
    * @param number the number to add.
    */
   public void increase(int number) {
       this.count = this.count + number;
   }
   /**
    * The method is in charge of subtracting a given number to the current count.
    *
    * @param number the number to subtract.
    */
   public void decrease(int number) {
       this.count = this.count - number;
   }
   /**
    * The method is in charge of finding the current count value.
    *
    * @return the current count value.
    */
   public int getValue() {
       return this.count;
   }
}