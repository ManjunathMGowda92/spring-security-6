package org.fourstack.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorDetails {
  private LocalDateTime timestamp;
  private int statusCode;
  private HttpStatus status;
  private String errorMessage;
  private String uriPath;
}
