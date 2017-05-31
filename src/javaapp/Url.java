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
public class Url {
    String url;
    int quantidade;

    public Url(String url, int quantidade) {
        this.url = url;
        this.quantidade = quantidade;
    }

    public String getUrl() {
        return url;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
