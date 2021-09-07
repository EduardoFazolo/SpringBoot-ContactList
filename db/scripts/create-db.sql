CREATE TABLE "contact" (
  "id" BIGSERIAL PRIMARY KEY,
  "name" varchar,
  "last_name" varchar,
  "birthday" timestamp,
  "kinship_degree" varchar
);

CREATE TABLE "phones" (
  "id" BIGSERIAL PRIMARY KEY,
  "contact_id" bigint UNIQUE NOT NULL,
  "number" varchar
);

ALTER TABLE "phones" ADD FOREIGN KEY ("contact_id") REFERENCES "contact" ("id");
