package com.example.buildingdb.repository;

import com.example.buildingdb.entity.*;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryRepositoryImpl {
    private final JPAQueryFactory queryFactory;

    public CategoryRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    public boolean existsByBuildingAndTag(Building building, Tag tag) {
        QCategory category = QCategory.category;

        Category result = queryFactory.selectFrom(category)
                .where(category.building.eq(building)
                        .and(category.tag.eq(tag)))
                .fetchFirst();

        return result != null;
    }

    public boolean existsByBuildingIdAndTagId(Long buildingId, Long tagId) {
        QBuilding qBuilding = QBuilding.building;
        QTag qTag = QTag.tag;

        Building building = queryFactory.selectFrom(qBuilding)
                .where(qBuilding.id.eq(buildingId))
                .fetchFirst();

        Tag tag = queryFactory.selectFrom(qTag)
                .where(qTag.id.eq(tagId))
                .fetchFirst();

        return existsByBuildingAndTag(building, tag);
    }
}
