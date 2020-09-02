/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package retailsfinal1.models;

/**
 *
 * @author JESUS-CHRIST
 */
public class SiteConcerne {

    private int id;
    private String nomSite;
    private String bandePassanteSite;
    private String dateSite;
    private String heureSite;
    private String fichierSite;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomSite() {
        return nomSite;
    }

    public void setNomSite(String nomSite) {
        this.nomSite = nomSite;
    }

    public String getBandePassanteSite() {
        return bandePassanteSite;
    }

    public void setBandePassanteSite(String bandePassanteSite) {
        this.bandePassanteSite = bandePassanteSite;
    }

    public String getDateSite() {
        return dateSite;
    }

    public void setDateSite(String dateSite) {
        this.dateSite = dateSite;
    }

    public String getHeureSite() {
        return heureSite;
    }

    public void setHeureSite(String heureSite) {
        this.heureSite = heureSite;
    }

    public String getFichierSite() {
        return fichierSite;
    }

    public void setFichierSite(String fichierSite) {
        this.fichierSite = fichierSite;
    }

}
