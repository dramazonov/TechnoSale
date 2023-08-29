package TechnoSale.Repositories;

import TechnoSale.Entities.Role;
import TechnoSale.Types.Enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    List<Role> findAllByRoleName(RoleName roleName);
}
