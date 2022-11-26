package com.keroles.jobify.Repository;

import com.keroles.jobify.Model.Entity.SocialLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialLinkRepo extends JpaRepository<SocialLink,Long> {
}
