package com.proyect.products.entity;

import com.proyect.products.enums.Rol;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class Users {
    
    /**
     * Entidad que representa un usuario en el sistema. Contiene los campos necesarios para definir un usuario, como el ID del usuario, el nombre, el correo electrónico, la contraseña, el número de teléfono y el rol del usuario. Esta entidad se utiliza para mapear la información de los usuarios en la base de datos y para realizar operaciones relacionadas con los usuarios en el sistema.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long userId;

    
    @Column(name = "name")
    private String name;

    
    @Column(name = "email")
    private String email;

    
    @Column(name = "password")
    private String password;

    
    @Column(name = "phone")
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Rol rol;

}
