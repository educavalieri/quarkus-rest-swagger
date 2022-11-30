package acc.br.dtos;

import acc.br.entities.User;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Schema(name="UserDTO", description="Representacao do usuario a ser criada")
public class UserDto {

    @NotBlank
    @Schema(title = "Usuario", required = true)
    private String username;

    @NotBlank
    @Schema(title = "Senha", required = true)
    private String password;

    @Schema(title = "Papel do usuario, ADMIN ou USER. USER e default")
    private String role;

    @NotBlank
    @Schema(title="Nome do usuario", required = true)
    private String firstName;

    @NotBlank
    @Schema(title="Ultimo nome do usuario", required = true)
    private String lastName;

    @Min(value = 1, message = "Este valor tem que ser maior que 0")
    @Max(value = 200, message = "Este valor tem que ser menor que 200")
    @Schema(title="Idade do usuário deve estar entre 1 e 200", required = true)
    private int age;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public User toUser() {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);
//            user.setRole(StringUtils.isBlank(role) ? "USER" : StringUtils.upperCase(role));
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAge(age);
        return user;
    }
}