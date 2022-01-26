/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author hotro
 */
public class board {
    public Node startNode;
    public Node endNode;
    public Node[][] board = new Node[15][15];
    public PriorityQueue<Node> openList;
    public ArrayList<Node> closedList = new ArrayList();

    public board() {
        this.openList = new PriorityQueue<Node>(new Comparator<Node>() {
            @Override
            public int compare(Node c, Node p) {
                return Integer.compare(c.getF(), p.getF());
            }
        });
    }
    
    
    public void populateBoard(){
        Random ran = new Random();
        int temp;
        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                temp = ran.nextInt(9);
                if(temp == 1){
                    board[i][j] = new Node(i,j,1);
                } else {
                    board[i][j] = new Node(i,j,0);
                }
            }
        }
    }
    
    public void printStartingBoard(){
        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                
                if(board[i][j].getT() == 1){
                    System.out.print("[B]");
                } else {
                    System.out.print("[-]");
                }
            }
            System.out.println("");
        }
    }
    
    public void getStartingNode(){
        int temp1 = -1;
        int temp2 = -1;
        Scanner s = new Scanner(System.in);
        while(true){
            while(temp1 < 0 || temp1 > 14){
                System.out.println("Enter Starting node row(1-15):");
                temp1 = s.nextInt() - 1;
            }
            while(temp2 < 0 || temp2 > 14){
                System.out.println("Enter Starting node column(1-15):");
                temp2 = s.nextInt() - 1;
            }
            if(board[temp1][temp2].getT() == 1){
                System.out.println("Starting node can not be a blocked node please select again.");
                temp1 = -1;
                temp2 = -1;
            } else {
                startNode = board[temp1][temp2];
                break;
            }
        }
    }
    
    public void getEndingingNode(){
        
        int temp1 = -1;
        int temp2 = -1;
        Scanner s = new Scanner(System.in);
        while(true){
            while(temp1 < 0 || temp1 > 14){
                System.out.println("Enter Ending node row(1-15):");
                temp1 = s.nextInt() - 1;
            }
            while(temp2 < 0 || temp2 > 14){
                System.out.println("Enter Ending node column(1-15):");
                temp2 = s.nextInt() - 1;
            }
            if(board[temp1][temp2].getT() == 1){
                System.out.println("Ending node can not be a blocked node please select again.");
                temp1 = -1;
                temp2 = -1;
            } else {
                endNode = board[temp1][temp2];
                break;
            }
        }
    }
    
    public void printBoard(){
            for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                if (i == startNode.getRow() && j == startNode.getCol()){
                    System.out.print("[S]");
                } else if (i == endNode.getRow()&& j == endNode.getCol()){
                    System.out.print("[E]");
                }
                else if (board[i][j].getT() == 1){
                    System.out.print("[B]");
                }
                else {
                    System.out.print("[-]");
                }
            }
            System.out.println("");
        }
        System.out.println("");
    }
    
    public void printBoardP(ArrayList<Node> temp){
            cord c;
            if(temp.isEmpty()){
                System.out.println("No path to end from start");
            }
            for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                c = new cord(i, j);
                if (i == startNode.getRow() && j == startNode.getCol()){
                    System.out.print("[S]");
                } else if (i == endNode.getRow() && j == endNode.getCol()){
                    System.out.print("[E]");
                }
                else if (board[i][j].getT() == 1){
                    System.out.print("[B]");
                } else if (temp.contains(board[i][j])){
                    
                    System.out.print("[*]");
                }
                else {
                    System.out.print("[-]");
                }
            }
            System.out.println("");
        }
            System.out.println("");
//         for(int i = 0; i < temp.size(); i++){
//             System.out.println(temp.get(i).getParent());
//         }
//         System.out.println(board[endNode.getRow()][endNode.getCol()].getParent());
    }
    
    public boolean checkCord(cord c, ArrayList<cord> temp){
        boolean temp1 = false;
        for(int i = 0; i < temp.size(); i++){
            temp1 =  c.cordEquals(temp.get(i));
         }
        return temp1;
    }
    
    public int calulateH(Node n){
         int temp = 0;
         temp = Math.abs((n.getRow()) - endNode.getRow()); // row
         temp = temp + Math.abs((n.getCol()) - endNode.getCol()); // column
         return temp*10;
     }
     
     public void addHToNodes(){
         for(int i = 0 ; i < 15; i++){
             for(int j = 0; j < 15; j++){
                 board[i][j].setH(calulateH(board[i][j]));
             }
         }
         
     }
     public void printH(){
         for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                
                
                    System.out.print("[" + board[i][j].getH() + "]");

            }
            System.out.println("");
        }
//         System.out.println(endNode.getRow() +", " + endNode.getCol());
//         System.out.println(startNode.getRow() +", " + startNode.getCol());
     }

     public void neighbor(Node c){
        if((c.getRow() + 1) < 15){
            if ((c.getCol() - 1) >= 0){
                check(c, c.getRow() + 1, c.getCol() - 1, 14);
            }
            if ((c.getCol() + 1) < 15){
                check(c, c.getRow() + 1, c.getCol() + 1, 14);
            }
            check(c, c.getRow() + 1, c.getCol(), 10);
        }
         
       
        
        if((c.getRow() - 1) >= 0){
            if ((c.getCol() - 1) >= 0){
                check(c, c.getRow() - 1, c.getCol() - 1, 14);
            }
            if ((c.getCol() + 1) < 15){
                check(c, c.getRow() - 1, c.getCol() + 1, 14);
            }
            check(c, c.getRow() - 1, c.getCol(), 10);
        }
        
         if ((c.getCol() - 1) >= 0){
            check(c, c.getRow(), c.getCol() - 1, 14);
        }
        if ((c.getCol() + 1) < 15){
            check(c, c.getRow(), c.getCol() + 1, 14);
        }
    }
     
     
     public void check(Node n, int r, int c, int g){
         Node temp = board[r][c];
         
         if(temp.getT() != 1 && !closedList.contains(temp)){
             if(!openList.contains(temp)){
                 
                 temp.setParent(n);
                 temp.setG(n.getG() + g);
                 temp.setF();
                 openList.add(temp);
                 
             } else {
                 
                 if(temp.getG() > (n.getG() + g)){
                     temp.setParent(n);
                     temp.setG(n.getG() + g);
                     temp.setF();
                     openList.remove(temp);
                     openList.add(temp);
                 }
             }
         }
         
     }
     
     public ArrayList<Node> path(){
         ArrayList<Node> p = new ArrayList<Node>();
         cord c;
         Node temp;
         p.clear();
//         System.out.println(startNode);
         openList.add(startNode);
//         System.out.println(openList);
         while(!(openList.isEmpty())){
             temp = openList.poll();
//             System.out.println(temp);
             closedList.add(temp);
//             System.out.println(temp.getRow() + ", " + temp.getCol());
             if(temp.getRow() == endNode.getRow() && temp.getCol() == endNode.getCol()){
                 
                 Node m = temp.getParent();
                 while(m.getParent()!= null){
                   //System.out.println(m);
                    p.add(m);
                    m = m.getParent();
                    
                 }
                 return p;
             } else {
                 neighbor(temp);
             }
         }
         return new ArrayList<Node>();
     }
     
     public void clear(){
         openList.clear();
         closedList.clear();
         for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                board[i][j].setParent(null);
                
            }
        }
     }
}
     


