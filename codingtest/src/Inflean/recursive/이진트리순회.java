package Inflean.recursive;

import java.util.LinkedList;
import java.util.Queue;

public class 이진트리순회 {
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

    public void BFS(Node root){
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        int L=0;
        while(!q.isEmpty()){
            int len = q.size();
            System.out.println("len = " + len);
            for(int i=0; i<len; i++){
                Node poll = q.poll();
                System.out.println("poll = " + poll.data);
                if(poll.lt !=null) q.offer(poll.lt);
                if(poll.rt !=null) q.offer(poll.rt);
            }
            L++;
            System.out.println();

        }
    }


    public static void main(String args[]){
        이진트리순회 tree = new 이진트리순회();
        tree.root = new Node(1);
        tree.root.lt = new Node(2);
        tree.root.rt = new Node(3);
        tree.root.lt.lt = new Node(4);
        tree.root.lt.rt = new Node(5);
        tree.root.rt.lt = new Node(6);
        tree.root.rt.rt = new Node(7);

        //tree.DFS(tree.root);
        tree.BFS(tree.root);

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