package com.example;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

//you will need to implement two functions in this file.
public class Piece {
    private final boolean color;
    private BufferedImage img;
    
public Piece(boolean isWhite, String img_file) {
    this.color = isWhite;
    this.img = null;
     
    try {
        this.img = ImageIO.read(new File(System.getProperty("user.dir")+img_file));
      } catch (IOException e) {
        System.out.println("File not found: " + e.getMessage());
      }
}
    
    

    
    public boolean getColor() {
        return color;
    }
    
    public Image getImage() {
        return img;
    }
    
    //precondition: g and currentSquare must be on-null valid objects.
    //postcondition: the image stored in the img property of this object is drawn to the screen.
    public void draw(Graphics g, Square currentSquare) {
        if (this.img != null) {
            int x = currentSquare.getX()+5;
            int y = currentSquare.getY()+5;
            
            g.drawImage(this.img, x, y, null);
        }
    }
    
    
    // TO BE IMPLEMENTED!
    //return a list of every square that is "controlled" by this piece. A square is controlled
    //if the piece capture into it legally.
    public ArrayList<Square> getControlledSquares(Square[][] board, Square start) {
        ArrayList<Square> moves = new ArrayList<>();
        int startRow = start.getRow();
        int startCol = start.getCol();
        
        if (startRow > 0) {
            Square s = board[startRow - 1][startCol];
            if (!s.isOccupied() || (s.isOccupied() && s.getOccupyingPiece().getColor() != this.getColor())) {
                moves.add(s);
            }
        }
        
        if (startRow < 7) {
            Square s = board[startRow + 1][startCol];
            if (!s.isOccupied() || (s.isOccupied() && s.getOccupyingPiece().getColor() != this.getColor())) {
                moves.add(s);
            }
        }
        
        if (startCol > 0) {
            Square s = board[startRow][startCol - 1];
            if (!s.isOccupied() || (s.isOccupied() && s.getOccupyingPiece().getColor() != this.getColor())) {
                moves.add(s);
            }
        }
        
        if (startCol < 7) {
            Square s = board[startRow][startCol + 1];
            if (!s.isOccupied() || (s.isOccupied() && s.getOccupyingPiece().getColor() != this.getColor())) {
                moves.add(s);
            }
        }
        
        return moves;
    }
    

    //TO BE IMPLEMENTED!
    //implement the move function here
    //it's up to you how the piece moves, but at the very least the rules should be logical and it should never move off the board!
    //returns an arraylist of squares which are legal to move to
    //please note that your piece must have some sort of logic. Just being able to move to every square on the board is not
    //going to score any points.
    public ArrayList<Square> getLegalMoves(Board b, Square start){
        ArrayList<Square> legalMoves = new ArrayList<>();
        for(Square s: getControlledSquares(b.getSquareArray(), start)){
            // if(!s.isOccupied() || s.getOccupyingPiece().getColor() != this.getColor()) {
                 legalMoves.add(s);
            //}
        }
        return legalMoves;
    }
}