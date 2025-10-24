package com.devsu.xp.bank.shared_kernel.error;

import java.time.OffsetDateTime;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ApiError {
  String code;
  String message;
  OffsetDateTime timestamp;
  String path;
}
