package com.proyect.products.dto.ProductsResponseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MenssageResponseDTO {
    /**
     * DTO para la respuesta de mensajes generales. Contiene un mensaje que se utiliza para comunicar el resultado de una operación o para proporcionar información adicional al cliente. Este DTO se puede utilizar en diversas situaciones, como respuestas de éxito, errores o cualquier otro tipo de mensaje que se desee transmitir al cliente en el sistema.
     */
    private String menssage;
}
