package com.example.buildingdb.service;

import com.example.buildingdb.dto.ArchitectDto;
import com.example.buildingdb.util.ValidationUtil;
import org.junit.jupiter.api.Test;

class ValidationUtilTest {

    @Test
    void testValidateArchitectDto() {
        // given
        ArchitectDto dto = new ArchitectDto(1L, "Valid Name", null);

        // when
        ValidationUtil.validateArchitectDto(dto);

        // then
        // it is successful if any exception didn't occur.
    }
}