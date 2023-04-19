package com.example.Ecommerce;

import com.example.Ecommerce.entity.Role;
import com.example.Ecommerce.entity.User;
import com.example.Ecommerce.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //su dung csdl that de test
@Rollback(false)
public class UserRepositoryTests {

    @Autowired
    private UserRepository repo;


    @Autowired
    private TestEntityManager entityManager;





    @Test
    public void testCreateUserAdmin(){
        Role admin = entityManager.find(Role.class, 1);
        User userHuy = new User("Huy","huy@gmail.com", "huy","Huy", "Quoc");
        //User userHuy = new User("huy@gmail.com", "huy","Huy", "Quoc");

        userHuy.addRole(admin);

        User savedUser = repo.save(userHuy);

        assertThat(savedUser.getId()).isGreaterThan(0);


    }

    @Test
    public void testCreateUserWithTwoRoles() {
        User userRavi = new User("Ravi","ravi@gmail.com", "ravi","Ravi", "Pham");

        Role roleEditor = new Role(3);

        Role roleAssistant = new Role(5);

        userRavi.addRole(roleEditor);

        userRavi.addRole(roleAssistant);

        User savedUser = repo.save(userRavi);

        assertThat(savedUser.getId()).isGreaterThan(0);


    }

    @Test
    public void testListAllUsers(){
        Iterable<User> listUsers = repo.findAll();
        listUsers.forEach(user -> System.out.println(user)) ;

    }

    @Test
    public void testUserById(){
        User userHuy = repo.findById(1).get();
        System.out.println(userHuy);
        assertThat(userHuy).isNotNull();
    }

    @Test
    public void testUserByEmail(){
        User userHuy = repo.findByEmail("phamquochuy3005@gmail.com").get();
        System.out.println(userHuy);
        assertThat(userHuy).isNotNull();
    }



    @Test
    public void testUpdateUserDetails(){
        User userHuy = repo.findById(1).get();
        userHuy.setEnabled(true);
        userHuy.setEmail("phamquochuy3005@gmail.com");

        repo.save(userHuy);
    }

    @Test
    public void testUpdateUserRoles(){
        User userRavi = repo.findById(2).get();
        Role roleEditor = new Role(3);
        Role roleSalesPerson = new Role(2);

        userRavi.getRoles().remove(roleEditor);
        userRavi.addRole(roleSalesPerson);

        repo.save(userRavi);
    }

    @Test
    public void testDeleteUser(){
        Integer userId = 2;
        repo.deleteById(userId);
    }










}
