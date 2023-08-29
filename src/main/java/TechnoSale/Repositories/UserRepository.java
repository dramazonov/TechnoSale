package TechnoSale.Repositories;

import TechnoSale.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    @Query(value = "select *\n"+
            "from users u\n"+
            "join user_role ur on u.id = ur.user_id\n"+
            "join role r on r.id = ur.role_id\n"+
            " where u.user_name=:userName and(r.role_name<>'ROLE_CLIENT'and role_name<>'ROLE_SUPPLIER' and role_name<>'ROLE_MODERATOR')",nativeQuery = true)
    Optional<User> findAllByUserNameAndRolesNotIn(@Param("userName")String userName);
}
