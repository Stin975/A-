/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

/**
 *
 * @author hotro
 */
public class AStar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        board b = new board();
        
        b.populateBoard();
        b.printStartingBoard();
        while(true){
        while(true){
        b.getStartingNode();
        b.getEndingingNode();
        if(b.startNode.equals(b.endNode)){
            System.out.println("Starting node and ending node can not be the same!");
        } else {
            break;
        }
        }
        b.printBoard();
        b.addHToNodes();
//        b.printH();
        b.printBoardP(b.path());
        b.clear();
        }
    }
    
}
