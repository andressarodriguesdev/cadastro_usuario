package com.andressadev.cadastro_usuario.infrastructure.repository;

import com.andressadev.cadastro_usuario.infrastructure.entity.Usuario;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByEmail(String email);



    @Modifying
    @Transactional
    @Query("DELETE FROM Usuario u WHERE u.email = :email")
    int deleteByEmail(@Param("email") String email);

}
