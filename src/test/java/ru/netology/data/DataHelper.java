package ru.netology.data;

import lombok.Value;
import lombok.val;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DataHelper {
  private DataHelper() {}

  @Value
  public static class AuthInfo {
    private String login;
    private String password;
  }

  public static AuthInfo getAuthInfo() {
    return new AuthInfo("vasya", "qwerty123");
  }

  // public static AuthInfo getOtherAuthInfo(AuthInfo original) {return new AuthInfo("petya", "123qwerty");}

  @Value
  public static class VerificationCode {
    private String code;
  }

  public static VerificationCode getVerificationCodeFor() {
    val codeSQL ="SELECT code FROM auth_codes WHERE user_id = '0a604536-0191-45b2-9250-e2712fa4a04c';";
    val runner =new QueryRunner();
    String code = "";

    try (
            val conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "9mREsvXDs9Gk89Ef");
    ) {
      code = runner.query(conn, codeSQL, new ScalarHandler<>());
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
    return new VerificationCode(code);}

  public static VerificationCode getVerificationWrongCode() {
    return new VerificationCode("12345");
  }


}


