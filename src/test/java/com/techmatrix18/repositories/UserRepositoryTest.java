package com.techmatrix18.repositories;

import com.techmatrix18.model.User;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    void whenFindByEmail_thenReturnUser() {
        // given
        User user = new User();
        user.setUsername("makklays");
        user.setEmail("makklays@techmatrix18.com");
        user.setMob("+380988705397");
        user.setAge("42");
        //user.setCreatedAt();
        entityManager.persist(user);
        entityManager.flush();

        // when
        Optional<User> found = userRepository.findByUsername("makklays");

        // then
        assertTrue(found.isPresent());
        assertEquals("Alexander Kuziv", found.get().getUsername());
    }

    @Test
    void whenFindByNonExistingEmail_thenReturnEmpty() {
        Optional<User> result = userRepository.findByUsername("notfound@techmatrix18.com");
        assertFalse(result.isPresent());
    }

    @Test
    void whenFindByMob_thenReturnCorrectUsers() {
        // given — create users with different phone mobile
        User admin = new User();
        admin.setEmail("admin@techmatrix18.com");
        admin.setMob("+380988705397");
        entityManager.persist(admin);

        User user = new User();
        user.setEmail("user@techmatrix18.com");
        user.setMob("+380981112233");
        entityManager.persist(user);

        entityManager.flush();

        // when — search all users with mobile "+380988705397"
        List<User> admins = userRepository.findByMob("+380988705397");

        // then — check, that find only one user, and this admin
        assertEquals(1, admins.size());
        assertEquals("admin@techmatrix18.com", admins.get(0).getEmail());
    }
}

