package br.com.jg.service;

import br.com.jg.model.Usuario;
import br.com.jg.repository.UsuarioRepository;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@ApplicationScoped
public class UsuarioService implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Inject
    private UsuarioRepository repository;
    
    public Usuario buscarPorId(Long id) {
        return repository.buscarPorId(id);
    }
    
    public List<Usuario> listarTodos() {
        return repository.listarTodos();
    }
    
    @Transactional
    public Usuario salvar(Usuario usuario) {
        // Aqui você pode adicionar regras de negócio antes de salvar
        return repository.salvar(usuario);
    }
    
    @Transactional
    public void remover(Usuario usuario) {
        repository.remover(usuario);
    }
    
    public Usuario autenticar(String email, String senha) {
        Usuario usuario = repository.buscarPorEmail(email);
        if (usuario != null && usuario.getSenha().equals(senha)) {
            return usuario;
        }
        return null;
    }
}