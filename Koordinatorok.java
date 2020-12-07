/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KordiKereso;

/**
 *
 * @author Radagea
 */
public class Koordinatorok {
    private String név;
    private String település;
    private String tel;

    public Koordinatorok(String név,String település, String tel) {
        this.név = név;
        this.tel = tel;
        this.település=település;
    }

    public String getNév() {
        return név;
    }

    public String getTel() {
        return tel;
    }

    public String getTelepülés() {
        return település;
    }
    @Override
    public String toString() {
        return "Név:"+this.név+"\nTelefonszám:"+this.tel+""+this.település;
    }
    
    
}
