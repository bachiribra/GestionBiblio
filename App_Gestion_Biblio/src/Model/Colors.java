/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.Color;

/**
 *
 * @author Ali Abdoulkader Ali
 */
public class Colors {
        
    public static final Colors INSTANCE = new Colors();
    
    public Color primaryColor(){
        return new Color(37, 59, 55);
    }
    
    public Color secondaryColor(){
        return new Color(41, 94, 81);
    }
    
    public Color textColor(){
        return Color.WHITE;
    }
    
    public Color CopyrightTextColor(){
        return Color.ORANGE;
    }
    
        public Color menuColor(){
        return new Color(47,58,71);
    }
}
