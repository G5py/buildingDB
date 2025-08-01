package com.example.buildingdb.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;

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
        boolean actual = ReflectionTestUtils.invokeMethod(architectService, "isValidEnglishName", name);

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
        boolean actual = ReflectionTestUtils.invokeMethod(architectService, "isValidKoreanName", name);

        assertThat(actual).isEqualTo(expected);
    }
}