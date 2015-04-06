package com;

import java.awt.Point;
import javax.swing.*;

public class PlayerCanMove {

    private Calculate_Moves CB;
    private short players_turn = 1;
    private boolean GameOver = false;
    private boolean Game_started = false;
    private final String KING_CHECK = "King is in Check";
    private final String ILLEGAL_MOVE = "Illegal Move";
    private final String GAME_OVER = "Game Over";
    public int board[][];
    private Point_flip_flop PF;   
    public PlayerCanMove() 
    {
        PF = new Point_flip_flop();
        CB = new Calculate_Moves();
        initializeBoard();
        players_turn = 1;
        GameOver = false;
        Game_started = true;       
    }
    public void init()
    {
        PF = new Point_flip_flop();
        CB = new Calculate_Moves();
        initializeBoard();
        players_turn = 1;
        GameOver = false;
        Game_started = true;
    }
    private void initializeBoard() {
        board = new int[9][9];
        for (int k = 1; k <= 8; k++) {
            for (int l = 1; l <= 8; l++) {
                board[k][l] = 0;
            }
        }
        int j = 1;
        int c = 25;
        for (int i = 0; i <= 7; i++, j++) {
            board[j][7] = c++;
        }
        board[8][8] = 17;
        board[1][8] = 18;
        board[2][8] = 19;
        board[7][8] = 20;
        board[3][8] = 21;
        board[6][8] = 22;
        board[4][8] = 23;
        board[5][8] = 24;
        j = 1;
        c = 9;
        for (int i = 0; i <= 7; i++, j++) {
            board[j][2] = c++;
        }
        board[1][1] = 1;
        board[8][1] = 2;
        board[2][1] = 3;
        board[7][1] = 4;
        board[3][1] = 5;
        board[6][1] = 6;
        board[4][1] = 7;
        board[5][1] = 8;
    }

    public String CanMove(int Crow, int Ccol, int Srow, int Scol) {
        String MOVE_STATUS = "";        
        int inhand = getSelectedPlayersInhandIndex(Srow, Scol);
        if(inhand==-1)
        {
            init();
            inhand = getSelectedPlayersInhandIndex(Srow, Scol);           
        }  
        if (players_turn == 1) {
            PF.White_SetInhand(inhand);
            ChangePlayersPixel(Crow, Ccol); 
            MOVE_STATUS = White_move(inhand);            
        } else if (players_turn == 2) {
            PF.Black_SetInhand(inhand);
            ChangePlayersPixel(Crow, Ccol); 
            MOVE_STATUS = Black_move(inhand);            
        }
        if(MOVE_STATUS.equals("SUCK"))return MOVE_STATUS;
        else UpdateStatus(MOVE_STATUS);
        return MOVE_STATUS;

    }
    public void UpdateStatus(String MOVE_STATUS)
    {
      
        if (MOVE_STATUS.equals("")) {
            
            if (players_turn == 1) {
                PF.White_SetInhand(-1);
                players_turn = 2;

            } else if (players_turn == 2) {
                PF.Black_SetInhand(-1);
                players_turn = 1;
            }          
            
        } else if (MOVE_STATUS.equals(ILLEGAL_MOVE)) {
            Point p = new Point();
            if (players_turn == 1) {
                p = CB.returnPostion(PF.White_GetInhand());
                CB.changePixel(p.x, p.y, PF.White_GetInhand());
            } else if (players_turn == 2) {
                p = CB.returnPostion(PF.Black_GetInhand());
                CB.changePixel(p.x, p.y, PF.Black_GetInhand());
            }
        } else if (MOVE_STATUS.equals(KING_CHECK)) {
            System.out.println(KING_CHECK);
            if (players_turn == 1) {
                PF.White_SetInhand(-1);
            } else if (players_turn == 2) {
                PF.Black_SetInhand(-1);
            }
        }
    }
    private void ChangePlayersPixel(int x, int y) {
        if (players_turn == 1 && PF.White_GetInhand() != -1) {
            CB.changePixel(x, y, PF.White_GetInhand());
        } else if (players_turn == 2 && PF.Black_GetInhand() != -1) {
            CB.changePixel(x, y, PF.Black_GetInhand());
        }
    }

