package TechnoSale.Services;

import TechnoSale.Entities.User;
import TechnoSale.Payloads.RegisterDTO;
import TechnoSale.Payloads.Result;
import TechnoSale.Repositories.RoleRepository;
import TechnoSale.Repositories.UserRepository;
import TechnoSale.Types.Enums.RoleName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return userRepository.findAllByUserNameAndRolesNotIn(userName).orElseThrow(() -> new UsernameNotFoundException(userName + " doesn't find"));
    }

    public Result register(RegisterDTO registerDTO){
        User user = new User();
        user.setFirstName(registerDTO.getFirstName());
        user.setLastName(registerDTO.getLastName());
        user.setGmail(registerDTO.getGmail());
        user.setPhoneNumber(registerDTO.getPhoneNumber());
        user.setUserName(registerDTO.getUserName());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAllByRoleName(RoleName.ROLE_ADMIN)));
        user.setCreatedDate(LocalDateTime.now());
        userRepository.save(user);
        return new Result("Success",true);
    }
}
