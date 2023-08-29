package TechnoSale.Payloads;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    @Pattern(regexp = "^[+][9][9][8][0-9]{9}$",message = "Phone number must be 13 numbers.")
    private String phoneNumber;
    private String gmail;
    private String userName;
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-_]).{8,}$")
    private String password;
}
