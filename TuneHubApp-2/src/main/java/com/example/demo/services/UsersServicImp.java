package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Users;
import com.example.demo.repositories.UsersRepository;

@Service
public class UsersServicImp implements UsersService {

	@Autowired
	UsersRepository usersRepo;

	@Override
	public String addUser(Users user) {
		usersRepo.save(user);
		return "User created";
	}

	@Override
	public boolean emailExists(String email) {
		if (usersRepo.findByEmail(email) == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean validateUser(String email, String password) {

		Users user = usersRepo.findByEmail(email);
		String db_password = user.getPassword();
		if (db_password.equals(password)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String getRole(String email) {
		return usersRepo.findByEmail(email).getRole();
		
	}

	@Override
	public Users getUser(String email) {
		return usersRepo.findByEmail(email);
	}

	@Override
	public void updateUser(Users user) {
		usersRepo.save(user);
	}

	@Override
	public String getName(String email) {
		return usersRepo.findByEmail(email).getUsername();
	}

	@Override
	public void updatePassword(Users user) {
		usersRepo.save(user);
		
	}


}
