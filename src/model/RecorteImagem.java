/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import marvin.gui.MarvinImagePanel;
import marvin.image.MarvinImage;

/**
 *
 * @author costa
 */
public class RecorteImagem extends JPanel implements MouseMotionListener, MouseListener, KeyListener{
    
    private Image foto_original;
    private BufferedImage imagem_em_memoria;
    private BufferedImage tmp_recorte;
    public MarvinImagePanel marvinPanel;
    
    
    private Graphics2D g2d;
    private boolean com_foto;
    
    private float clipX = 0;
    private float clipY = 0;
    private float clipAncho =200;
    private float clipAlto =200;
    
    private int Pos_Marca_new_X =0;
    private int Pos_Marca_new_Y =0;
    private int Pos_Marca_X =0;
    private int Pos_Marca_Y =0;
    private int Dist_X =0;
    private int Dist_Y =0;
    
    private Color color_linea = new Color(200,0,0);
    private float grosor_linea = 2f;
    
    public RecorteImagem(BufferedImage f, MarvinImagePanel panel){
        this.foto_original = f;
        this.setSize(f.getWidth(), f.getHeight());
        this.setVisible(true);
        this.com_foto = true;
        this.marvinPanel = panel;
        
        addMouseMotionListener(this);
        addMouseListener(this);
        addKeyListener(this);
        
    
    }
    
    
    public void setFotoOriginal(BufferedImage img){
        this.foto_original = img;
    }
    
    public void setClipAncho(float valor){
        this.clipAncho = valor;
    }
    
    public void setClipAlto(float valor){
        this.clipAlto = valor;
    }
    
    public float getClipAncho(){
        return this.clipAncho;
    }
    
    public float getClipAlto(){
        return this.clipAlto;
    }
    
    
    
    
    public RecorteImagem(){}
    
    protected void paintComponent(Graphics g){
    Graphics2D g2 = (Graphics2D) g;
     if(this.com_foto){
     imagem_em_memoria = new BufferedImage(this.getWidth(),this.getHeight(),BufferedImage.TYPE_INT_BGR);
     g2d = imagem_em_memoria.createGraphics();
     g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
     g2d.drawImage(foto_original, 0,0,foto_original.getWidth(this),foto_original.getHeight(this), this);
     g2d.setStroke(new BasicStroke(this.grosor_linea));
     g2d.setColor(color_linea);
     
     Rectangle2D r2 = new Rectangle2D.Float(clipX, clipY, clipAncho, clipAlto);
     g2d.draw(r2);
     g2.drawImage(imagem_em_memoria,0,0, this);
         
     }
    }
    
    public BufferedImage recortar(){
        tmp_recorte = ((BufferedImage) foto_original).getSubimage((int)clipX, (int)clipY, (int)clipAncho,(int) clipAlto);
        return tmp_recorte;
    }
    
    public void guardar_imagem(String f){
        recortar();
        try {
            ImageIO.write(tmp_recorte, "jpg", new File(f));
            JOptionPane.showMessageDialog(null, "o recorte foi guardado com sucesso");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ocorreu um erro ao tentar guardar o recorte");
        }
    }
    
   
    

    @Override
    public void mouseDragged(MouseEvent e) {
    Pos_Marca_new_X = (int)e.getPoint().getX();
    Pos_Marca_new_Y = (int)e.getPoint().getY();
    
    Dist_X = Pos_Marca_new_X - Pos_Marca_X;
    Dist_Y = Pos_Marca_new_Y - Pos_Marca_Y;
    
    clipX = clipX + Dist_X;
    clipY = clipY + Dist_Y;
    
    if(clipX<0) clipX = 0;
    if(clipY<0) clipY = 0;
    if((clipX + this.clipAncho) > this.getWidth()) 
        clipX = this.getWidth() - this.clipAncho;
    if((clipY + this.clipAlto) > this.getHeight()) 
        clipY = this.getHeight()- this.clipAlto;
    
    Pos_Marca_X = Pos_Marca_X + Dist_X;
    Pos_Marca_Y = Pos_Marca_Y + Dist_Y;
    this.repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount() == 2){
            this.marvinPanel.removeAll();
            this.marvinPanel.repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("Ola1");
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Ola2");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("Ola3");
    }
    
}
