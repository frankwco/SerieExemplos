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
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import modelo.EntidadeCidade;
import modelo.EntidadeEstado;

/**
 *
 * @author frank
 */
@ManagedBean
@ViewScoped
public class ControleDropdown implements Serializable {

    private DAOGenerico dao;
    private List<EntidadeCidade> listaCidade;
    private List<EntidadeEstado> listaEstado;
    private EntidadeEstado estadoSelecionado;
    private EntidadeCidade cidadeSelecionada;

  
    public ControleDropdown() {
        dao = new DAOGenerico();
        listaCidade = new ArrayList<>();
        listaEstado = new ArrayList<>();

        preencherListaEstado();
    }

    private void preencherListaEstado() {
        listaEstado = dao.lista(EntidadeEstado.class);

    }

    /**
     * Este método irá filtrar as cidades de acordo com o estado escolhido*
     */
    public void filtrarCidades() {
        System.out.println("no método");
        if (estadoSelecionado != null && estadoSelecionado.getId() != null) {
            listaCidade = dao.listaCondicao(EntidadeCidade.class, "estado.id=" + estadoSelecionado.getId());

        }
    }

    public void mostrarCidadeEstadoSelecionados() {
        FacesMessage msg;
        if (estadoSelecionado != null && cidadeSelecionada != null) {
            msg = new FacesMessage("Selecionado" + cidadeSelecionada.getNome() + " do " + estadoSelecionado.getNome());
        } else {
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "A cidade não foi selecionada!!!");
        }

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public List<EntidadeCidade> getListaCidade() {
        return listaCidade;
    }

    public void setListaCidade(List<EntidadeCidade> listaCidade) {
        this.listaCidade = listaCidade;
    }

    public List<EntidadeEstado> getListaEstado() {
        return listaEstado;
    }

    public void setListaEstado(List<EntidadeEstado> listaEstado) {
        this.listaEstado = listaEstado;
    }

    public EntidadeEstado getEstadoSelecionado() {
        return estadoSelecionado;
    }

    public void setEstadoSelecionado(EntidadeEstado estadoSelecionado) {
        this.estadoSelecionado = estadoSelecionado;
    }

    public EntidadeCidade getCidadeSelecionada() {
        return cidadeSelecionada;
    }

    public void setCidadeSelecionada(EntidadeCidade cidadeSelecionada) {
        this.cidadeSelecionada = cidadeSelecionada;
    }

}
