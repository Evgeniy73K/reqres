package api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserDataResponse {
    private Integer id;
    private String email;
    private String first_name;
    private String last_name;
    private String avatar;
}
