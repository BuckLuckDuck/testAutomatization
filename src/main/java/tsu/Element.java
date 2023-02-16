package tsu;

import java.util.*;

public class Element {
    private String tagName = null;

    private Double x = null;
    private Double y = null;
    private Double width = null;
    private Double height = null;

    private List<Element> children = null;

    public String getTagName() {
        return tagName != null ? tagName : "";
    }
    public Double getX() {
        return x != null ? x :0;
    }
    public Double getY() {
        return y != null ? y :0;
    }
    public Double getWidth() {
        return width != null ? width :0;
    }
    public Double getHeight() {
        return height != null ? height :0;
    }
    public Element[] getChildren() {
        return children != null ? children.toArray(new Element[children.size()]) : new Element[0];
    }

//    @Override
//    public String toString() {
//        return getTagName();
//    }

    @Override
    public String toString() {
        return "\nElement{" +
                "\ntagName='" + tagName + '\'' +
                "\nx=" + x +
                "\ny=" + y +
                "\nwidth=" + width +
                "\nheight=" + height +
                "\nchildren=" + children +
                "\n}\n";
    }
}
