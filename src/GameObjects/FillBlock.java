import java.util.ArrayList;
import java.util.List;

/**
* @author Elad Jedwabe <jed.el.me@gmail.com>
*/
public class FillBlock {
    private List<DrawTask> fill = new ArrayList<DrawTask>();
    private DrawTask defaultFill;
    private List<Integer> k = new ArrayList<Integer>();
    /**
     * The method is in charge of setting a defaultFill.
     *
     * @param f the new default-fill.
     */
    public void defaultFill(DrawTask f) {
        this.defaultFill = f;
    }
    /**
     * The method is in charge of adding a new fill.
     *
     * @param f the new fill.
     * @param num the new fill hit point value.
     */
    public void addToFill(int num, DrawTask f) {
        int n = this.k.indexOf(num);
        if (n >= 0) {
            this.fill.remove(n);
            this.k.remove(n);
        }
        this.fill.add(f);
        this.k.add(num);
    }
    /**
     * The method is in charge of finding the fill for the given hit-count.
     *
     * @param hitCount the hit point value.
     *
     * @return a DrawTask.
     */
    public DrawTask getFill(int hitCount) {
        for (int i = 0; i < fill.size() && i < k.size(); i++) {
            if (k.get(i) == hitCount) {
                return fill.get(i);
            }
        }
        return this.defaultFill;
    }
    /**
     * The method is in charge of checking if the FillBlock as all
     * the values needed to a block with the given hit-count.
     *
     * @param num the hit point value.
     *
     * @return true if yes, and false otherwise.
     */
    public boolean check(int num) {
        if (this.defaultFill != null) {
            return true;
        }
        for (int i = 1; i <= num; i++) {
            if (this.k.indexOf(i) < 0) {
                return false;
            }
        }
        return true;
    }
    /**
     * The method is in charge of creating a clone of the FillBlock.
     *
     * @return the new FillBlock.
     */
    public FillBlock createClone() {
        FillBlock fb = new FillBlock();
        fb.defaultFill(this.defaultFill);
        for (int i = 0; i < this.fill.size() && i < this.fill.size(); i++) {
            fb.addToFill(this.k.get(i), this.fill.get(i));
        }
        return fb;
    }
}
