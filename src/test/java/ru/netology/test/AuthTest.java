package ru.netology.test;

import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.LoginPage;


import java.sql.DriverManager;
import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;

class AuthTest {

  @BeforeEach
  void setUp() throws SQLException {
    val codeSQL ="DELETE FROM auth_codes WHERE user_id = '0a604536-0191-45b2-9250-e2712fa4a04c';";
    val runner =new QueryRunner();
    try (
            val conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "9mREsvXDs9Gk89Ef");
    ) {
      runner.update(conn, codeSQL);
    }

  }

  @Test
  void shouldAuth() {
    val loginPage = open("http://localhost:9999", LoginPage.class);
    val authInfo = DataHelper.getAuthInfo();
    val verificationPage = loginPage.validLogin(authInfo);
    val verificationCode = DataHelper.getVerificationCodeFor();
    verificationPage.validVerify(verificationCode);
  }

  @Test
  void shouldNotAuth() {
    val loginPage = open("http://localhost:9999", LoginPage.class);
    val authInfo = DataHelper.getAuthInfo();
    val verificationPage = loginPage.validLogin(authInfo);
    val verificationCode = DataHelper.getVerificationWrongCode();
    verificationPage.notValidVerify(verificationCode);
  }
}

