package com.keroles.jobify.Repository;

import com.keroles.jobify.Model.Entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaRepo extends JpaRepository<Media,Long> {
}
