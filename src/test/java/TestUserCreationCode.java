import io.restassured.response.Response;
import models.User;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class TestUserCreationCode {

    @Test
    public void verifyUserCanBeCreated() {
        User currentUser = new User();
        currentUser.setId(3);
        currentUser.setUserName("Vasya");
        currentUser.setFirstName("Vasya");
        currentUser.setLastName("Tymchenko");
        currentUser.setEmail("vasya@ukr.net");
        currentUser.setPassword("vasya");
        currentUser.setPhone("555555");
        currentUser.setUserStatus(0);

        new UserEndPoint()
                .createUser(currentUser)
                .then()
                .statusCode(200);

        System.out.println();
    }

    @Test
    public void verifyUserHasIdAfterCreation() {
        User user = new User();
        user.setUserName("Vasya");
        user.setId(5);
        user.setFirstName("Vasya");
        user.setLastName("Tymchenko");
        user.setEmail("vasya@ukr.net");
        user.setPassword("vasya");
        user.setPhone("555555");
        user.setUserStatus(0);

        Response userResponse = new UserEndPoint()
                .createUser(user);

        User userFromService = userResponse.body().as(User.class);
        Assert.assertNotNull(userFromService);

        Assert.assertNotNull(userFromService.getId());
    }

    @Test
    public void verifyUserCanBeDeleted() throws InterruptedException {
        User currentUser = new User();
        currentUser.setUserName("Vasya");
        currentUser.setId(2);
        currentUser.setFirstName("Vasya");
        currentUser.setLastName("Tymchenko");
        currentUser.setEmail("vasya@ukr.net");
        currentUser.setPassword("vasya");
        currentUser.setPhone("555555");
        currentUser.setUserStatus(0);

        Response userResponse = new UserEndPoint()
                .createUser(currentUser);
        String createdUserName = userResponse
                .body()
                .as(User.class)
                .getUserName();
        User userCreatedFromService = new UserEndPoint()
                .getUser(createdUserName)
                .as(User.class);

        new UserEndPoint().deleteUser(userCreatedFromService.getUserName());

        Response userByUserName = new UserEndPoint().getUser(String.valueOf(userCreatedFromService.getUserName()));

        Assertions.assertThat(userByUserName.statusCode()).isEqualTo(404);



    }
}
//
////        SoftAssertions assertions = new SoftAssertions();
//////        assertions.assertThat(petFromService.getName()).as("Name").isEqualTo(murchyk.getName());
////        assertions.assertThat(petFromService.getName()).as("Name").isEqualTo("Murchyk2");
//////        assertions.assertThat(petFromService.getStatus()).as("Status").isEqualTo(murchyk.getStatus());
////        assertions.assertThat(petFromService.getStatus()).as("Status").isEqualTo("available");
////        assertions.assertAll();
//
//    }
//
//    @BeforeClass
//    public static void cleanUp() {
//        List<Pet> petList = new PetStorePetEndPoint()
//                .getPetByStatus("available")
//                .body()
//                .jsonPath().getList("$", Pet.class);
//
//        List<Pet> petList2 = new PetStorePetEndPoint()
//                .getPetByStatus("available")
//                .body()
//                .jsonPath().getList("findAll {item -> item.name == 'Murchyk2' }", Pet.class);
//
//        List<Pet> petList3 = new PetStorePetEndPoint()
//                .getPetByStatus("available")
//                .body()
//                .jsonPath().getList("findAll {item -> item.name == 'Murchyk3' }", Pet.class);
//
//        for(Pet pet : petList2) {
//            new PetStorePetEndPoint().deleteById(pet.getId());
//        }
//
//        System.out.println();
//    }
//}
