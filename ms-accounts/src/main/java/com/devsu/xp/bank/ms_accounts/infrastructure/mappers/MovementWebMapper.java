package com.devsu.xp.bank.ms_accounts.infrastructure.mappers;

import com.devsu.xp.bank.ms_accounts.domain.models.*;
import com.devsu.xp.bank.ms_accounts.web.dtos.requests.*;
import com.devsu.xp.bank.ms_accounts.web.dtos.responses.*;
import java.time.OffsetDateTime;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface MovementWebMapper {

  // ---------- Movements ----------
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "dateTime", ignore = true) // se setea en service
  @Mapping(target = "type", expression = "java(toMovementType(req.getType()))")
  @Mapping(target = "resultingBalance", ignore = true)
  Movement toDomain(MovementRequest req);

  @Mapping(target = "dateTime", expression = "java(src.getDateTime() != null ? src.getDateTime().toString() : null)")
  @Mapping(target = "type", expression = "java(fromMovementType(src.getType()))")
  MovementResponse toResponse(Movement src);

  // ---------- Helpers ----------

  default MovementType toMovementType(String v) {
    return v == null ? null : MovementType.valueOf(v.trim().toUpperCase());
  }

  default String fromMovementType(MovementType t) {
    return t == null ? null : t.name();
  }

  // Solo por si necesitas parsear fechas entrantes en otro endpoint
  default OffsetDateTime toOffsetDateTime(String iso) {
    return iso == null ? null : OffsetDateTime.parse(iso);
  }
}
