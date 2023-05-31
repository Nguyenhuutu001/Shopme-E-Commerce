package com.shopme.admin.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import com.shopme.common.entity.Role;
import com.shopme.common.entity.User;

@DataJpaTest
@AutoConfigureTestDatabase(replace =  Replace.NONE)
@Rollback(false)
public class UserrepositoryTests {
		@Autowired
		private UserRepository repo;
		
		@Autowired
		private TestEntityManager entityManager;
		
		@Test
		public void testCreateNewUserWithOneRole() {
			Role roleAdmin = entityManager.find(Role.class, 1);
			User userTuNH = new User("nguyenhuutu001@gmail.com", "tu2023", "Tu", "Nguyen Huu");
			userTuNH.addRole(roleAdmin);
			
			User savedUser = repo.save(userTuNH);
			assertThat(savedUser.getId()).isGreaterThan(0);
		}
		
		@Test
		public void testCreateNewUserWithTwoRoles() {
			User userRavil = new User("revid@gmail.com", "revid2023", "Ravi", "Kumar");
			Role roleEditor = new Role(3);
			Role roleAssistant = new Role(5);
			
			userRavil.addRole(roleEditor);
			userRavil.addRole(roleAssistant);
			
			User savedUser = repo.save(userRavil);
			
			assertThat(savedUser.getId()).isGreaterThan(0);
		}
		
		@Test
		public void testListAllUsers() {
			Iterable<User> listUsers = repo.findAll();
			listUsers.forEach(user -> System.out.println(user));
			
		}
		
		@Test
		public void testGetUserById() {
			User userTu = repo.findById(1).get();
			System.out.println(userTu);
			assertThat(userTu).isNotNull();
		}
		
		@Test
		public void testUpdateUserDetails() {
			User userTu = repo.findById(1).get();
			userTu.setEnabled(true);
			userTu.setEmail("nguyenhuutu@gmail.com");
			
			repo.save(userTu);
			
		}
		
		@Test
		public void testUpdateUserRoles() {
			User userRavi = repo.findById(2).get();
			Role roleEditor = new Role(3);
			Role roleSalesperson = new Role(2);
			
			userRavi.getRoles().remove(roleEditor);
			userRavi.addRole(roleSalesperson);
			
			repo.save(userRavi);
		}
		
		@Test
		public void testDeleteUser() {
			Integer useId = 2;
			repo.deleteById(useId);
			
		}
		
		@Test
		public void testGetUserByEmail() {
			String email = "tupro@gmail.com";
			User user = repo.getUserByEmail(email);
			
			assertThat(user).isNotNull();
		}
		@Test
		public void testCountById() {
			Integer id = 1;
			Long countById = repo.countById(id);
			
			assertThat(countById).isNotNull().isGreaterThan(0);
		}
}