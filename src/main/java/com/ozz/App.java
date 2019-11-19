package com.ozz;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Hello world!
 *
 */


class SumInLeavesVisitor extends TreeVis {
    Integer result = 0;
    public int getResult() {
        return result;
    }

    public void visitNode(TreeNode node) {
    }

    public void visitLeaf(TreeLeaf leaf) {
        result = result + leaf.getValue();
    }
}

class ProductOfRedNodesVisitor extends TreeVis {
    long result = 1;
    private static final int M = 1000000007;

    public int getResult() {
        return (int) result;
    }

    public void visitNode(TreeNode node) {
        if(node.getColor() == Color.RED){
            result = (result * node.getValue()) % M;
        }
    }

    public void visitLeaf(TreeLeaf leaf) {
        if(leaf.getColor() == Color.RED){
            result = (result * leaf.getValue()) %M;
        }
    }
}

class FancyVisitor extends TreeVis {
    Integer sumNodes = 0;
    Integer sumLeaves = 0;
    public int getResult() {
        return abs(sumNodes - sumLeaves);
    }

    private Integer abs(Integer number) {
        if(number <0) {
            return number * -1;
        } else {
            return number;
        }
    }

    public void visitNode(TreeNode node) {

        if(node.getDepth() % 2 == 0)
            sumNodes = sumNodes + node.getValue();
    }

    public void visitLeaf(TreeLeaf leaf) {
        if(leaf.getColor() == Color.GREEN)
            sumLeaves = sumLeaves + leaf.getValue();
    }
}

public class App {
    Tree parent = null;

    public Tree createNode(int index,  int depth, HashMap<Integer, HashSet<Integer>> map,int[] values, int[] colors ) {
        TreeNode parent = null;
        TreeLeaf leaf = null;

        HashSet<Integer> children = map.get(index);
        Integer value = values[index - 1];
        Color color = colors[index-1] == 1 ? Color.GREEN : Color.RED;
        if(children != null && children.size() > 0) {
            parent = new TreeNode(value, color, depth);
        } else {
            leaf = new TreeLeaf(value, color, depth);
        }

        if(children != null)
        for(int child : children) {
            map.get(child).remove(index);
            Tree childTree = createNode(child, depth + 1, map, values, colors);
            parent.addChild(childTree);
        }

        return parent != null ? parent : leaf;
    }

    private int[] strArrtoIntArr(String[] strArr){
        int[] intArr = new int[strArr.length];
        for(int i = 0; i < intArr.length; i++) {
            intArr[i] = Integer.parseInt(strArr[i]);
        }
        return intArr;
    }

    public App(BufferedReader input) throws IOException {
        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();

        int len = Integer.parseInt(input.readLine());
        int[] values = strArrtoIntArr(input.readLine().split("\\s"));
        int[] colors = strArrtoIntArr(input.readLine().split("\\s"));

        for (int i = 0; i < len -1; i++){
            int [] relation = strArrtoIntArr(input.readLine().split("\\s"));
            Integer parent = relation[0];
            Integer child = relation[1];
            map = upsertToMap(map, parent, child);
            map = upsertToMap(map, child, parent);
        }

        parent = createNode(1, 0, map, values, colors);
	}

    private HashMap<Integer, HashSet<Integer>> upsertToMap(HashMap<Integer, HashSet<Integer>> map, Integer parent,
			Integer child) {
        HashSet<Integer> children = map.get(parent);
        if(children == null) {
            children = new HashSet<>();
        }
    children.add(child);
    map.put(parent, children);
		return map;
	}

	public Tree solve() {
      return parent;
    }


}
