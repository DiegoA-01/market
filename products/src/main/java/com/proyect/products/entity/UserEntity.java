package com.proyect.products.entity;

import org.springframework.data.annotation.Id;

import com.proyect.products.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class UserEntity {
  
  @Id
  @GeneratedValue
  @Column(name = "id_user")
  private Long userId;

  @NotBlank(message = "Name is required")
  @Size(max = 100)
  @Column(name = "name")
  private String name;

  @NotBlank(message = "Email is required")
  @Size(max = 100)
  @Column(name = "email")
  private String email;

  @NotBlank(message = "Password is required")
  @Size(max = 100)
  @Column(name = "password")
  private String password;

  @NotBlank(message = "Phone is required")
  @Size(max = 20)
  @Column(name = "phone")
  private String phone;

  @NotBlank(message = "Role is required")
  @Enumerated(EnumType.STRING)
  @Column(name = "role ")
  private Role role ;
}
