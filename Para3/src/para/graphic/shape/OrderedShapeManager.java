package para.graphic.shape;

import para.graphic.target.Target;
import java.util.*;

/**
 * 描画順序をidの順とする図形集合
 */
public class OrderedShapeManager extends ShapeManager {

    final AbstractCollection<Shape> data;

    public OrderedShapeManager() {
        super();
        data = new TreeSet<Shape>(Comparator.comparing(Shape::getID).reversed());
        ;
    }

    /**
     * 図形の集合に図形を追加する
     * 
     * @param in 追加する図形
     */
    public synchronized void add(Shape in) {
        data.add(in);
    }

    /**
     * 集合内の図形を出力する．
     * 
     * @param target 出力装置
     */
    public synchronized void draw(Target target) {
        for (Shape s : data) {
            s.draw(target);
        }
    }

}
