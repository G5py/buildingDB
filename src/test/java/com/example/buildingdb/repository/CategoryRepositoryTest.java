package com.example.buildingdb.repository;


import com.example.buildingdb.entity.Architect;
import com.example.buildingdb.entity.Building;
import com.example.buildingdb.entity.Category;
import com.example.buildingdb.entity.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository cr;

    @Autowired
    private BuildingRepository br;

    @Autowired
    private TagRepository tr;

    @Test
    public void testFindByTag() {
        // given
        Tag tag = new Tag("exposed concrete");

        Building b1 = Building.builder()
                .name("Church on the Water")
                .koreanName("물의 교회")
                .build();

        Building b2 = Building.builder()
                .name("Amorepacific headquarters")
                .koreanName("아모레퍼시픽 사옥")
                .build();

        Category category1 = new Category(b1, tag);
        Category category2 = new Category(b2, tag);

        tr.save(tag);

        br.save(b1);
        br.save(b2);

        cr.save(category1);
        cr.save(category2);


        // when
        List<Category> result = cr.findByTag(tag);

        // then
        List<Category> expected = new ArrayList<>();
        expected.add(category1);
        expected.add(category2);

        assertThat(result).isEqualTo(expected);
    }
}
