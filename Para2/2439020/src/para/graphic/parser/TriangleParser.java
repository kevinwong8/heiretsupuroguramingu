package para.graphic.parser;

import java.util.Scanner;

import para.graphic.shape.Attribute;
import para.graphic.shape.Circle;
import para.graphic.shape.Triangle;

public class TriangleParser implements ShapeParser {
    TriangleParser() {
    }

    @Override
    public Triangle parse(Scanner s, int id) {
        int x = s.nextInt();
        int y = s.nextInt();
        int r = s.nextInt();
        Triangle ret;
        Attribute attr = null;
        if (s.hasNext("Attribute")) {
            attr = AttributeParser.parse(s);
        }
        ret = new Triangle(id, x, y, r, attr);
        return ret;
    }

}
