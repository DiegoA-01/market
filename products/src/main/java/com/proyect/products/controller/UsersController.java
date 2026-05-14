package com.proyect.products.controller;

import com.proyect.products.service.PermissionService;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyect.products.dto.RequestDTO.UsersRequestDTO;
import com.proyect.products.dto.ResponseDTO.DeleteUsersResponseDTO;
import com.proyect.products.dto.ResponseDTO.UsersResponseDTO;
import com.proyect.products.service.UsersService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {


    @Autowired
    UsersService usersService;
    private final PermissionService permissionService;



    @PostMapping("/secure")
public ResponseEntity<?> createUser(@Valid @RequestBody UsersRequestDTO request,
                                    HttpServletRequest httpServletRequest) {
    String rol = (String) httpServletRequest.getAttribute("rol");
    permissionService.checkAdmin(rol);

    usersService.createUser(request);
    return ResponseEntity.status(HttpStatus.CREATED)
                        .body(Map.of("message", "Usuario Creado exitosamente"));
}


    @GetMapping
    public List<UsersResponseDTO> listUsers(HttpServletRequest httpServletRequest){
        String rol = (String) httpServletRequest.getAttribute("rol");
        permissionService.checkAdminOrCashier(rol);
        return usersService.listUsers();
    }

    @GetMapping("/{userId}")
    public UsersResponseDTO showId(@Valid @PathVariable Long  userId,HttpServletRequest httpServletRequest){
        String rol = (String) httpServletRequest.getAttribute("rol");
        permissionService.checkAdminOrCashier(rol);

        return usersService.showId(userId);
    }

    @PutMapping("/{userId}")
    public ResponseEntity <?> updatedId(@PathVariable Long userId, @Valid @RequestBody UsersRequestDTO request,HttpServletRequest httpServletRequest){
        String rol = (String) httpServletRequest.getAttribute("rol");
        permissionService.checkAdmin(rol);
        UsersResponseDTO updated = usersService.updatedId(userId,request);
    return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteId(@PathVariable Long userId,@Valid @RequestBody UsersRequestDTO usersRequestDTO,HttpServletRequest httpServletRequest){
        String rol = (String) httpServletRequest.getAttribute("rol");
        permissionService.checkAdmin(rol);
        DeleteUsersResponseDTO deleted = usersService.deletedId(userId);
        return ResponseEntity.ok(deleted);
    }
}
