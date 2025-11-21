DROP DATABASE IF EXISTS `mini-pet-shelter-db`;
CREATE DATABASE IF NOT EXISTS `mini-pet-shelter-db`
  CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `mini-pet-shelter-db`;

DROP TABLE IF EXISTS adoptions;
DROP TABLE IF EXISTS adoption_applications;
DROP TABLE IF EXISTS medical_records;
DROP TABLE IF EXISTS foster_care;
DROP TABLE IF EXISTS intake_records;
DROP TABLE IF EXISTS animals;
DROP TABLE IF EXISTS shelters;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS users;

-- 유저/권한
CREATE TABLE users (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  login_id VARCHAR(50) NOT NULL,
  password VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  phone VARCHAR(30) NULL,
  created_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  updated_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  
  CONSTRAINT `uk_login` UNIQUE (login_id),
  CONSTRAINT `uk_email` UNIQUE (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE roles (
  role_name VARCHAR(30) PRIMARY KEY -- USER/APPLICANT/STAFF/ADMIN
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE user_roles (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id BIGINT NOT NULL,
  role_name VARCHAR(30) NOT NULL,
  
  UNIQUE KEY `uk_user_roles_user_id_role_name` (user_id, role_name),
    INDEX `idx_user_roles_user_id` (user_id),
    INDEX `idx_user_roles_role_name` (role_name),
    
  CONSTRAINT `fk_user_roles_user` FOREIGN KEY (user_id) REFERENCES users(id),
  CONSTRAINT `fk_user_roles_role` FOREIGN KEY (role_name) REFERENCES roles(role_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

# === REFRESH TOKENS (1:1 관계) === #
CREATE TABLE refresh_tokens (
	id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL UNIQUE COMMENT '사용자 ID',
    token VARCHAR(350) NOT NULL COMMENT '리프레시 토큰 값',
    expiry DATETIME(6) NOT NULL COMMENT '만료 시간',
    
    created_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    updated_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
    
    INDEX `idx_refresh_token_user_id` (user_id),
    
    CONSTRAINT `fk_refresh_token_user` FOREIGN KEY (user_id) REFERENCES users(id)
)	
	ENGINE=InnoDB
    DEFAULT CHARSET = utf8mb4
    COLLATE = utf8mb4_unicode_ci
    COMMENT = '리프레시 토큰 저장 테이블';

-- 보호소/동물
CREATE TABLE shelters (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(120) NOT NULL,
  address VARCHAR(255) NULL,
  latitude DECIMAL(10,7) NULL,
  longitude DECIMAL(10,7) NULL,
  phone VARCHAR(30) NULL,
  created_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE animals (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(30) NULL DEFAULT 'TBD',
  shelter_id BIGINT NOT NULL,
  species VARCHAR(30) NOT NULL,               -- DOG/CAT/OTHER
  breed VARCHAR(60) NULL,
  sex VARCHAR(10) NOT NULL,                   -- MALE/FEMALE/UNKNOWN
  age_years DECIMAL(4,1) NULL,
  weight_kg DECIMAL(5,2) NULL,
  temperament VARCHAR(100) NULL,              -- 성격 요약
  status VARCHAR(20) NOT NULL DEFAULT 'AVAILABLE', -- AVAILABLE/FOSTERED/ADOPTION_PENDING/ADOPTED/TREATMENT
  photo_url VARCHAR(255) NULL,
  created_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  updated_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  CONSTRAINT `fk_animals_shelter` FOREIGN KEY (shelter_id) REFERENCES shelters(id),
  CONSTRAINT `chk_animals_sex` CHECK (sex IN ('MALE','FEMALE','UNKNOWN')),
  CONSTRAINT `chk_animals_status` CHECK (status IN ('AVAILABLE','FOSTERED','ADOPTION_PENDING','ADOPTED','TREATMENT')),
  INDEX idx_animals_shelter (shelter_id, status),
  INDEX idx_animals_species (species, breed)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 입소/구조 기록
CREATE TABLE intake_records (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  animal_id BIGINT NOT NULL,
  intake_date DATE NOT NULL,
  intake_reason VARCHAR(100) NOT NULL,        -- STRAY/SURRENDER/TRANSFER 등
  found_location VARCHAR(255) NULL,
  note TEXT NULL,
  created_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  CONSTRAINT `fk_intake_records_animal` FOREIGN KEY (animal_id) REFERENCES animals(id),
  INDEX idx_intake_animal (animal_id, intake_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 임시보호 배정
CREATE TABLE foster_care (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  animal_id BIGINT NOT NULL,
  foster_user_id BIGINT NOT NULL,             -- 임시보호인(유저)
  start_date DATE NOT NULL,
  end_date DATE NULL,
  status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE', -- ACTIVE/CLOSED/CANCELED
  note TEXT NULL,
  created_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  CONSTRAINT `fk_foster_care_animal` FOREIGN KEY (animal_id) REFERENCES animals(id),
  CONSTRAINT `fk_foster_care_user` FOREIGN KEY (foster_user_id) REFERENCES users(id),
  CONSTRAINT `foster_care_status` CHECK (status IN ('ACTIVE','CLOSED','CANCELED')),
  CONSTRAINT `uk_foster_care_animal_id_status` UNIQUE(animal_id, status), -- ACTIVE는 동물당 1건만
  INDEX idx_foster_user (foster_user_id, status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 의료 기록
CREATE TABLE medical_records (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  animal_id BIGINT NOT NULL,
  record_date DATE NOT NULL,
  type VARCHAR(30) NOT NULL,                  -- VACCINE/NEUTER/TREATMENT/EXAM
  description TEXT NOT NULL,
  cost DECIMAL(10,2) NULL,
  created_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  CONSTRAINT `fk_medical_records_animal` FOREIGN KEY (animal_id) REFERENCES animals(id),
  INDEX idx_medical_animal (animal_id, record_date, type)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 입양 신청/계약
CREATE TABLE adoption_applications (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  animal_id BIGINT NOT NULL,
  applicant_id BIGINT NOT NULL,
  status VARCHAR(20) NOT NULL DEFAULT 'APPLIED', -- APPLIED/REVIEW/APPROVED/REJECTED/CANCELED
  interview_at DATETIME(6) NULL,
  home_check BOOLEAN NOT NULL DEFAULT FALSE,
  message TEXT NULL,
  created_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  updated_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  CONSTRAINT `fk_adoption_applications_animal` FOREIGN KEY (animal_id) REFERENCES animals(id),
  CONSTRAINT `fk_adoption_applications_users`FOREIGN KEY (applicant_id) REFERENCES users(id),
  CONSTRAINT `chk_adoption_applications_status`CHECK (status IN ('APPLIED','REVIEW','APPROVED','REJECTED','CANCELED')),
  CONSTRAINT `uk_application_once` UNIQUE (animal_id, applicant_id, status),
  INDEX idx_app_animal (animal_id, status),
  INDEX idx_app_user (applicant_id, status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE adoptions (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  animal_id BIGINT NOT NULL,
  application_id BIGINT NOT NULL,
  adopter_id BIGINT NOT NULL,
  adoption_date DATE NOT NULL,
  fee DECIMAL(10,2) NULL,
  contract_url VARCHAR(255) NULL,
  created_at DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
  CONSTRAINT `fk_adoptions_animal`FOREIGN KEY (animal_id) REFERENCES animals(id),
  CONSTRAINT `fk_adoptions_adoption_application`FOREIGN KEY (application_id) REFERENCES adoption_applications(id),
  CONSTRAINT `fk_adoptions_user`FOREIGN KEY (adopter_id) REFERENCES users(id),
  CONSTRAINT `uk_adoption_animal` UNIQUE (animal_id) -- 동물당 1회만 입양 완료
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;



