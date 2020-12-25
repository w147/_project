package com.plaso.tree;

import java.util.ArrayList;

class Tree{
    int value;
    Tree left;
    Tree right;
    public Tree(int value){
        this.value=value;
    }
}

public class Find {

    private static Tree root;
    //用于构建二叉排序树
    public void addTree(int value){
        Tree newTree=new Tree(value);
        if(root==null){
            root=newTree;
        }else{
            Tree foucs=root;
            Tree parent;
            while(true){
                parent=foucs;
                if(value<foucs.value){
                    foucs=foucs.left;
                    if(foucs==null){
                        parent.left=newTree;
                        return;
                    }
                }else{
                    foucs=foucs.right;
                    if(foucs==null){
                        parent.right=newTree;
                        return;
                    }
                }
            }

        }
    }



    //前序遍历打印二叉树
    public void printTree(Tree root){
        if(root==null){
            return;
        }

        System.out.print(root.value+"  ");
        printTree(root.left);
        printTree(root.right);

    }

    //查找二叉树路径节点值之和等于result的路径
    public void findPath(Tree root,int result,ArrayList<Integer> list,int currentSum){
        currentSum+=root.value;

        list.add(root.value);
        //判断当前结点是否是叶子节点
        boolean isLeaf=root.left==null&&root.right==null;
        //判断是否和要求的值相等
        if(currentSum==result&&isLeaf){
            System.out.println("\n");
            System.out.println("发现路径：");
            for(Integer i:list){
                System.out.print(i+"-");
            }
            System.out.println("\n");
        }
        if(root.left!=null){
            findPath(root.left,result,list,currentSum);
        }
        if(root.right!=null){
            findPath(root.right,result,list,currentSum);
        }
        if(list.size()>=0){
            list.remove(list.size()-1);
        }


    }



    public static void main(String [] args){

        int[] num={5,2,4,6,8,9,7};
        Find f=new Find();

        for(int i=0;i<num.length;i++){
            f.addTree(num[i]);
        }
        f.printTree(root);
        ArrayList<Integer> list=new ArrayList<Integer>();
        f.findPath(root, 26, list, 0);
    }

}