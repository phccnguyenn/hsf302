package javafx.fe_movie_ticket.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
public class FilesystemConfig {

    @Value("${file.movie}")
    private String movieDirectory;

    @Value("${file.ticket}")
    private String ticketDirectory;

}
