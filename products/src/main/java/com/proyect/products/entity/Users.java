package com.proyect.products.entity;



import jakarta.persistence.Id;
import jakarta.persistence.Table;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import jakarta.persistence.EnumType;



import lombok.NoArgsConstructor;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long userId;

    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Size(max = 50)
    @Column(name = "name", unique = true)
    private String name;

    @NotBlank(message = "El correo electrónico es obligatorio")
    @Size(max = 100)
    @Column(name = "email", unique = true)
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(max = 100)
    @Column(name = "password")
    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

    @NotBlank(message = "El teléfono es obligatorio")
    @Size(max = 20)
    @Column(name = "phone", unique = true)
    private String phone;


    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Rol rol;


}
