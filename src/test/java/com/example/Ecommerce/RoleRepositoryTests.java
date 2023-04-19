package com.example.Ecommerce;

import com.example.Ecommerce.entity.Role;
import com.example.Ecommerce.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.LIST;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) //su dung csdl that de test
@Rollback(false)
public class RoleRepositoryTests {

    @Autowired
    private RoleRepository repo;

    @Test
    public void testCreateFirstRole(){
        Role role = new Role("ROLE_ADMIN", "Manage everything");
        Role savedRole= repo.save(role);
        assertThat(savedRole.getId()).isGreaterThan(0);

    }

    @Test
    public void testCreateRestRoles(){
        Role roleSalesPerson = new Role("ROLE_SELLER", "Manage product price, customers, shipping, orders and sales report");
        Role roleEditor = new Role("ROLE_EDITOR", "Manage categories, brands, products, articles, menus");
        Role roleShipper = new Role("ROLE_SHIPPER", "View products, view orders, update order status");
        Role roleAssistant = new Role("ROLE_ASSISTANT", "Manage Q&A");

        ArrayList<Role> listRoles = new ArrayList<>();
        listRoles.add(roleSalesPerson);
        listRoles.add(roleEditor);
        listRoles.add(roleShipper);
        listRoles.add(roleAssistant);

        repo.saveAll(listRoles);

    }

    @Test
    public void testFindByName(){
        Role role = repo.findByName("Editor");
        assertThat(role).isNotNull();
    }

}
