package com.example.demo.systemUser;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public Page<SystemUser> findUsers(SystemUserListForm systemUserListForm, Pageable pageable) {

    	return systemUserRepositry.findAll(Specification
    										.where(systemUserSpecifications.nameContains(systemUserListForm.getName()))
    										.and(systemUserSpecifications.roleContains(systemUserListForm.getRole()))
    										,pageable);
    }

}
