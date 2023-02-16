package tsu;

import java.util.List;

public class Elements {
    private List<Element> children = null;

    public Element[] getChildren() {
        return children != null ? children.toArray(new Element[children.size()]) : new Element[0];
    }

    @Override
    public String toString() {
        return "Elements{" +
                "children=" + children +
                '}';
    }
}
