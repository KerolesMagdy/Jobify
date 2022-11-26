package com.keroles.jobify.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepo <Entity,ID> extends JpaRepository<Entity,ID> {
}
