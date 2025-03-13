package org.fourstack.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorDetails implements Serializable {
  @Serial
  private static final long serialVersionUID = -1822621562717476581L;
  private LocalDateTime timestamp;
  private int statusCode;
  private HttpStatus status;
  private String errorMessage;
  private String uriPath;
}
