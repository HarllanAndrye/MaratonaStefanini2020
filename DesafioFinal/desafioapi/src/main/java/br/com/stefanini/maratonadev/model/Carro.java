package br.com.stefanini.maratonadev.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity
@Table(name = "carro")
public class Carro   extends PanacheEntityBase {
	
    @Id
    private String placa;

    @Column(name = "ano", nullable = false)
    private int ano;

    @Column(name = "modelo", length = 100, nullable = false)
    private String modelo;

    @Column(name = "marca", length = 100, nullable = false)
    private String marca;
    

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

}
