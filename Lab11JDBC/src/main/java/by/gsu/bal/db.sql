ALTER TABLE directories DROP FOREIGN KEY directories_id_directories_parentDirectory_foreign;
ALTER TABLE files DROP FOREIGN KEY files_parentDirectory_directories_id_foreign;
DROP TABLE IF EXISTS directories;
DROP TABLE IF EXISTS files;

CREATE TABLE IF NOT EXISTS directories (
  id bigint NOT NULL,
  parent_directory bigint NOT NULL,
  name varchar(255) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT directories_id_directories_parentDirectory_foreign FOREIGN KEY (parent_directory) REFERENCES directories (id)
);

CREATE TABLE IF NOT EXISTS files (
  id bigint NOT NULL,
  parent_directory bigint NOT NULL,
  name varchar(255) NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT files_parentDirectory_directories_id_foreign FOREIGN KEY (parent_directory) REFERENCES directories (id)
);