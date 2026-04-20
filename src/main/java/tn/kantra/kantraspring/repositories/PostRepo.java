package tn.kantra.kantraspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.kantra.kantraspring.entities.PostEntity;

@Repository
public interface PostRepo extends JpaRepository<PostEntity,Long> {
}
