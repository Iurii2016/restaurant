package javaonline.service;//package javaonline.service;
//
//import javaonline.dao.IUserDao;
//import javaonline.dao.IUserRoleDao;
//import javaonline.dao.entity.User;
//import javaonline.dao.entity.UserRole;
//import netscape.security.Privilege;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.HashSet;
//import java.util.Set;
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    @Autowired
//    private IUserDao userService;
//
//    @Autowired
//    private IUserRoleDao userRoleDao;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userService.findByUserName(username);
//        UserDetails userDetails;
//        if (user != null){
//            Set<GrantedAuthority> roles = new HashSet<>();
//            for (UserRole r : user.getUserRole()){
//                roles.add(new SimpleGrantedAuthority(r.getRole()));
//            }
//               return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), roles);
//        } else throw new UsernameNotFoundException("User " + username + " not found!");
//    }
//
//}
