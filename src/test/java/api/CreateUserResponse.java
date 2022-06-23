package api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateUserResponse extends CreateUserBody{
    private Integer id;
    private String createdAt;
}
