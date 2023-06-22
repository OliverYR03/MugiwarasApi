package certus.edu.pe.model.exceptions;


import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {

	private Date timestamp;
	private String message;
	private String details;
	
}
