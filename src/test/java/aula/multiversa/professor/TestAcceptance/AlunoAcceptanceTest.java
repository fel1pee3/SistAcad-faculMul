package aula.multiversa.professor.TestAcceptance;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import io.restassured.RestAssured;

import static org.hamcrest.Matchers.equalTo;

public class AlunoAcceptanceTest {

    @Test
    void testCadastro_AlunoComDadosValidos() {
        String alunoJson = "{ \"nome\": \"Pedro\", \"email\": \"pedro@email.com\"}";

        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(alunoJson)
                .when()
                .post("http://localhost:8080/alunos/create")
                .then()
                .statusCode(200)
                .body("nome", equalTo("Pedro"))
                .body("email", equalTo("pedro@email.com"));

    }

}
