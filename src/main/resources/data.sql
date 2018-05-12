INSERT INTO "user"(name, surname, role)
  SELECT 'Shimon', 'Lanshtain', 'ADMIN'
  WHERE NOT EXISTS(
      SELECT id
      FROM "user"
      WHERE id = 1
  );

INSERT INTO user_data(login, hash_password, user_role, status, user_id)
  SELECT 'admin', '$2a$10$oxICZ.L9vaaM2gPCYEHTFeUjkmshfKP48pxEQ2A/SzoMNV9LctpVa', 'ADMIN', 'CONFIRMED', 1
  WHERE NOT EXISTS(
      SELECT id
      FROM user_data
      WHERE id = 1
  );

INSERT INTO "user"(name, surname, role)
  SELECT 'Konstantin', 'Kovalenko', 'USER'
  WHERE NOT EXISTS(
      SELECT id
      FROM "user"
      WHERE id = 2
  );

INSERT INTO user_data(login, hash_password, user_role, status, user_id)
  SELECT 'user', '$2a$10$y3tC82XQRC3B5TZtPvz/SOTIK8Xgi7Gt0H2VoXj5hw6MNzWE4Vz/2', 'USER', 'CONFIRMED', 2
  WHERE NOT EXISTS(
      SELECT id
      FROM user_data
      WHERE id = 2
  );

CREATE TABLE IF NOT EXISTS persistent_logins (
  username VARCHAR(64) NOT NULL,
  series VARCHAR(64) NOT NULL,
  token VARCHAR(64) NOT NULL,
  last_used TIMESTAMP NOT NULL,
  PRIMARY KEY (series)
);