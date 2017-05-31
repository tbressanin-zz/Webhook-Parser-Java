/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapp;

/**
 *
 * @author thiago.bressanin
 */
public class Code {
    String code;
    int quantidade;

    public Code(String code, int quantidade) {
        this.code = code;
        this.quantidade = quantidade;
    }

    public String getCode() {
        return code;
    }

    public int getQuantidade() {
        return quantidade;
    }    
}
