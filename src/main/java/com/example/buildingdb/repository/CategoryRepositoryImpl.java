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

    public Category findByBuildingIdAndTagId(Long buildingId, Long tagId) {
        QCategory category = QCategory.category;

        return queryFactory.selectFrom(category)
                .where(category.building.id.eq(buildingId)
                        .and(category.tag.id.eq(tagId)))
                .fetchFirst();
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
        QCategory category = QCategory.category;

        Category result = findByBuildingIdAndTagId(buildingId, tagId);

        return result != null;
    }
}
