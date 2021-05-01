package com.example.demo.systemUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SystemUserService {

	@Autowired
	SystemUserRepository systemUserRepositry;

	@Autowired
	SystemUserSpecifications systemUserSpecifications;

	public SystemUser save(SystemUser entity) {

		return this.systemUserRepositry.save(entity);
	}

	public List<SystemUser> findAll() {
		return systemUserRepositry.findAll(Sort.by("id"));
	}

	public Optional<SystemUser> findById(Long id) {

		return this.systemUserRepositry.findById(id);
	}

	public SystemUser findByLoginId(String loginid) {

		return this.systemUserRepositry.findByLoginidEquals(loginid);
	}

    public List<SystemUser> findUsers(SystemUserListForm systemUserListForm) {

    	List<SystemUser> list = new ArrayList<SystemUser>();

    	list = systemUserRepositry.findAll(Specification
    										.where(systemUserSpecifications.nameContains(systemUserListForm.getName()))
    										.and(systemUserSpecifications.roleContains(systemUserListForm.getRole())));
    	return list;
    }

}
