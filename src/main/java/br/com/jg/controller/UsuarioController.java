package br.com.jg.controller;

import br.com.jg.model.Usuario;
import br.com.jg.service.UsuarioService;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class UsuarioController implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Inject
    private UsuarioService service;
    
    private Usuario usuario;
    private List<Usuario> usuarios;
    
    @PostConstruct
    public void init() {
        this.usuario = new Usuario();
        this.usuarios = service.listarTodos();
    }
    
    public void salvar() {
        try {
            service.salvar(usuario);
            addMessage("Sucesso", "Usuário salvo com sucesso!");
            this.usuario = new Usuario();
            this.usuarios = service.listarTodos();
        } catch (Exception e) {
            addMessage("Erro", "Erro ao salvar: " + e.getMessage());
        }
    }
    
    public void excluir(Usuario usuario) {
        try {
            service.remover(usuario);
            addMessage("Sucesso", "Usuário excluído com sucesso!");
            this.usuarios = service.listarTodos();
        } catch (Exception e) {
            addMessage("Erro", "Erro ao excluir: " + e.getMessage());
        }
    }
    
    public void editar(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public void novoUsuario() {
        this.usuario = new Usuario();
    }
    
    private void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    // Getters e Setters
    public Usuario getUsuario() {
        return usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public List<Usuario> getUsuarios() {
        return usuarios;
    }
}