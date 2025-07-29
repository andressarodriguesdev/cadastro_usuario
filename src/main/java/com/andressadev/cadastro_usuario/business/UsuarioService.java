package com.andressadev.cadastro_usuario.business;

import com.andressadev.cadastro_usuario.infrastructure.entity.Usuario;
import com.andressadev.cadastro_usuario.infrastructure.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public void salvarUsuario(Usuario usuario){
        repository.saveAndFlush(usuario);
    }

    public Usuario buscarUsuarioEmail(String email){
     return  repository.findByEmail(email).orElseThrow(
             () -> new RuntimeException("Email não encontrado")
     );
    }

    @Transactional
    public boolean deletarUsuarioPorEmail(String email) {
        int deletados = repository.deleteByEmail(email);
        return deletados > 0;
    }

    public void atualizarUsuarioPorId(Integer id, Usuario usuario){
       Usuario usuarioEntity = repository.findById(id).orElseThrow(
               () -> new RuntimeException("Usuario não encontrado"));
       Usuario usuarioAtualizado = Usuario.builder()
               .email(usuario.getEmail() != null ?
                       usuario.getEmail() : usuarioEntity.getEmail())
               .nome(usuario.getNome() != null ?
                       usuario.getNome() : usuarioEntity.getNome())
               .id(usuarioEntity.getId())
               .build();
         repository.saveAndFlush(usuarioAtualizado);
    }


}
