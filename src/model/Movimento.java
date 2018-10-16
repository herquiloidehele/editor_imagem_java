/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author costa
 */
public class Movimento implements MouseListener, MouseMotionListener{
    
    
    private int X,Y;
    
    public Movimento(Component... pns){
    
        for(Component painel :pns){
        painel.addMouseListener(this);
        painel.addMouseMotionListener(this);
        
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent event) {
        X = event.getX();
        Y = event.getY();
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
    public void mouseDragged(MouseEvent event) {
        event.getComponent().setLocation((event.getX()+event.getComponent().getX())-X, (event.getY()+event.getComponent().getY())-Y);
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
    }
    
}
