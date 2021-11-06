package com.example.demo.login;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.systemUser.SystemUser;
import com.example.demo.systemUser.SystemUserDetails;
import com.example.demo.systemUser.SystemUserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private SystemUserRepository systemUserRepository;

    @Override
    public UserDetails loadUserByUsername(String loginid) throws UsernameNotFoundException {

        SystemUser systemUser = systemUserRepository.findByLoginid(loginid);

        if (systemUser == null) {
            throw new UsernameNotFoundException("User" + loginid + "was not found in the database");
        }
        //権限のリスト
        //AdminやUserなどが存在するが、今回は利用しないのでUSERのみを仮で設定
        //権限を利用する場合は、DB上で権限テーブル、ユーザ権限テーブルを作成し管理が必要
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        GrantedAuthority authority = new SimpleGrantedAuthority("USER");
        grantList.add(authority);

        //rawDataのパスワードは渡すことができないので、暗号化
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        systemUser.setPassword(encoder.encode(systemUser.getPassword()));

        var systemUserDetails = new SystemUserDetails(systemUser);

        return systemUserDetails;
    }

}
