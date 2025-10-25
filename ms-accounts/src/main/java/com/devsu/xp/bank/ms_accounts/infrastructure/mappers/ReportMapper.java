package com.devsu.xp.bank.ms_accounts.infrastructure.mappers;

import com.devsu.xp.bank.ms_accounts.domain.models.ReportItem;
import com.devsu.xp.bank.ms_accounts.web.dtos.responses.ReportResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ReportMapper {
  ReportResponse toResponse(ReportItem item);
}
