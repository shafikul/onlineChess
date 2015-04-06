package com;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.io.IOException;

public class Calculate_Moves {

    public int X, Y;
    private Calculate_class[] guti;
    private int inHand;

    public Calculate_Moves() 
    {
        guti = new Calculate_class[33];
        int i = 1;
        guti[i++] = new Calculate_class("Rook_Black", 1, 1, 4, "Black");
        guti[i++] = new Calculate_class("Rook_Black", 8, 1, 4, "Black");
        guti[i++] = new Calculate_class("Knight_Black", 2, 1, 2, "Black");
        guti[i++] = new Calculate_class("Knight_Black", 7, 1, 2, "Black");
        guti[i++] = new Calculate_class("Bishop_Black", 3, 1, 3, "Black");
        guti[i++] = new Calculate_class("Bishop_Black", 6, 1, 3, "Black");
        guti[i++] = new Calculate_class("Queen_Black", 4, 1, 5, "Black");
        guti[i++] = new Calculate_class("King_Black", 5, 1, 6, "Black");
        int j = 1;
        for (int k = 0; k <= 7; k++, j++) {
            guti[i++] = new Calculate_class("Pawn_Black", j, 2, 1, "Black");
        }
        guti[i++] = new Calculate_class("Rook_White", 8, 8, 4, "White");
        guti[i++] = new Calculate_class("Rook_White", 1, 8, 4, "White");
        guti[i++] = new Calculate_class("Knight_White", 2, 8, 2, "White");
        guti[i++] = new Calculate_class("Knight_White", 7, 8, 2, "White");
        guti[i++] = new Calculate_class("Bishop_White", 3, 8, 3, "White");
        guti[i++] = new Calculate_class("Bishop_White", 6, 8, 3, "White");
        guti[i++] = new Calculate_class("Queen_White", 4, 8, 5, "White");
        guti[i++] = new Calculate_class("King_White", 5, 8, 6, "White");
        j = 1;
        for (int k = 0; k <= 7; k++, j++) {
            guti[i++] = new Calculate_class("Pawn_White", j, 7, 1, "White");
        }
    }

//    public String[][] setPieces(String[][] piecepositions) {
//        for (int i = 1; i <= 32; i++) {
//            Point p = guti[i].returnPostion();
//            int x = (int) (p.getX() - 1);
//            int y = (int) (p.getY() - 1);
//            try {
//                piecepositions[y][x] = guti[i].getPieceIcon().getPieceIconName();
//            } catch (Exception e) {
//            }
//        }
//        return piecepositions;
//    }

    public Point returnPostion(int i) {
        if (i >= 1 && i <= 32) {
            return guti[i].returnPostion();
        }
        return new Point(-1, -1);
    }

    public Point returnOldPostion(int i) {

        if (i >= 1 && i <= 32) {
            return guti[i].returnOld();
        }
        return new Point(-1, -1);
    }

    public boolean castle_moved(int i) {
        if (i == 17 || i == 18 || i == 1 || i == 2) {
            return guti[i].movedbefore();
        }
        return true;
    }

    public int getstate(int inHand) {
        if (inHand >= 1 && inHand <= 32) {
            return guti[inHand].getstate();
        }
        return 0;
    }
   
    public void changePostion(Point newPoint, int i) {
        if (i >= 1 && i <= 32) {
            guti[i].setPoint(newPoint);
        }
    }

    public void changePostionToOld(Point newPoint, int i) {
        if (i >= 1 && i <= 32) {
            guti[i].toOld(newPoint);
        }
    }

    public void changePixel(int newPixelX, int newPixelY, int i) {
        if (i >= 1 && i <= 32) {
            guti[i].setPixels(newPixelX, newPixelY);
        }
    }

    public Point getPixelPoint(int i) {
        if (i >= 1 && i <= 32) {
            return guti[i].getpixelPoint();
        }
        return null;
    }

    public boolean has_moved(int inHand) {
        return guti[inHand].movedbefore();
    }

