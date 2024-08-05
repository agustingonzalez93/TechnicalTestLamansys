package apiModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAndModifyUserResponse {
    private int code;
    private String type;
    private String message;
}
