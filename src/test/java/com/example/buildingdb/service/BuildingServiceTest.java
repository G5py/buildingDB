package com.example.buildingdb.service;

import com.example.buildingdb.dto.BuildingDto;
import com.example.buildingdb.entity.Architect;
import com.example.buildingdb.entity.Building;
import com.example.buildingdb.repository.ArchitectRepository;
import com.example.buildingdb.repository.BuildingRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.ReflectionTestUtils.invokeMethod;

class BuildingServiceTest {

    @Test
    void testValidateId_valid() {
        BuildingService buildingService = new BuildingService(null, null);
        invokeMethod(buildingService, "validateId", 1L);
    }

    @Test
    void testValidateArchitectName_valid() {
        BuildingService buildingService = new BuildingService(null, null);
        invokeMethod(buildingService, "validateArchitectName", "valid name");
    }

    @Test
    void testAddBuilding() {
        // given
        BuildingDto inputDto = BuildingDto.builder()
                .name("Church on the Water")
                .architectName("Ando Tadao")
                .build();

        Architect architect = new Architect("Ando Tadao");

        Building building = Building.builder()
                .id(1L)
                .name("Church on the Water")
                .architect(architect)
                .build();

        ArchitectRepository mockArchRepo = mock(ArchitectRepository.class);
        when(mockArchRepo.findByNameOrThrow("Ando Tadao"))
                .thenReturn(architect);

        BuildingRepository mockBuildingRepo = mock(BuildingRepository.class);
        when(mockBuildingRepo.save(any(Building.class)))
                .thenReturn(building);

        BuildingService buildingService = new BuildingService(mockArchRepo, mockBuildingRepo);


        // when
        BuildingDto actual = buildingService.addBuilding(inputDto);

        // then
        BuildingDto expected = BuildingDto.builder()
                .id(1L)
                .name("Church on the Water")
                .architectName("Ando Tadao")
                .build();

        assertThat(actual)
                .usingRecursiveComparison()
                .isEqualTo(expected);
    }

    @Test
    void getBuilding() {
    }

    @Test
    void putBuilding() {
    }

    @Test
    void deleteBuilding() {
    }
}