    public void set_image(int inHand, Point current, int side, int state) {
        if (side == 1) {
            switch (state) {
                case 2:
                    guti[inHand].Set_Soilder_image("Knight_White", current.x, current.y, state);
                    break;
                case 3:
                    guti[inHand].Set_Soilder_image("Bishop_White", current.x, current.y, state);
                    break;
                case 4:
                    guti[inHand].Set_Soilder_image("Rook_White", current.x, current.y, state);
                    break;
                case 5:
                    guti[inHand].Set_Soilder_image("Queen_White", current.x, current.y, state);
                    break;
            }
        } else if (side == 2) {
            switch (state) {
                case 2:
                    guti[inHand].Set_Soilder_image("Knight_Black", current.x, current.y, state);
                    break;
                case 3:
                    guti[inHand].Set_Soilder_image("Bishop_Black", current.x, current.y, state);
                    break;
                case 4:
                    guti[inHand].Set_Soilder_image("Rook_Black", current.x, current.y, state);
                    break;
                case 5:
                    guti[inHand].Set_Soilder_image("Queen_Black", current.x, current.y, state);
                    break;
            }
        }
    }

    public boolean Killedpiec(int i) {
        Point out = new Point(13, 13);
        if (i == 24 || i == 8) {
            return true;
        } else if (i >= 1 && i <= 32) {
            guti[i].setPoint(out);
            return true;
        } else {
            return false;
        }
    }

    public void SetInhand(int i) {
        inHand = i;
    }

    public int GetInhand() {
        return inHand;
    }

    public boolean canmove(Point current, int inHand, int[][] board) {
        int i = guti[inHand].getstate();
        String str = guti[inHand].getside();
        boolean movedbefore = guti[inHand].movedbefore();
        X = guti[inHand].returnX();
        Y = guti[inHand].returnY();

        switch (i) {
            case 1:
                return Soilder_Canmove(str, current, movedbefore, board);
            case 2:
                return Horse_Canmove(current);
            case 3:
                return Elephant_Canmove(current);
            case 4:
                return Castle_Canmove(current);
            case 5:
                return Queen_Canmove(current);
            case 6:
                return King_Canmove(current);
        }
        return false;
    }

    public boolean King_Canmove(Point current) {
        int x = current.x;
        int y = current.y;
        if (((y == Y) && (x == (X - 1))) || ((y == Y - 1) && (x == (X + 1)))
                || ((y == Y - 1) && (x == (X - 1))) || ((y == Y + 1) && (x == (X + 1)))
                || (((y == Y + 1) && x == (X - 1))) || ((y == Y) && (x == (X + 1)))
                || ((y == Y - 1) && x == ((X))) || ((y == Y + 1) && (x == (X)))) {
            return true;
        }
        // else if(!movedBefore && y == Y && X==5 && (x == 3 || x == 7))return true;
        return false;

    }

    public boolean Queen_Canmove(Point current) {
        int x = current.x;
        int y = current.y;
        if (((y == Y) && (x > (X) || (x < X)))) {
            return true;

        } else if ((((y > Y) || (y < Y)) && (x == (X)))) {
            return true;
        } else if ((x - y) == (X - Y)) {
            return true;

        } else if ((x + y) == (X + Y)) {
            return true;

        } else {
            return false;
        }

    }

