package com.example.buildingdb.controller;

import com.example.buildingdb.dto.ArchitectDto;
import com.example.buildingdb.exception.InvalidDataException;
import com.example.buildingdb.service.ArchitectService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ArchitectControllerTest {

    @Test
    void testPostArchitect() {
        try {
            // given
            ArchitectDto dto = new ArchitectDto(1L, "Tadao Ando", "안도 다다오");

            ArchitectService mockService = mock(ArchitectService.class);
            when(mockService.addArchitect(dto))
                    .thenReturn(dto);

            ArchitectController controller = new ArchitectController(mockService);

            // when
            ResponseEntity<ArchitectDto> actual = controller.postArchitect(dto);

            // then
            assertThat(actual.getBody()).isEqualTo(dto);
            assertThat(actual.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        } catch (InvalidDataException e) {
            fail("InvalidDataException occurred.");
        } catch (URISyntaxException e) {
            fail("URISyntaxException occurred.");
        }
    }

    @Test
    void testGetArchitect() {
        try {
            // given
            ArchitectDto expected = new ArchitectDto(1L, "Tadao Ando", "안도 다다오");

            ArchitectService mockService = mock(ArchitectService.class);
            when(mockService.getArchitect(1L))
                    .thenReturn(expected);

            ArchitectController controller = new ArchitectController(mockService);

            // when
            ArchitectDto actual = controller.getArchitect(1L);

            // then
            assertThat(actual).isEqualTo(expected);
        } catch (InvalidDataException e) {
            fail("Exception occurred.");
        }
    }

    @Test
    void testPutArchitect() {
        try {
            // given
            ArchitectDto dto = new ArchitectDto(1L, "Tadao Ando", "안도 다다오");

            ArchitectService mockService = mock(ArchitectService.class);
            when(mockService.putArchitect(1L, dto))
                    .thenReturn(dto);

            ArchitectController controller = new ArchitectController(mockService);

            // when
            ArchitectDto actual = controller.putArchitect(1L, dto);

            // then
            assertThat(actual).isEqualTo(dto);
        } catch (InvalidDataException e) {
            fail("Exception occurred.");
        }
    }

    @Test
    void testDeleteArchitect() {
        try {
            // given
            ArchitectDto expected = ArchitectDto.builder()
                    .id(1L)
                    .build();

            ArchitectService mockService = mock(ArchitectService.class);

            ArchitectController controller = new ArchitectController(mockService);

            // when
            ArchitectDto actual = controller.deleteArchitect(1L);

            // then
            assertThat(actual).isEqualTo(expected);
        } catch (InvalidDataException e) {
            fail("Exception occurred.");
        }
    }
}