/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import marvin.image.MarvinImage;

/**
 *
 * @author herquiloide
 */
public class Rotacao {
    
    
    
    public static MarvinImage rotate90(MarvinImage imagem){
        
        MarvinImage imageOut = new MarvinImage(imagem.getHeight(), imagem.getWidth());
        
        for(int y = 0; y < imagem.getHeight(); y++){
            for(int x = 0; x < imagem.getWidth(); x++){
                int novoX = y;
                int novoY = (imagem.getWidth() -1) -x ;
                imageOut.setIntColor(novoX, novoY, imagem.getIntColor(x, y));
            }
        }
        
        return imageOut;
    }
    
    
    public static MarvinImage rotate180(MarvinImage image){
        MarvinImage imageOut = new MarvinImage(image.getWidth(), image.getHeight());
        for(int y=0; y<image.getHeight(); y++){
            for(int x=0; x<image.getWidth(); x++){
                int newX = (image.getWidth()-1)-x;
                int newY = (image.getHeight()-1)-y;
                imageOut.setIntColor(newX, newY, image.getIntColor(x, y));
            }
        }
        return imageOut;
    }
    
    
    public static MarvinImage rotate270(MarvinImage image){
        
        MarvinImage imageOut = new MarvinImage(image.getHeight(), image.getWidth());
        
        for(int y = 0; y < image.getHeight(); y++){
            for(int x = 0; x < image.getWidth(); x++){
                int newX = (image.getHeight() - 1) -y;
                int newY = x;
                imageOut.setIntColor(newX, newY, image.getIntColor(x, y));
            }
        }
        return imageOut;
    }
    
    
}