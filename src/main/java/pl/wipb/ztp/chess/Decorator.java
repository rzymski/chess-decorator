package pl.wipb.ztp.chess;

import java.awt.*;
import java.awt.geom.AffineTransform;

import static pl.wipb.ztp.chess.Chessboard.ZEROX;
import static pl.wipb.ztp.chess.Chessboard.ZEROY;
import static pl.wipb.ztp.chess.Piece.TILESIZE;

interface PieceInterface{
    void draw(Graphics2D g);
    int getX();
    int getY();
    void moveTo(int xx, int yy);
    PieceInterface undecorate();
}
abstract class AbstractDecorator implements  PieceInterface
{
    protected PieceInterface pieceInterface;
    public AbstractDecorator(PieceInterface pieceInterface) { this.pieceInterface = pieceInterface; }
    public void draw(Graphics2D g) { pieceInterface.draw(g); }
    public int getX() { return pieceInterface.getX(); }
    public int getY() { return pieceInterface.getY(); }
    public void moveTo(int xx, int yy) { pieceInterface.moveTo(xx, yy); }
}

class Decorator extends AbstractDecorator
{
    public Decorator(PieceInterface pieceInterface) { super(pieceInterface); }
    public void draw(Graphics2D g) {
        AffineTransform before = g.getTransform();
        AffineTransform tr=new AffineTransform();
        tr.translate(ZEROX,ZEROY);
        tr.scale(TILESIZE, TILESIZE);
        g.transform(tr);
        super.draw(g);
        g.setTransform(before);
    }
    public PieceInterface undecorate() { return pieceInterface; }
}

class Decorator2 extends AbstractDecorator
{
    private AffineTransform affineTransform;
    public Decorator2(PieceInterface pieceInterface, AffineTransform af){
        super(pieceInterface);
        affineTransform = af;
    }
    public void draw(Graphics2D g) {
        AffineTransform before = g.getTransform();
        g.transform(affineTransform);
        this.pieceInterface.draw(g);
        g.setTransform(before);
    }
    public PieceInterface undecorate() { return pieceInterface; }
}