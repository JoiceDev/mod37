package br.com.jg.repository;

import br.com.jg.model.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

@ApplicationScoped
public class UsuarioRepository implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @PersistenceContext
    private EntityManager em;
    
    public Usuario buscarPorId(Long id) {
        return em.find(Usuario.class, id);
    }
    
    public List<Usuario> listarTodos() {
        TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u", Usuario.class);
        return query.getResultList();
    }
    
    @Transactional
    public Usuario salvar(Usuario usuario) {
        if (usuario.getId() == null) {
            em.persist(usuario);
        } else {
            usuario = em.merge(usuario);
        }
        return usuario;
    }
    
    @Transactional
    public void remover(Usuario usuario) {
        usuario = buscarPorId(usuario.getId());
        em.remove(usuario);
    }
    
    public Usuario buscarPorEmail(String email) {
        TypedQuery<Usuario> query = em.createQuery(
            "SELECT u FROM Usuario u WHERE u.email = :email", Usuario.class);
        query.setParameter("email", email);
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }
}