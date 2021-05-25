package ru.netology.mode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    private String login;
    private String password;
    //java -jar ./app-deadline.jar -P:jdbc.url=jdbc:mysql://localhost:3306/app -P:jdbc.user=user -P:jdbc.password=pass
    //java -jar ./app-deadline.jar -P:jdbc.url=jdbc:mysql://localhost:3306/app -P:jdbc.user=user -P:jdbc.password=pass
    //docker-compose exec mysql mysql -u app -p app
}
