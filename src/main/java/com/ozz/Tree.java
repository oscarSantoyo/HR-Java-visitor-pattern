package com.ozz;

public abstract class Tree {

    private int value;
    private Color color;
    private int depth;

    public Tree(int value, Color color, int depth) {
        this.value = value;
        this.color = color;
        this.depth = depth;
    }

    public int getValue() {
        return value;
    }

    public Color getColor() {
        return color;
    }

    public int getDepth() {
        return depth;
    }

    public abstract void accept(TreeVis visitor);

	@Override
	public String toString() {
      return new StringBuilder()
          .append( "Tree [value= " + value + "]").append('\n')
          .append( "Tree [color= " + color + "]").append('\n')
          .append( "Tree [depth= " + depth + "]").append('\n')
          .toString();
	}



}