    public int getSelectedPlayersInhandIndex(int x, int y) {
        int inhand = -1;
        if (players_turn == 1)//Player 1
        {
            for (int i = 17; i <= 32; i++) {
                Point p = CB.returnPostion(i);
                if (p.x == x && p.y == y) {
                    inhand = i;
                    break;
                }
            }
        } else if (players_turn == 2)//Player 2
        {
            for (int i = 1; i <= 16; i++) {
                Point p = CB.returnPostion(i);
                if (p.x == x && p.y == y) {
                    inhand = i;
                    break;
                }
            }
        }

        return inhand;
    }
    public String White_move(int inHand) {

        String MESSAGE = "";
        Point newP;

        newP = CB.getPixelPoint(inHand);

        Point present = CB.returnPostion(inHand);
        boolean kill = false;

        int killed = 0;
        if (!(newP.x == present.x && newP.y == present.y)) {
            if (inHand == 24 && (present.x - newP.x == 2 || present.x - newP.x == -2) && newP.y == present.y) {
                return King_can_Move(inHand, present, newP);
            }
            if (CB.canmove(newP, inHand, board) && !CB.Piece_Inthe_Way(newP, inHand, board) && !CB.Piece_already_there(newP, board, inHand)) {
                if (board[newP.x][newP.y] >= 1 && board[newP.x][newP.y] < 17) {
                    killed = board[newP.x][newP.y];
                    CB.Killedpiec(killed);
                    CB.changePostion(newP, inHand);
                    board[newP.x][newP.y] = inHand;
                    board[present.x][present.y] = 0;
                    kill = true;
                } else if (board[newP.x][newP.y] == 0) {
                    CB.changePostion(newP, inHand);
                    board[newP.x][newP.y] = inHand;
                    board[present.x][present.y] = 0;
                }
                CB.White_checkKing(false);
                if (CB.White_King_is_Check(board)) {
                    CB.White_checkKing(true);
                    if (kill) {
                        CB.changePostion(newP, killed);
                        board[newP.x][newP.y] = killed;
                        board[present.x][present.y] = inHand;
                        CB.changePostion(present, inHand);
                    } else {
                        board[newP.x][newP.y] = 0;
                        board[present.x][present.y] = inHand;
                        CB.changePostion(present, inHand);
                    }
                    MESSAGE = KING_CHECK;
                }
                else 
                {
                    CB.Set_status(inHand);
                    if (newP.y == 1 && inHand > 24 && inHand < 33 && CB.getstate(inHand) == 1) {
                        return "SUCK";
                    }
                    MESSAGE =  SetCheckmate(MESSAGE);
                }
            } else {
                MESSAGE = ILLEGAL_MOVE;
            }
        } else {
            MESSAGE = ILLEGAL_MOVE;
        }

        return MESSAGE;
}
public String changeImage(int code)
{
        String MESSAGE = "";
        int inHand;
        Point newP;        
        if(players_turn == 1){
            inHand = PF.White_GetInhand();
            newP = CB.getPixelPoint(inHand);
            CB.Set_status(inHand);
            CB.set_image(inHand, newP, 1, code);
        }
        else {
            inHand = PF.Black_GetInhand();
            newP = CB.getPixelPoint(inHand);
            CB.Set_status(inHand);
            CB.set_image(inHand, newP, 2, code);
        }
        MESSAGE = SetCheckmate(MESSAGE);
        UpdateStatus(MESSAGE);
        return MESSAGE;
}
public String SetCheckmate (String MESSAGE)
{
    if(players_turn == 1)
    {
         if (CB.Black_King_is_Check(board)) {
                        CB.Black_checkKing(true);
                        if (CB.Black_Check_Mate_GameOver(board)) {
                            GameOver();
                            MESSAGE = GAME_OVER;

                        }
                    }
    }
    else
    {
        if (CB.White_King_is_Check(board)) {
                        CB.White_checkKing(true);
                        if (CB.White_Check_Mate_GameOver(board)) {
                            GameOver();
                            MESSAGE = GAME_OVER;

                        }
                    }

    }
    return MESSAGE;
}
    public String Black_move(int inHand) {

        String MESSAGE = "";
        Point newP;

        newP = CB.getPixelPoint(inHand);

        Point present = CB.returnPostion(inHand);
        boolean kill = false;

        int killed = 0;
        if (!(newP.x == present.x && newP.y == present.y)) {
            if (inHand == 8 && (present.x - newP.x == 2 || present.x - newP.x == -2) && newP.y == present.y) {
                return King_can_Move(inHand, present, newP);
            }
            if (CB.canmove(newP, inHand, board) && !CB.Piece_Inthe_Way(newP, inHand, board) && !CB.Piece_already_there(newP, board, inHand)) {
                if (board[newP.x][newP.y] >= 17 && board[newP.x][newP.y] < 33) {
                    killed = board[newP.x][newP.y];
                    CB.Killedpiec(killed);
                    CB.changePostion(newP, inHand);
                    board[newP.x][newP.y] = inHand;
                    board[present.x][present.y] = 0;
                    kill = true;
                } else if (board[newP.x][newP.y] == 0) {
                    CB.changePostion(newP, inHand);
                    board[newP.x][newP.y] = inHand;
                    board[present.x][present.y] = 0;
                }
                CB.Black_checkKing(false);
                if (CB.Black_King_is_Check(board)) {
                    CB.Black_checkKing(true);
                    if (kill) {
                        CB.changePostion(newP, killed);
                        board[newP.x][newP.y] = killed;
                        board[present.x][present.y] = inHand;
                        CB.changePostion(present, inHand);
                    } else {
                        board[newP.x][newP.y] = 0;
                        board[present.x][present.y] = inHand;
                        CB.changePostion(present, inHand);
                    }
                    MESSAGE = KING_CHECK;
                } else {
                    CB.Set_status(inHand);
                    if (newP.y == 8 && inHand > 8 && inHand < 17 && CB.getstate(inHand) == 1) {
                        return "SUCK";
                    }
                    MESSAGE =  SetCheckmate(MESSAGE);
                    return MESSAGE;
                }
            } else {
                MESSAGE = ILLEGAL_MOVE;
            }
        } else {
            MESSAGE = ILLEGAL_MOVE;
        }

        return MESSAGE;
    }
private String King_can_Move(int inHand, Point old, Point newp) {
        String MESSAGE = "";
        Point Piece = new Point(-1, -1);
        if (inHand == 24 && !CB.has_moved(inHand) && newp.y == 8) {
            if (newp.x - old.x == -2) {
                if (board[1][newp.y] == 18 && !CB.has_moved(18) && board[2][newp.y] == 0 && board[3][newp.y] == 0 && board[4][newp.y] == 0) {
                    Piece.x = newp.x + 1;
                    Piece.y = newp.y;
                    CB.changePostion(newp, inHand);
                    CB.changePostion(Piece, 18);
                    board[1][newp.y] = 0;
                    board[old.x][old.y] = 0;
                    board[newp.x][newp.y] = inHand;
                    board[Piece.x][Piece.y] = 18;
                    if (CB.White_King_is_Check(board)) {
                        CB.changePostion(old, inHand);
                        CB.changePostion(new Point(1, 8), 18);
                        board[1][newp.y] = 18;
                        board[old.x][old.y] = inHand;
                        board[newp.x][newp.y] = 0;
                        board[Piece.x][Piece.y] = 0;
                        return KING_CHECK;
                    } else {
                        CB.Set_status(18);
                        CB.Set_status(inHand);
                        if (CB.Black_King_is_Check(board)) {
                            CB.Black_checkKing(true);
                            if (CB.Black_Check_Mate_GameOver(board)) {
                                GameOver();
                                MESSAGE = GAME_OVER;

                            }
                        }

                        return MESSAGE;

                    }
                }
                return ILLEGAL_MOVE;

            } else if (newp.x - old.x == 2) {
                if (board[8][newp.y] == 17 && !CB.has_moved(17) && board[6][newp.y] == 0 && board[7][newp.y] == 0) {
                    Piece.x = newp.x - 1;
                    Piece.y = newp.y;
                    CB.changePostion(newp, inHand);
                    CB.changePostion(Piece, 17);
                    board[1][newp.y] = 0;
                    board[old.x][old.y] = 0;
                    board[newp.x][newp.y] = inHand;
                    board[Piece.x][Piece.y] = 17;
                    if (CB.White_King_is_Check(board)) {
                        CB.changePostion(old, inHand);
                        CB.changePostion(new Point(1, 8), 17);
                        board[1][newp.y] = 17;
                        board[old.x][old.y] = inHand;
                        board[newp.x][newp.y] = 0;
                        board[Piece.x][Piece.y] = 0;
                        return KING_CHECK;
                    } else {
                        CB.Set_status(17);
                        CB.Set_status(inHand);
                        if (CB.White_King_is_Check(board)) {
                            CB.White_checkKing(true);
                            if (CB.White_Check_Mate_GameOver(board)) {
                                GameOver();
                                MESSAGE = GAME_OVER;

                            }
                        }
                        return MESSAGE;
                    }
                }
                return ILLEGAL_MOVE;
            }
            return ILLEGAL_MOVE;
        } else if (inHand == 8 && !CB.has_moved(inHand) && old.y == 1) {
            if (newp.x - old.x == -2) {
                if (board[1][newp.y] == 1 && !CB.has_moved(1) && board[2][newp.y] == 0 && board[3][newp.y] == 0 && board[4][newp.y] == 0) {
                    Piece.x = newp.x + 1;
                    Piece.y = newp.y;
                    CB.changePostion(newp, inHand);
                    CB.changePostion(Piece, 1);
                    board[1][newp.y] = 0;
                    board[old.x][old.y] = 0;
                    board[newp.x][newp.y] = inHand;
                    board[Piece.x][Piece.y] = 1;
                    if (CB.Black_King_is_Check(board)) {
                        CB.changePostion(old, inHand);
                        CB.changePostion(new Point(1, 1), 1);
                        board[1][newp.y] = 1;
                        board[old.x][old.y] = inHand;
                        board[newp.x][newp.y] = 0;
                        board[Piece.x][Piece.y] = 0;
                        return KING_CHECK;
                    } else {
                        CB.Set_status(1);
                        CB.Set_status(inHand);
                        if (CB.White_King_is_Check(board)) {
                            CB.White_checkKing(true);
                            if (CB.White_Check_Mate_GameOver(board)) {
                                GameOver();
                                MESSAGE = GAME_OVER;

                            }
                        }
                        return MESSAGE;
                    }
                }
                return ILLEGAL_MOVE;

            } else if (newp.x - old.x == 2) {
                if (board[8][newp.y] == 2 && !CB.has_moved(2) && board[6][newp.y] == 0 && board[7][newp.y] == 0) {
                    Piece.x = newp.x - 1;
                    Piece.y = newp.y;
                    CB.changePostion(newp, inHand);
                    CB.changePostion(Piece, 2);
                    board[1][newp.y] = 0;
                    board[old.x][old.y] = 0;
                    board[newp.x][newp.y] = inHand;
                    board[Piece.x][Piece.y] = 2;
                    if (CB.Black_King_is_Check(board)) {
                        CB.changePostion(old, inHand);
                        CB.changePostion(new Point(8, 1), 2);
                        board[1][newp.y] = 2;
                        board[old.x][old.y] = inHand;
                        board[newp.x][newp.y] = 0;
                        board[Piece.x][Piece.y] = 0;
                        return KING_CHECK;
                    } else {
                        CB.Set_status(2);
                        CB.Set_status(inHand);
                        if (CB.White_King_is_Check(board)) {
                            CB.White_checkKing(true);
                            if (CB.White_Check_Mate_GameOver(board)) {
                                GameOver();
                                MESSAGE = GAME_OVER;

                            }
                        }
                        return MESSAGE;
                    }
                }
                return ILLEGAL_MOVE;
            }
            return ILLEGAL_MOVE;
        }
        return ILLEGAL_MOVE;
    }
    private void GameOver() {
        GameOver = true;
    }
}

class Point_flip_flop {

    private int inHand = -1;
    private int InHand = -1;

    public void Black_SetInhand(int i) {
        inHand = i;
    }

    public int Black_GetInhand() {
        return inHand;
    }

    public void White_SetInhand(int i) {
        InHand = i;
    }

    public int White_GetInhand() {
        return InHand;
    }
}