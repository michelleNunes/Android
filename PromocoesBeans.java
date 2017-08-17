package model.bean;

import java.io.Serializable;

/**
 * Created by Adriano Merodack on 22/10/2015.
 */
public class Promocoes implements Serializable {

    private Integer codigo;
    private String promocao;
    private String descricao;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    private String data;
    private byte[] foto;




    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {

        this.codigo = codigo;
    }

    public String getPromocao() {
        return promocao;
    }

    public void setPromocao(String promocao) {

        this.promocao = promocao;
    }

    public String getDescricao() {

        return descricao;
    }

    public void setDescricao(String descricao) {

        this.descricao = descricao;
    }

    public byte[] getFoto() {

        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
}
