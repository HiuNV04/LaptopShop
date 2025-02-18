 
package model;

/**
 *
 * @author Admin
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Brand {

    private int id;
    private String name;
    
    //constructor for insert a new brand
    public Brand(String name) {
        this.name = name;
    }

}
