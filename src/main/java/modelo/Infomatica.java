package modelo;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "informatica")
public class Infomatica extends Produto{

    private String marca;
    private String modelo;

    public Infomatica() {
    }

    public Infomatica(String marca, String modelo) {
        this.marca = marca;
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }
}
