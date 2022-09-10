package de.laurens.tree;


import basis.Stift;

import java.util.ArrayList;

public class Tree
{
    private Node root;
    private StringBuilder row;

    public Tree()
    {
        root=null;
    }


    //kleinsten Nachfolger einer Node finden
    public Node smallest(Node node) {
        if(node.left != null) {
            smallest(node.left);
        }
        return node;
    }


    public void printSmallest() {
       System.out.println( smallest(root).getContent().id);
    }

    private Node insertAt(Node node, Content content)
    {
        if (node == null) node = new Node(content);
        else
        {
            if (content.id < node.getContent().id) node.left=insertAt(node.left,content);
            if (content.id > node.getContent().id) node.right=insertAt(node.right,content);
            if (content.id == node.getContent().id) System.out.println("mach ich nicht");
        }
        return node;
    }

    private void printAscAt(Node node)
    {
        if (node == null) return;

        printAscAt(node.left);
        System.out.print(node.getContent().id+" ");
        printAscAt(node.right);

    }

    private void printDescAt(Node node)
    {
        if (node == null) return;
        printDescAt(node.right);
        System.out.print(node.getContent().id+" ");

        printDescAt(node.left);
    }

    private Content searchAt(int id, Node node) {
        if(node == null) return new Content(-1, "not found");
        if(id > node.getContent().id ){
            return searchAt(id, node.right);
        } else if (id < node.getContent().id) {
            return searchAt(id, node.left);
        } else {
            return node.getContent();
        }
    }

    private int getLevelAt(int level, Node node, int id) {
        System.out.println(level + " " + node.getContent().id + " " + id);
        if (node == null) return 0;
        if(id > node.getContent().id) {
            return getLevelAt(level+1, node.right, id);
        } else if(id < node.getContent().id) {
            return getLevelAt(level+1, node.left, id);
        } else {
            return level;
        }
    }

    private Node printLevelRow(Node node, int level){
        if(node == null) return null;

        if(level != 0) {
            printLevelRow(node.left, level-1);
            printLevelRow(node.right, level-1);
        } else {
            row.append(node.getContent().id + " ");
        }
        return node;
    }

    public String printRow(int level) {
        row = new StringBuilder();
        printLevelRow(root, level-1);
        return row.toString();
    }

    private int getDepth(Node node, int depth) {
        if (node == null) return depth - 1;
        return Math.max(getDepth(node.right, depth + 1),
                getDepth(node.left, depth + 1));
    }

    public int depth() {
        return getDepth(root, 1);
    }

    public Content search(int id) {
        return searchAt(id, root);
    }

    public int getLevel(int id) {
        return getLevelAt(0, root, id);
    }

    public void insert(Content content)
    {
        root=insertAt(root,content);
    }

    public void printAsc()
    {
        printAscAt(root);
        System.out.println();
    }

    public void printDesc(){
        printDescAt(root);
        System.out.println();
    }

    public ArrayList<String> getTreeDrawInst() {
        return drawTree(root, new DrawAssistant(1, depth()));
    }



    public ArrayList<String> drawTree(Node node, DrawAssistant drawAssistant) {
        if (node == null) return null;
        drawAssistant.addToInstructions(node.getContent().value);

        if(node.left != null) {
            drawAssistant.moveDownLeft();
            drawTree(node.left, drawAssistant);
            drawAssistant.moveUpRight();
        }
        if(node.right != null) {
            drawAssistant.moveDownRight();
            drawTree(node.right, drawAssistant);
            drawAssistant.moveUpLeft();

        }

        return drawAssistant.getDrawInstructions();
    }
}
