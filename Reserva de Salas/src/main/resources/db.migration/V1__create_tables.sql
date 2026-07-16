CREATE TABLE rooms (
                       id          BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name        VARCHAR(100) NOT NULL,
                       is_busy     BOOLEAN      NOT NULL DEFAULT FALSE,
                       capacity    INT          NOT NULL DEFAULT 8,
                       created_at  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       updated_at  DATETIME     ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE employees (
                           id          BIGINT AUTO_INCREMENT PRIMARY KEY,
                           name        VARCHAR(150) NOT NULL,
                           position       VARCHAR(100) NOT NULL,
                           email       VARCHAR(150) NOT NULL UNIQUE,
                           password    VARCHAR(255) NOT NULL,
                           created_at  DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           updated_at  DATETIME     ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE reservations (
                              id            BIGINT AUTO_INCREMENT PRIMARY KEY,
                              room_id       BIGINT       NOT NULL,
                              employee_id   BIGINT       NOT NULL,
                              start_time    DATETIME     NOT NULL,
                              end_time      DATETIME     NOT NULL,
                              status        ENUM('pending', 'confirmed', 'cancelled', 'completed')
                  NOT NULL DEFAULT 'confirmed',
                              created_at    DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              updated_at    DATETIME     ON UPDATE CURRENT_TIMESTAMP,

                              FOREIGN KEY (room_id)     REFERENCES rooms(id)     ON DELETE RESTRICT,
                              FOREIGN KEY (employee_id) REFERENCES employees(id) ON DELETE CASCADE
);

-- =============================================
-- ÍNDICES para performance e regras de negócio
-- =============================================

-- Índice para buscas rápidas por sala e horário
CREATE INDEX idx_room_time ON reservations(room_id, start_time, end_time);

-- Índice para verificar reservas de um funcionário
CREATE INDEX idx_employee ON reservations(employee_id);

-- =============================================
-- TRIGGERS / CHECKS (MySQL 8.0+)
-- =============================================

-- Impede reserva com data/hora passada
DELIMITER //
CREATE TRIGGER before_reservation_insert BEFORE INSERT ON reservations
    FOR EACH ROW
BEGIN
    IF NEW.start_time < NOW() THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Não é possível reservar datas/horários no passado.';
END IF;

IF NEW.end_time <= NEW.start_time THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'A data de término deve ser posterior à data de início.';
END IF;
END//
DELIMITER ;