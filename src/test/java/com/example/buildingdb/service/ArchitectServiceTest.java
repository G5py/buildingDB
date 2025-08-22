package com.example.buildingdb.service;

import com.example.buildingdb.dto.ArchitectDto;
import com.example.buildingdb.entity.Architect;
import com.example.buildingdb.exception.InvalidDataException;
import com.example.buildingdb.repository.ArchitectRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.ReflectionTestUtils.invokeMethod;

class ArchitectServiceTest {


    @ParameterizedTest
    @CsvSource({
            "Sam Smith, true",
            "가asd sadff, false",
            "asd asdf힣, false",
            "asdㅇ asdf, false",
            "asdf-asdf, false"
    })
    void testIsValidEnglishName(String name, boolean expected) {
        ArchitectService architectService = new ArchitectService(null);
        boolean actual = invokeMethod(architectService, "isValidEnglishName", name);

        assertThat(actual).isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({
            "이상혁, true",
            "데이비드 치퍼필드, true",
            "자르반 4세, true",
            "Sam Smith, false",
            "ㅁ이상혁, false"
    })
    void testIsValidKoreanName(String name, boolean expected) {
        ArchitectService architectService = new ArchitectService(null);
        boolean actual = invokeMethod(architectService, "isValidKoreanName", name);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void testAddArchitectValid() {
        // given
        Architect architect = new Architect(1L, "Valid Name", "유효한 이름");

        ArchitectDto dto = new ArchitectDto(1L, "Valid Name", "유효한 이름");

        ArchitectRepository mockRepo = mock(ArchitectRepository.class);
        when(mockRepo.existsByName("Valid Name"))
                .thenReturn(false);

        when(mockRepo.save(any(Architect.class)))
                .thenReturn(architect);

        ArchitectService archService = new ArchitectService(mockRepo);

        // when
        ArchitectDto result = archService.addArchitect(dto);

        // then
        assertThat(result).isEqualTo(dto);
    }

    @ParameterizedTest
    @MethodSource("testAddArchitectInvalidSource")
    void testAddArchitectInvalid(ArchitectDto dto) {
        // given
        ArchitectRepository mockRepo = mock(ArchitectRepository.class);
        when(mockRepo.existsByName("Existing Name"))
                .thenReturn(true);
        when(mockRepo.existsByName("Not Existing Name"))
                .thenReturn(false);
        when(mockRepo.save(any(Architect.class)))
                .thenReturn(new Architect());

        ArchitectService archService = new ArchitectService(mockRepo);

        // when - then
        assertThrows(InvalidDataException.class, () -> archService.addArchitect(dto));
    }

    static Stream<ArchitectDto> testAddArchitectInvalidSource() {
        return Stream.of(
                new ArchitectDto(null, null, "유효한 이름"),                       // name이 null인 경우
                new ArchitectDto(null, "가asd sadff", "유효한 이름"),              // 유효하지 않은 name인 경우
                new ArchitectDto(null, "Not Existing Name", "d유효하지 않은 이름"), // 유효하지 않은 koreanName인 경우
                new ArchitectDto(null, "Existing Name", "유효한 이름"));           // 이미 존재하는 name인 경우
    }

    @Test
    void testGetArchitect() {
        // given
        Long id = 1L;
        Architect architect = new Architect("Louis Kahn");

        ArchitectRepository mockRepo = mock(ArchitectRepository.class);
        when(mockRepo.findByIdOrThrow(id))
                .thenReturn(architect);

        ArchitectService service = new ArchitectService(mockRepo);

        // when
        ArchitectDto actual = service.getArchitect(id);

        // then
        ArchitectDto expected = new ArchitectDto(architect);
        assertThat(actual).isEqualTo(expected);
    }
}