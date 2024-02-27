package edu.iescamp.venteoRestApi.users.repositories.dao;

import edu.iescamp.venteoRestApi.users.repositories.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query("SELECT u FROM User u WHERE u.userName = ?1 AND u.password = ?2")
    User findByUsernameAndPassword(String username, String password);

    @Query("SELECT u FROM User u WHERE u.roleId = ?1")
    List<User> findByRoleId(Long roleId);
}