    public boolean Horse_Canmove(Point current) {
        int x = current.x;
        int y = current.y;
        if ((x + 1 == X) && (y + 2 == Y) || (x + 1 == X) && (y - 2 == Y) || (x - 1 == X) && (y + 2 == Y) || (x - 1 == X) && (y - 2 == Y) || (x + 2 == X) && (y + 1 == Y)
                || (x + 2 == X) && (y - 1 == Y) || (x - 2 == X) && (y + 1 == Y) || (x - 2 == X) && (y - 1 == Y)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean Elephant_Canmove(Point current) {

        int x = current.x;
        int y = current.y;
        int j = y;
        int i = x;
        if ((x - y) == (X - Y)) {

            return true;

        } //////////////////////////////////////////////////////////////////////////////////////////
        else if ((x + y) == (X + Y)) {
            return true;

        } else {
            return false;
        }

    }

    public boolean Castle_Canmove(Point current) {

        int x = current.x;
        int y = current.y;
        if (((y == Y) && (x > (X) || (x < (X))))) {
            return true;
        } else if ((((y > Y) || (y < Y)) && (x == (X)))) {
            return true;
        } else {
            return false;
        }
    }

    public boolean Soilder_Canmove(String side, Point current, boolean movedbefore, int[][] board) {
        int x = current.x;
        int y = current.y;

        if ((side.equals("Black"))) {
            if ((((y - 1 == Y) && (x == (X))))) {
                if (board[x][y] == 0) {
                    return true;
                } else {
                    return false;
                }
            } else if ((((y - 2 == Y) && (x == (X)))) && !movedbefore) {
                if (board[x][y] == 0 && board[x][y - 1] == 0) {
                    return true;
                } else {

                    return false;
                }
            } else if ((y - 1 == Y && x + 1 == (X) || (y - 1 == Y && x - 1 == (X)))) {
                if ((y - 1) == Y && (x + 1) == X && board[x][y] >= 17 && board[x][y] <= 32) {
                    return true;
                } else if ((y - 1) == Y && (x - 1) == (X) && board[x][y] >= 17 && board[x][y] <= 33) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else if (side.equals("White")) {

            if (((y + 1 == Y) && (x == (X)))) {
                if (board[x][y] == 0) {
                    return true;
                } else {
                    return false;
                }
            } else if ((((y + 2 == Y) && (x == (X)))) && !movedbefore) {
                if (board[x][y] == 0 && board[x][y + 1] == 0) {
                    return true;
                } else {
                    return false;
                }
            } else if ((y + 1 == Y && x + 1 == (X) || (y + 1 == Y && x - 1 == (X)))) {
                if ((y + 1) == Y && (x + 1) == X && board[x][y] >= 1 && board[x][y] < 17) {
                    return true;
                } else if ((y + 1) == Y && (x - 1) == (X) && board[x][y] >= 1 && board[x][y] < 17) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return false;
    }

    public boolean Piece_Inthe_Way(Point current, int inHand, int[][] board) {
        int i = guti[inHand].getstate();
        String str = guti[inHand].getside();
        boolean movedbefore = guti[inHand].movedbefore();
        X = guti[inHand].returnX();
        Y = guti[inHand].returnY();
        switch (i) {
            case 1:
                return false;
            case 2:
                return false;
            case 3:
                return Elephant_PieceInMYway(current, board);
            case 4:
                return Castle_PieceInMYway(current, board);
            case 5:
                return Queen_PieceInMYway(current, board);
            case 6:
                return false;
        }
        return false;
    }

    public boolean Piece_already_there(Point current, int[][] board, int inHand) {
        String str = guti[inHand].getside();
        if (str.equals("White")) {
            if (board[current.x][current.y] >= 17 && board[current.x][current.y] < 33) {
                return true;
            }
            return false;
        } else {
            if (board[current.x][current.y] >= 1 && board[current.x][current.y] < 17) {
                return true;
            }
            return false;
        }
    }

    public boolean Castle_PieceInMYway(Point current, int[][] board) {
        int y = current.y;
        int x = current.x;
        int j = y;
        int i = x;
        if (((y == Y) && (x > (X) || (x < (X))))) {

            if ((X < i)) {

                while ((i != X + 1)) {
                    i--;
                    if (board[i][j] != 0)//there Same Color piece
                    {
                        return true;
                    }
                }
            } else if ((X > i)) {
                while ((i != X - 1)) {
                    i++;
                    if (board[i][j] != 0) {
                        return true;
                    }
                }
            }
        } else if ((((y > Y) || (y < Y)) && (x == (X)))) {
            if ((Y < j)) {
                while ((j != Y + 1)) {
                    j--;
                    if ((board[i][j] != 0)) {
                        return true;
                    }
                }
            } else if ((Y > j)) {
                while ((j != Y - 1)) {
                    j++;

                    if (board[i][j] != 0) {
                        return true;
                    }
                }

            }
        }
        return false;
    }

    public boolean Elephant_PieceInMYway(Point current, int[][] board) {
        int x = current.x;
        int y = current.y;
        int j = y;
        int i = x;
        if ((x - y) == (X - Y)) {
            if (x > X && y > Y) {
                while ((j != Y + 1) && (i != X + 1)) {
                    j--;
                    i--;
                    if (board[i][j] != 0) {
                        return true;
                    }
                }
            } else if (x < X && y < Y) {
                while ((j != Y - 1) && (i != X - 1)) {
                    j++;
                    i++;


                    if (board[i][j] != 0) {
                        return true;
                    }

                }
            }
        } else if (((x + y)) == ((X + Y))) {

            if ((X < i) && (Y > j)) {

                while (((j != Y - 1)) && ((i != X + 1))) {
                    j++;
                    i--;


                    if (board[i][j] != 0) {

                        return true;
                    }

                }

            } else if ((X > i) && (Y < j)) {
                while ((j != X + 1) && (i != X - 1)) {
                    j--;
                    i++;

                    if (board[i][j] != 0) {
                        return true;
                    }


                }
            }
        }
        return false;
    }

    public boolean Queen_PieceInMYway(Point current, int[][] board) {
        int x = current.x;
        int y = current.y;
        int j = y;
        int i = x;
        if (((y == Y) && (x > (X) || (x < (X))))) {
            if ((X < i)) {
                while ((i != X + 1)) {
                    i--;
                    if (board[i][j] != 0)//there Same Color piece
                    {
                        return true;
                    }
                }
            } else if ((X > i)) {
                while ((i != X - 1)) {
                    i++;
                    if (board[i][j] != 0) {
                        return true;
                    }
                }
            }
        } else if ((((y > Y) || (y < Y)) && (x == (X)))) {
            if ((Y < j)) {
                while ((j != Y + 1)) {
                    j--;
                    if (board[i][j] != 0) {
                        return true;
                    }
                }
            } else if ((Y > j)) {
                while ((j != Y - 1)) {
                    j++;

                    if (board[i][j] != 0) {
                        return true;
                    }
                }

            }
        } else if ((x - y) == (X - Y)) {
            if (x > X && y > Y) {
                while ((j != Y + 1) && (i != X + 1)) {
                    j--;
                    i--;
                    if (board[i][j] != 0) {
                        return true;
                    }
                }
            } else if (x < X && y < Y) {
                while ((j != Y - 1) && (i != X - 1)) {
                    j++;
                    i++;


                    if (board[i][j] != 0) {
                        return true;
                    }

                }
            }
        } else if ((x + y) == (X + Y)) {

            if ((X < i) && (Y > j)) {
                while ((j != Y - 1) && (i != X + 1)) {
                    j++;
                    i--;

                    if (board[i][j] != 0) {
                        return true;
                    }

                }

            } else if ((X > i) && (Y < j)) {
                while ((j != Y + 1) && (i != X - 1)) {
                    j--;
                    i++;

                    if (board[i][j] != 0) {
                        return true;
                    }
                }
            }

        }
        return false;
    }
    private boolean White_King_Check = false;
    private boolean Black_King_Check = false;

    public void White_checkKing(boolean newkingcheck) {
        White_King_Check = newkingcheck;
    }

    public boolean White_returncheckKing() {
        return White_King_Check;
    }

    public void Black_checkKing(boolean newkingcheck) {
        Black_King_Check = newkingcheck;
    }

    public boolean Black_returncheckKing() {
        return Black_King_Check;
    }

    public boolean White_King_is_Check(int[][] board) {
        Point My_King_Postion = guti[24].returnPostion();
        boolean flag = false;
        int i = 0;
        for (i = 1; i < 17; i++) {

            if (guti[i].returnX() != 13 && canmove(My_King_Postion, i, board) && !Piece_Inthe_Way(My_King_Postion, i, board)) {
                break;
            }
        }
        if (i == 17) {
            return false;
        }
        return true;
    }

    public boolean Black_King_is_Check(int[][] board) {
        Point My_King_Postion = guti[8].returnPostion();
        boolean flag = false;
        int i = 0;
        for (i = 17; i < 33; i++) {
            if (guti[i].returnX() != 13 && canmove(My_King_Postion, i, board) && !Piece_Inthe_Way(My_King_Postion, i, board)) {
                break;
            }
        }
        if (i == 33) {
            return false;
        }
        return true;
    }

    public void Set_status(int inHand) {
        if (inHand > 0 && inHand < 33) {
            guti[inHand].Set_move_status();
        }
    }

    public boolean White_Check_Mate_GameOver(int[][] board) {
        if (!King_generates_Move(24, board)) {
            System.out.println("King generates false.");
            return false;
        }
        for (int i = 17; i < 33; i++) {
            if (guti[i].getPixelX() == 13 || i == 24) {
                continue;
            } else {
                int state = guti[i].getstate();
                if (state == 1 && !Soilder_generates_Move(i, board)) {
                    System.out.println("Soilder generates false.");
                    return false;
                } else if (state == 2 && !Horse_generates_Move(i, board)) {
                    System.out.println("Horse generates false.");
                    return false;
                } else if (state == 3 && !Elephant_generates_Move(i, board)) {
                    System.out.println("Elephant generates false.");
                    return false;
                } else if (state == 4 && !Castel_generates_Move(i, board)) {
                    System.out.println("Castel generates false.");
                    return false;
                } else if (state == 5 && !Queen_generates_Move(i, board)) {
                    System.out.println("Queen generates false.");
                    return false;
                }
            }
        }

        return true;

    }

    public boolean Black_Check_Mate_GameOver(int[][] board) {
        if (!King_generates_Move(8, board)) {
            System.out.println("King generates false.");
            return false;
        }
        for (int i = 1; i < 17; i++) {
            if (guti[i].getPixelX() == 13 || i == 8) {
                continue;
            } else {
                int state = guti[i].getstate();

                if (state == 1 && !Soilder_generates_Move(i, board)) {
                    System.out.println("Soilder generates false.");
                    return false;
                } else if (state == 2 && !Horse_generates_Move(i, board)) {
                    System.out.println("Horse generates false.");
                    return false;
                } else if (state == 3 && !Elephant_generates_Move(i, board)) {
                    System.out.println("Elephant generates false.");
                    return false;
                } else if (state == 4 && !Castel_generates_Move(i, board)) {
                    System.out.println("Castel generates false.");
                    return false;
                } else if (state == 5 && !Queen_generates_Move(i, board)) {
                    System.out.println("Queen generates false.");
                    return false;
                }
            }

        }
        return true;

    }

    public boolean Piece_killed(int inHand, int[][] board, Point Position) {
        if (guti[inHand].getside().equals("White")) {
            if (board[Position.x][Position.y] >= 1 && board[Position.x][Position.y] <= 16) {
                return true;
            }
            return false;
        } else {
            if (board[Position.x][Position.y] >= 17 && board[Position.x][Position.y] <= 32) {
                return true;
            }
            return false;
        }
    }

    public boolean King_generates_Move(int inHand, int[][] board) {
        boolean somthing_killed = false;
        Point Oldp = new Point();

        Point PlaceCheck = new Point();


        int x = guti[inHand].returnX();
        int y = guti[inHand].returnY();
        int xx[] = {x + 1, x - 1, x, x, x + 1, x - 1, x + 1, x - 1};
        int yy[] = {y, y, y + 1, y - 1, y + 1, y - 1, y - 1, y + 1};
        Oldp.x = x;
        Oldp.y = y;
        int Piece = 0;
        //      System.out.println("old point: x- " + Oldp.x + "old point: y- " + Oldp.y + "  inHands:- " + inHand);
        for (int i = 0; i < 8; i++) {
            if (xx[i] <= 8 && xx[i] >= 1 && yy[i] <= 8 && yy[i] >= 1) {

                PlaceCheck.x = xx[i];
                PlaceCheck.y = yy[i];
                Piece = board[PlaceCheck.x][PlaceCheck.y];
                if (!Piece_already_there(PlaceCheck, board, inHand)) {
                    guti[inHand].setX(PlaceCheck.x);
                    guti[inHand].setY(PlaceCheck.y);
                    board[PlaceCheck.x][PlaceCheck.y] = inHand;
                    board[Oldp.x][Oldp.y] = 0;
                    if (Piece != 0) {
                        somthing_killed = true;
                        Killedpiec(Piece);
                    }
                    if (inHand > 16 && !White_King_is_Check(board)) {
                        guti[inHand].setPoint(Oldp);
                        board[Oldp.x][Oldp.y] = inHand;
                        board[PlaceCheck.x][PlaceCheck.y] = Piece;
                        if (somthing_killed) {
                            guti[Piece].setPoint(PlaceCheck);
                            somthing_killed = false;
                        }
                        return false;
                    } else if (inHand < 17 && !Black_King_is_Check(board)) {
                        guti[inHand].setPoint(Oldp);
                        board[Oldp.x][Oldp.y] = inHand;
                        board[PlaceCheck.x][PlaceCheck.y] = Piece;
                        if (somthing_killed) {
                            guti[Piece].setPoint(PlaceCheck);
                            somthing_killed = false;
                        }
                        return false;
                    }
                    guti[inHand].setPoint(Oldp);
                    board[Oldp.x][Oldp.y] = inHand;
                    board[PlaceCheck.x][PlaceCheck.y] = Piece;
                    if (somthing_killed) {
                        guti[Piece].setPoint(PlaceCheck);
                        somthing_killed = false;
                    }

                }

            }


        }
        return true;

    }

    public boolean Soilder_generates_Move(int inHand, int[][] board) {
        boolean somthing_killed = false;
        Point Oldp = new Point();

        Point PlaceCheck = new Point();


        int x = guti[inHand].returnX();
        int y = guti[inHand].returnY();
        int[] xx = {x, x, x + 1, x - 1, x, x, x + 1, x - 1};
        int[] yy = {y - 1, y - 2, y - 1, y - 1, y + 1, y + 2, y + 1, y + 1};
        Oldp.x = x;
        Oldp.y = y;
        int Piece = 0;
        //canmove(Point current, int inHand, int[][] board)
        if (x != 13) {
            for (int i = 0; i < 8; i++) {
                if (xx[i] <= 8 && xx[i] >= 1 && yy[i] <= 8 && yy[i] >= 1) {

                    PlaceCheck.x = xx[i];
                    PlaceCheck.y = yy[i];
                    Piece = board[PlaceCheck.x][PlaceCheck.y];
                    if (canmove(PlaceCheck, inHand, board)) {
                        board[PlaceCheck.x][PlaceCheck.y] = inHand;
                        board[Oldp.x][Oldp.y] = 0;
                        guti[inHand].setX(xx[i]);
                        guti[inHand].setY(yy[i]);
                        if (Piece != 0) {
                            somthing_killed = true;
                            Killedpiec(Piece);
                        }
                        if (inHand > 16 && !White_King_is_Check(board)) {
                            guti[inHand].setPoint(Oldp);
                            board[Oldp.x][Oldp.y] = inHand;
                            board[PlaceCheck.x][PlaceCheck.y] = Piece;
                            if (somthing_killed) {
                                guti[Piece].setPoint(PlaceCheck);
                                somthing_killed = false;
                            }
                            return false;
                        } else if (inHand < 17 && !Black_King_is_Check(board)) {
                            guti[inHand].setPoint(Oldp);
                            board[Oldp.x][Oldp.y] = inHand;
                            board[PlaceCheck.x][PlaceCheck.y] = Piece;
                            if (somthing_killed) {
                                guti[Piece].setPoint(PlaceCheck);
                                somthing_killed = false;
                            }
                            return false;
                        }

                    }
                    guti[inHand].setPoint(Oldp);
                    board[Oldp.x][Oldp.y] = inHand;
                    board[PlaceCheck.x][PlaceCheck.y] = Piece;
                    if (somthing_killed) {
                        guti[Piece].setPoint(PlaceCheck);
                        somthing_killed = false;
                    }
                }


            }
        }
        return true;
        // int [] xx ={x,x,x+1,x-1,x,x,x+1,x-1};
        // int [] yy = {y-1,y-2,y-1,y-1,y+1,y+2,y+1,y+1};
    }

    public boolean Horse_generates_Move(int inHand, int[][] board) {
        boolean somthing_killed = false;
        Point Oldp = new Point();

        Point PlaceCheck = new Point();


        int x = guti[inHand].returnX();
        int y = guti[inHand].returnY();
        int xx[] = {x + 1, x + 1, x + 2, x + 2, x - 1, x - 1, x - 2, x - 2};
        int yy[] = {y + 2, y - 2, y + 1, y - 1, y + 2, y - 2, y + 1, y - 1};
        Oldp.x = x;
        Oldp.y = y;
        int Piece = 0;
        if (x != 13) {
            for (int i = 0; i < 8; i++) {
                if (xx[i] <= 8 && xx[i] >= 1 && yy[i] <= 8 && yy[i] >= 1) {

                    PlaceCheck.x = xx[i];
                    PlaceCheck.y = yy[i];
                    Piece = board[xx[i]][yy[i]];
                    if (!Piece_already_there(PlaceCheck, board, inHand)) {
                        guti[inHand].setX(xx[i]);
                        guti[inHand].setY(yy[i]);
                        board[xx[i]][yy[i]] = inHand;
                        board[Oldp.x][Oldp.y] = 0;
                        if (Piece != 0) {
                            somthing_killed = true;
                            Killedpiec(Piece);
                        }
                        if (inHand > 16 && !White_King_is_Check(board)) {
                            guti[inHand].setPoint(Oldp);
                            board[Oldp.x][Oldp.y] = inHand;
                            board[xx[i]][yy[i]] = Piece;
                            if (somthing_killed) {
                                guti[Piece].setPoint(PlaceCheck);
                                somthing_killed = false;
                            }
                            return false;
                        } else if (inHand < 17 && !Black_King_is_Check(board)) {
                            guti[inHand].setPoint(Oldp);
                            board[Oldp.x][Oldp.y] = inHand;
                            board[xx[i]][yy[i]] = Piece;
                            if (somthing_killed) {
                                guti[Piece].setPoint(PlaceCheck);
                                somthing_killed = false;
                            }
                            return false;
                        }

                    }
                    guti[inHand].setPoint(Oldp);
                    board[Oldp.x][Oldp.y] = inHand;
                    board[xx[i]][yy[i]] = Piece;
                    if (somthing_killed) {
                        guti[Piece].setPoint(PlaceCheck);
                        somthing_killed = false;
                    }
                }


            }
        }
        return true;

    }

    public boolean Castel_generates_Move(int inHand, int[][] board) {
        boolean somthing_killed = false;
        Point Oldp = new Point();

        Point PlaceCheck = new Point();
        int x = guti[inHand].returnX();
        int y = guti[inHand].returnY();

        Oldp.x = x;
        Oldp.y = y;
        int Piece = 0;
        PlaceCheck.y = Oldp.y;
        if (y != 13) {
            for (int i = 1; i <= 8; i++) {
                PlaceCheck.x = i;

                Piece = board[PlaceCheck.x][PlaceCheck.y];
                if (!Piece_Inthe_Way(PlaceCheck, inHand, board) && !Piece_already_there(PlaceCheck, board, inHand)) {
                    board[PlaceCheck.x][PlaceCheck.y] = inHand;
                    board[Oldp.x][Oldp.y] = 0;
                    guti[inHand].setX(PlaceCheck.x);
                    guti[inHand].setY(PlaceCheck.y);
                    if (Piece != 0) {
                        somthing_killed = true;
                        Killedpiec(Piece);
                    }
                    if (inHand > 16 && !White_King_is_Check(board)) {
                        guti[inHand].setPoint(Oldp);
                        board[Oldp.x][Oldp.y] = inHand;
                        board[PlaceCheck.x][PlaceCheck.y] = Piece;
                        if (somthing_killed) {
                            guti[Piece].setPoint(PlaceCheck);
                            somthing_killed = false;
                        }
                        return false;
                    } else if (inHand < 17 && !Black_King_is_Check(board)) {
                        guti[inHand].setPoint(Oldp);
                        board[Oldp.x][Oldp.y] = inHand;
                        board[PlaceCheck.x][PlaceCheck.y] = Piece;
                        if (somthing_killed) {
                            guti[Piece].setPoint(PlaceCheck);
                            somthing_killed = false;
                        }
                        return false;
                    }


                }
                guti[inHand].setPoint(Oldp);
                board[Oldp.x][Oldp.y] = inHand;
                board[PlaceCheck.x][PlaceCheck.y] = Piece;
                if (somthing_killed) {
                    guti[Piece].setPoint(PlaceCheck);
                    somthing_killed = false;
                }

            }
        }
        PlaceCheck.x = Oldp.x;
        if (x != 13) {
            for (int i = 1; i <= 8; i++) {
                PlaceCheck.y = i;

                Piece = board[PlaceCheck.x][PlaceCheck.y];
                if (!Piece_Inthe_Way(PlaceCheck, inHand, board) && !Piece_already_there(PlaceCheck, board, inHand)) {
                    board[PlaceCheck.x][PlaceCheck.y] = inHand;
                    board[Oldp.x][Oldp.y] = 0;
                    guti[inHand].setX(PlaceCheck.x);
                    guti[inHand].setY(PlaceCheck.y);
                    if (Piece != 0) {
                        somthing_killed = true;
                        Killedpiec(Piece);
                    }
                    if (inHand > 16 && !White_King_is_Check(board)) {
                        guti[inHand].setPoint(Oldp);
                        board[Oldp.x][Oldp.y] = inHand;
                        board[PlaceCheck.x][PlaceCheck.y] = Piece;
                        if (somthing_killed) {
                            guti[Piece].setPoint(PlaceCheck);
                            somthing_killed = false;
                        }
                        return false;
                    } else if (inHand < 17 && !Black_King_is_Check(board)) {
                        guti[inHand].setPoint(Oldp);
                        board[Oldp.x][Oldp.y] = inHand;
                        board[PlaceCheck.x][PlaceCheck.y] = Piece;
                        if (somthing_killed) {
                            guti[Piece].setPoint(PlaceCheck);
                            somthing_killed = false;
                        }
                        return false;
                    }


                }
                guti[inHand].setPoint(Oldp);
                board[Oldp.x][Oldp.y] = inHand;
                board[PlaceCheck.x][PlaceCheck.y] = Piece;
                if (somthing_killed) {
                    guti[Piece].setPoint(PlaceCheck);
                    somthing_killed = false;
                }

            }
        }
        return true;

    }
    private int[][] point = new int[16][2];

    public int Elephant_Moves(Point P) {
        int i, j, x, y, count;
        count = 0;
        for (i = P.x - 1, j = P.y + 1; i >= 1 && j <= 8; i--, j++) {
            point[count][0] = i;
            point[count][1] = j;
            count++;

        }
        for (i = P.x + 1, j = P.y - 1; j >= 1 && i <= 8; i++, j--) {
            point[count][0] = i;
            point[count][1] = j;
            count++;

        }

        x = P.x;
        y = P.y;
        while (x > 1 && y > 1) {

            x--;
            y--;
            point[count][0] = x;
            point[count][1] = y;
            count++;
        }
        x = P.x;
        y = P.y;
        while (x < 8 && y < 8) {

            x++;
            y++;
            point[count][0] = x;
            point[count][1] = y;
            count++;
        }
        return count;

    }

    public boolean Elephant_generates_Move(int inHand, int[][] board) {
        boolean somthing_killed = false;
        Point Oldp = new Point();
        Point PlaceCheck = new Point();
        int x = guti[inHand].returnX();
        int y = guti[inHand].returnY();
        Oldp.x = x;
        Oldp.y = y;
        int Piece = 0;
        int count = 0;
        PlaceCheck.y = Oldp.y;
        PlaceCheck.x = Oldp.x;
        if (Oldp.x != 13) {
            count = Elephant_Moves(PlaceCheck);
        }
        for (int i = 0; i < count; i++) {
            PlaceCheck.x = point[i][0];
            PlaceCheck.y = point[i][1];

            Piece = board[PlaceCheck.x][PlaceCheck.y];
            if (!Piece_Inthe_Way(PlaceCheck, inHand, board) && !Piece_already_there(PlaceCheck, board, inHand)) {
                board[PlaceCheck.x][PlaceCheck.y] = inHand;
                board[Oldp.x][Oldp.y] = 0;
                guti[inHand].setX(PlaceCheck.x);
                guti[inHand].setY(PlaceCheck.y);
                if (Piece != 0) {
                    somthing_killed = true;
                    Killedpiec(Piece);
                }
                if (inHand > 16 && !White_King_is_Check(board)) {
                    guti[inHand].setPoint(Oldp);
                    board[Oldp.x][Oldp.y] = inHand;
                    board[PlaceCheck.x][PlaceCheck.y] = Piece;
                    if (somthing_killed) {
                        guti[Piece].setPoint(PlaceCheck);
                        somthing_killed = false;
                    }
                    return false;
                } else if (inHand < 17 && !Black_King_is_Check(board)) {
                    guti[inHand].setPoint(Oldp);
                    board[Oldp.x][Oldp.y] = inHand;
                    board[PlaceCheck.x][PlaceCheck.y] = Piece;
                    if (somthing_killed) {
                        guti[Piece].setPoint(PlaceCheck);
                        somthing_killed = false;
                    }
                    return false;
                }


            }
            guti[inHand].setPoint(Oldp);
            board[Oldp.x][Oldp.y] = inHand;
            board[PlaceCheck.x][PlaceCheck.y] = Piece;
            if (somthing_killed) {
                guti[Piece].setPoint(PlaceCheck);
                somthing_killed = false;
            }

        }

        return true;

    }

    public boolean Queen_generates_Move(int inHand, int[][] board) {
        if (!Elephant_generates_Move(inHand, board) || !Castel_generates_Move(inHand, board)) {
            return false;
        }
        return true;
    }
}
