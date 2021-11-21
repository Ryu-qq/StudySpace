package Inflean.BFSDFS;

import com.sun.tools.javac.Main;

import java.util.LinkedList;
import java.util.Queue;

class Node{
    int data;
    Node lt, rt;
    public Node(int data){
        this.data =data;
        lt=rt=null;
    }
}


public class 트리말단노드 {
    Node root;
    public int DFS(int L, Node root){
        if(root.lt ==null && root.rt ==null) return L;
        else return Math.min(DFS(L+1, root.lt), DFS(L+1, root.rt));
    }

    public int BFS(Node root){
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        int L=0;
        while(!q.isEmpty()){
            int len = q.size();
            for(int i=0; i<len; i++){
                Node cur = q.poll();
                if(cur.lt ==null && cur.rt ==null) return L;
                if(cur.lt!=null) q.offer(cur.lt);
                if(cur.rt!=null) q.offer(cur.rt);

            }
            L++;
        }

        return 0;
    }


    public static void main(String args[]){
        트리말단노드 tree = new 트리말단노드();
        tree.root = new Node(1);
        tree.root.lt = new Node(2);
        tree.root.rt = new Node(3);
        tree.root.lt.lt = new Node(4);
        tree.root.lt.rt = new Node(5);
        System.out.println(tree.DFS(0, tree.root));

    }
}
