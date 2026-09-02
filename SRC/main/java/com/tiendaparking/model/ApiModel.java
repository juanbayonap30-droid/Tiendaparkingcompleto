package com.tiendaparking.model;

public class ApiModel {
    private String clave;
    private String root;
    private String url;

    public ApiModel() {
    }

    public ApiModel(String clave, String root, String url) {
        this.clave = clave;
        this.root = root;
        this.url = url;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean validarConexion() {
        return "1234".equals(this.clave);
    }

    public String desconexion() {
        return "Desconexión exitosa";
    }

    public String buscarChofer(String infoCedula) {
        return "Buscando chofer con cédula: " + infoCedula;
    }

    @Override
    public String toString() {
        return "ApiModel [Clave: " + clave + ", Root: " + root + ", Url: " + url + "]";
    }
}
