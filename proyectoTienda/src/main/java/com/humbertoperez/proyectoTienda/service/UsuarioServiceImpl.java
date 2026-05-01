package com.humbertoperez.proyectoTienda.service;

import com.humbertoperez.proyectoTienda.entity.Usuario;
import com.humbertoperez.proyectoTienda.exception.ResourceNotFoundException;
import com.humbertoperez.proyectoTienda.repository.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    private final UsuarioRepo usuarioRepo;

    public UsuarioServiceImpl(UsuarioRepo usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    @Override
    public List<Usuario> listar() {
        return usuarioRepo.findAll();
    }

    @Override
    public Usuario crear(Usuario usuario) {
        return usuarioRepo.save(usuario);
    }

    @Override
    public Usuario actualizar(Integer id, Usuario usuario) {
        Usuario existe = buscarPorId(id);

        usuario.setUsername(usuario.getUsername());
        usuario.setPassword(usuario.getPassword());
        usuario.setEmail(usuario.getEmail());
        usuario.setRol(usuario.getRol());
        usuario.setEstado(usuario.getEstado());

        return usuarioRepo.save(existe);
    }

    @Override
    public Usuario buscarPorId(Integer id) {
        return usuarioRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado por Id:" + id));
    }

    @Override
    public void eliminar(Integer id) {
        if (!usuarioRepo.existsById(id)) {
            throw new ResourceNotFoundException("Usuario no encontrado por id: " + id);
        }
        usuarioRepo.deleteById(id);
    }

}
