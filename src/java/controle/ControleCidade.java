/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import banco.DAOGenerico;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import modelo.EntidadeCidade;
import modelo.EntidadeEstado;

/**
 *
 * @author Admin
 */
@ManagedBean
@ViewScoped
public class ControleCidade implements Serializable{

    private EntidadeCidade objCidade = new EntidadeCidade();
    private List<EntidadeCidade> listaCidade = new ArrayList<>();
    private DAOGenerico dao = new DAOGenerico();

    private List<EntidadeEstado> listaEstados = new ArrayList<>();

    public ControleCidade() {
        preencherListaCidade();
        preencheListaEstados();
    }

    private void preencheListaEstados() {
        listaEstados = dao.lista(EntidadeEstado.class);
    }

    public void excluirCidade(EntidadeCidade est) {
        objCidade = est;
        if (objCidade.getId() != null) {
            try {
                dao.exluir(objCidade);
            } catch (Exception ex) {
                Logger.getLogger(ControleCidade.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        criarNovoObjetoCidade();
        preencherListaCidade();
    }

    public void criarNovoObjetoCidade() {
        objCidade = new EntidadeCidade();
    }

    public void inserirCidade() {
        if (objCidade.getId() == null) {
            dao.inserir(objCidade);
        } else {
            dao.salvar(objCidade);
        }
        preencherListaCidade();
        objCidade = new EntidadeCidade();
    }

    private void preencherListaCidade() {
        listaCidade = dao.lista(EntidadeCidade.class);
    }

    public List<EntidadeCidade> getListaCidade() {
        return listaCidade;
    }

    public void setListaCidade(List<EntidadeCidade> listaCidade) {
        this.listaCidade = listaCidade;
    }

    public EntidadeCidade getObjCidade() {
        return objCidade;
    }

    public void setObjCidade(EntidadeCidade objCidade) {
        this.objCidade = objCidade;
    }

    public List<EntidadeEstado> getListaEstados() {
        return listaEstados;
    }

    public void setListaEstados(List<EntidadeEstado> listaEstados) {
        this.listaEstados = listaEstados;
    }

}
