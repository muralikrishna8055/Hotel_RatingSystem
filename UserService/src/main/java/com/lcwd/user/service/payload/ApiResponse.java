package com.lcwd.user.service.payload;

import lombok.*;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ApiResponse {
    private String message;
    private Boolean success;
    private HttpStatus status;
}
