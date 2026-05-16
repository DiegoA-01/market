package com.proyect.products.service;

import org.springframework.stereotype.Service;

import com.proyect.products.dto.ResponseDTO.MessageResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PermissionService {

    /**
     * Valida si el usuario tiene el rol ADMIN.
     *
     * @param rol Rol obtenido desde el token
     * 
     * @throws ResponseStatusException
     * Arroja mensaje cuando: El rol es nulo o el usuario no es ADMIN
     */
    public void checkAdmin(String rol) {
        if (rol == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, new MessageResponseDTO("Permiso denegado: rol no encontrado en el token").getMessage());
        }
    
        if (!"ADMIN".equalsIgnoreCase(rol)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, new MessageResponseDTO("Permiso denegado: se requiere rol ADMIN").getMessage());
        }
    }



    /**
     * Valida si el usuario tiene el rol CASHIER.
     *
     * @param rol Rol obtenido desde el token
     * 
     * @throws ResponseStatusException
     * Arroja mensaje cuando: El rol es nulo o el usuario no es CASHIER
     */
    public void checkCashier(String rol) {
        if (rol == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, new MessageResponseDTO("Permiso denegado: rol no encontrado en el token").getMessage());
        }

        if (!"CASHIER".equalsIgnoreCase(rol)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, new MessageResponseDTO("Permiso denegado: se requiere rol CASHIER").getMessage());
        }
    }

    /**
     * Valida si el usuario tiene rol ADMIN o CASHIER.
     *
     * @param rol Rol obtenido desde el token
     * 
     * @throws ResponseStatusException
     * Lanza mensaje cuando: El rol es nulo o el usuario no es ADMIN ni CASHIER
     */
    public void checkAdminOrCashier(String rol) {
        if (rol == null) {
            throw new ResponseStatusException(
                HttpStatus.FORBIDDEN,
                new MessageResponseDTO("Permiso denegado: rol no encontrado en el token").getMessage()
            );
        }

        if (!("ADMIN".equalsIgnoreCase(rol) || "CASHIER".equalsIgnoreCase(rol))) {
            throw new ResponseStatusException(
                HttpStatus.FORBIDDEN,
                new MessageResponseDTO("Permiso denegado: se requiere rol ADMIN o CASHIER").getMessage()
            );
        }
    }
}
