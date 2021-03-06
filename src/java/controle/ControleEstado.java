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
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import modelo.EntidadeEstado;
import util.DatabaseException;
import util.LastException;

/**
 *
 * @author Admin
 */
@ManagedBean
@SessionScoped
public class ControleEstado implements Serializable {

    private EntidadeEstado objEstado;
    private List<EntidadeEstado> listaEstado;
    private DAOGenerico dao;

    public ControleEstado() {
        objEstado = new EntidadeEstado();
        listaEstado = new ArrayList<>();
        dao = new DAOGenerico();
        preencherListaEstado();
    }
    

    public void excluirEstado(EntidadeEstado est) {
        objEstado = est;
        if (objEstado.getId() != null) {
            try {
                dao.exluir(objEstado);
            } catch (Exception ex) {
                LastException lastException = new LastException();
                Throwable th = lastException.findLastException(ex);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro!!", new DatabaseException((Exception) th).getMessage()));
            }
        }
        criarNovoObjetoEstado();
        preencherListaEstado();
    }

    public void criarNovoObjetoEstado() {
        objEstado = new EntidadeEstado();
    }

    public void inserirEstado() {
        if (objEstado.getId() == null) {
            try {
                dao.inserir(objEstado);
            } catch (Exception ex) {
                LastException lastException = new LastException();
                Throwable th = lastException.findLastException(ex);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro!!", new DatabaseException((Exception) th).getMessage()));

            }

        } else {
            dao.salvar(objEstado);
        }
        preencherListaEstado();
        objEstado = new EntidadeEstado();
    }

    private void preencherListaEstado() {
        listaEstado = new ArrayList<>();
        listaEstado = dao.lista(EntidadeEstado.class);
    }

    //Criar get e set do obj e da lista
    public EntidadeEstado getObjEstado() {
        return objEstado;
    }

    public void setObjEstado(EntidadeEstado objEstado) {
        this.objEstado = objEstado;
    }

    public List<EntidadeEstado> getListaEstado() {
        return listaEstado;
    }

    public void setListaEstado(List<EntidadeEstado> listaEstado) {
        this.listaEstado = listaEstado;
    }

}
