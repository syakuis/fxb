    CREATE TABLE MODULE
    (
      MODULE_ID            VARCHAR2(100) NOT NULL,
      MODULE_NAME          VARCHAR2(100) NOT NULL,
      REG_DATE             DATETIME NULL
    );

    ALTER TABLE MODULE
    ADD CONSTRAINT  MODULE_PK PRIMARY KEY (MODULE_ID);

    COMMENT ON TABLE MODULE IS '모듈';

    COMMENT ON COLUMN MODULE.MODULE_ID IS '모듈ID';
    COMMENT ON COLUMN MODULE.MODULE_NAME IS '모듈명';
    COMMENT ON COLUMN MODULE.REG_DATE IS '등록일';