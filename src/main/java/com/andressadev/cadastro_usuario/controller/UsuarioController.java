package com.andressadev.cadastro_usuario.controller;

import com.andressadev.cadastro_usuario.business.UsuarioService;
import com.andressadev.cadastro_usuario.infrastructure.entity.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private  final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Void> salvarUsuario(@RequestBody Usuario usuario){
        usuarioService.salvarUsuario(usuario);
        return  ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Usuario> buscarUsuarioPorEmail(@RequestParam String email){
        return  ResponseEntity.ok(usuarioService.buscarUsuarioEmail(email));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarUsuarioPorEmail(@RequestParam String email) {
        boolean deletado = usuarioService.deletarUsuarioPorEmail(email);
        if (deletado) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping
    public  ResponseEntity<Void> atualizarUsuarioPorId(@RequestParam Integer id,
                                                       @RequestBody Usuario usuario ){
        usuarioService.atualizarUsuarioPorId(id, usuario);
        return  ResponseEntity.ok().build();

    }
}
