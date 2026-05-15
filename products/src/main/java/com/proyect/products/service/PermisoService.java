package com.proyect.products.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.proyect.products.dto.ProductsResponseDTO.MessageResponseDTO;

@Service
public class PermisoService {

    public void checkAdmin(String rol) {
        if (rol == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, new MessageResponseDTO("Permiso denegado: rol no encontrado en el token").getMessage());
        }

        if (!"ADMIN".equalsIgnoreCase(rol)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, new MessageResponseDTO("Permiso denegado: se requiere rol ADMIN").getMessage());
        }
    }

    public void checkCashier(String rol) {
        if (rol == null) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, new MessageResponseDTO("Permiso denegado: rol no encontrado en el token").getMessage());
        }

        if (!"CASHIER".equalsIgnoreCase(rol)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, new MessageResponseDTO("Permiso denegado: se requiere rol CASHIER").getMessage());
        }
    }

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
