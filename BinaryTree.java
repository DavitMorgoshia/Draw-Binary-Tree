package example;

import java.util.ArrayList;
import java.util.List;

/*

        Name: Davit Morgoshia
        Date: 18/05/2022

        Purpose of Program: Prints pretty binary tree with lines


        Commits: single commit 18/05/2022

*/

public class BinaryTree {
    Node root;

    public BinaryTree() {
    }

//    balanceing tree
    public void insert(int item){
        Node locptr = root;
        Node parent = null;
        boolean found = false;

        while (!found && locptr != null) {
            parent = locptr;
            if (item < locptr.data) {
                locptr = locptr.leftChiled;
            } else if (locptr.data < item) {
                locptr = locptr.rightChild;
            } else {
                found = true;
            }
        }
        if (!found) {
            locptr = new Node(item);
            if (parent == null) {
                root = locptr;
            } else if (item < parent.data) {
                parent.leftChiled = locptr;
            } else {
                parent.rightChild = locptr;
            }
        }
    }

//    adds lines to graph tree
    public String graph() {

        List<String> lines = new ArrayList<>();
        graphAux("", root, null, lines);

        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                if (lines.get(i).charAt(j) == '┌' || lines.get(i).charAt(j) == '┤' ||
                        lines.get(i).charAt(j) == '┐' ) {
                    int k = i+1;
                    while (lines.get(k).charAt(j) == ' ') {
                        String curr = lines.get(k);
                        lines.set(k, curr.substring(0, j) + '│' + curr.substring(j + 1));
                        k++;
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();

        for (String line : lines) {
            sb.append(line).append("\n");
        }
        return sb.toString();
    }

//    returns tree shaped
    public String graphAux(String indent, Node root, Node parent, List<String> lines) {

        String line = "";
        if (root != null) {
            if (parent == null) {
                line = line + graphAux(indent + " ".repeat(Integer.toString(root.data).length()), root.leftChiled, root, lines);
            } else {
                line = line + graphAux(indent + " ".repeat(Integer.toString(root.data).length() + 1), root.leftChiled, root, lines);
            }

            /* suffixer */
            String suffix = "";
            if (root.leftChiled != null && root.rightChild != null) {
                suffix = "┤";
            } else if (root.leftChiled != null || root.rightChild != null) {
                if (root.leftChiled != null) {
                    suffix = "┘";
                } else {
                    suffix = "┐";
                }
            }

            /* prefixer */
            String prefix = "";
            if (parent != null) {
                if (parent.leftChiled == root) {
                    prefix = "┌";
                } else if (parent.rightChild == root) {
                    prefix = "└";
                }
            }

            lines.add(indent + prefix + root.data + suffix);

            if (parent == null) {
                line = line + graphAux(indent + " ".repeat(Integer.toString(root.data).length()), root.rightChild, root, lines);
            } else {
                line = line + graphAux(indent + " ".repeat(Integer.toString(root.data).length() + 1), root.rightChild, root, lines);
            }

        }
        return line;
    }

}
