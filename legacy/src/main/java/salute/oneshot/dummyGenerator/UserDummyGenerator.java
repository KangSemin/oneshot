package salute.oneshot.dummyGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.datafaker.Faker;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import salute.oneshot.domain.user.entity.UserRole;

public class UserDummyGenerator {

    private static final String USER_TABLE_NAME = "users";
    private static final String USER_COLUMNS = "id,email,password,nick_name,user_role,created_at,modified_at,@is_deleted,@is_deleted_at";



    public static void main(String[] args) {

        Faker faker = new Faker();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(4);

        List<String> userDataList = new ArrayList<>();
        Set<String> emails = new HashSet<>();

        for (int i = 1; i <=100000 ; i++) {

            StringBuilder builder = new StringBuilder();
            String email ;

            do {
                email = faker.internet().emailAddress();
            } while (!emails.add(email));

            //id
            builder.append(i);
            builder.append(',');

            //email
            builder.append(email);
            builder.append(',');

            //password
            builder.append(encoder.encode(faker.internet().password()+"!"));
            builder.append(',');

            //nickname
            builder.append(faker.pokemon().name())
            .append(' ')
            .append(faker.dessert().variety());
            builder.append(',');

            //userRole
            builder.append( i<=1000 ? UserRole.ADMIN.toString() : UserRole.USER.toString());
            builder.append(',');

            //createdAt
            builder.append(LocalDateTime.now());
            builder.append(',');

            //modifiedAt
            builder.append(LocalDateTime.now());
            builder.append(',');

            //isDeleted
            builder.append(0);
            builder.append(',');

            //deletedAt

            userDataList.add(builder.toString());
        }

        CSVGenerator.generate(USER_TABLE_NAME,USER_COLUMNS,userDataList);
        System.out.println("SET is_deleted = CAST(@is_deleted AS UNSIGNED),\n"
                            + "is_deleted_at = NULLIF(@is_deleted_at, '');");
    }
}
