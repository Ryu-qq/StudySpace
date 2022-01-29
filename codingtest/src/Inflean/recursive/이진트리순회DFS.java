package Inflean.recursive;

public class 이진트리순회DFS {
    Node root;
    public void DFS(Node root){
        if(root == null) return;
        else{
            System.out.println(root.data + " "); //전위순회
            DFS(root.lt);
            //System.out.println(root.data + " "); //중위순회
            DFS(root.rt);
            //System.out.println(root.data + " "); //후위순회

        }


    }


    public static void main(String args[]){
        이진트리순회DFS tree = new 이진트리순회DFS();
        tree.root = new Node(1);
        tree.root.lt = new Node(2);
        tree.root.rt = new Node(3);
        tree.root.lt.lt = new Node(4);
        tree.root.lt.rt = new Node(5);
        tree.DFS(tree.root);

    }
}

class Node{
    int data;
    Node lt, rt;
    public Node(int data){
        this.data = data;
        lt = rt = null;
    }
}