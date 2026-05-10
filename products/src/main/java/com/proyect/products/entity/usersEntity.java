package com.proyect.products.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

public class usersEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private long userId;

  @NotBlank(message = "Name is required")
  @Size(max = 100)
  @Column(name ="name")
  private String name;

  @NotBlank(message = "This field is required")
  @Size(max = 100)
  @Column(name = "username")
  private String userName;

  @NotBlank(message = "Password is required")
  @Size(max = 100)
  @Column(name = "password")
  private String password;

  @NotBlank(message = "Rol is required")
  @Column(name = "rol")
  private String rol;

}
