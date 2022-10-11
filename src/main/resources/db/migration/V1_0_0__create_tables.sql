CREATE TABLE "photos"(
                         "id" UUID NOT NULL,
                         "name" VARCHAR(255) NOT NULL,
                         "file" VARCHAR(255) NOT NULL
);
ALTER TABLE
    "photos" ADD PRIMARY KEY("id");
CREATE TABLE "numbers"(
                          "id" BIGINT NOT NULL,
                          "name" VARCHAR(255) NOT NULL,
                          "description" VARCHAR(255) NOT NULL,
                          "photo_count" BIGINT NOT NULL,
                          "hotel_id" BIGINT NOT NULL,
                          "photo_id" UUID NOT NULL
);
ALTER TABLE
    "numbers" ADD PRIMARY KEY("id");
CREATE TABLE "hotels"(
                         "id" BIGINT NOT NULL,
                         "name" VARCHAR(255) NOT NULL,
                         "number_count" BIGINT NOT NULL,
                         "photo_count" BIGINT NOT NULL,
                         "place_id" BIGINT NOT NULL,
                         "photo_id" UUID NOT NULL
);
ALTER TABLE
    "hotels" ADD PRIMARY KEY("id");
CREATE TABLE "places"(
                         "id" BIGINT NOT NULL,
                         "name" VARCHAR(255) NOT NULL,
                         "hotel_count" BIGINT NOT NULL,
                         "photo_count" BIGINT NOT NULL,
                         "region_id" BIGINT NOT NULL,
                         "photo_id" UUID NOT NULL
);
ALTER TABLE
    "places" ADD PRIMARY KEY("id");
CREATE TABLE "regions"(
                          "id" BIGINT NOT NULL,
                          "name" VARCHAR(255) NOT NULL,
                          "place_count" BIGINT NOT NULL,
                          "hotel_count" BIGINT NOT NULL,
                          "country_id" BIGINT NOT NULL
);
ALTER TABLE
    "regions" ADD PRIMARY KEY("id");
CREATE TABLE "countries"(
                            "id" BIGINT NOT NULL,
                            "name" VARCHAR(255) NOT NULL,
                            "region_counter" BIGINT NOT NULL,
                            "place_count" BIGINT NOT NULL,
                            "hotel_count" BIGINT NOT NULL
);
ALTER TABLE
    "countries" ADD PRIMARY KEY("id");
CREATE TABLE "tasks"(
                        "id" BIGINT NOT NULL,
                        "name" VARCHAR(255) NOT NULL,
                        "description" VARCHAR(255) NOT NULL,
                        "user_id" BIGINT NULL,
                        "notification" VARCHAR(255) NOT NULL
);
ALTER TABLE
    "tasks" ADD PRIMARY KEY("id");
CREATE TABLE "users"(
                        "id" BIGINT NOT NULL,
                        "login" VARCHAR(255) NOT NULL,
                        "password" VARCHAR(255) NOT NULL,
                        "fio" VARCHAR(255) NOT NULL,
                        "role" VARCHAR(255) CHECK
                            ("role" IN('')) NOT NULL
);
ALTER TABLE
    "users" ADD PRIMARY KEY("id");
ALTER TABLE
    "tasks" ADD CONSTRAINT "tasks_user_id_foreign" FOREIGN KEY("user_id") REFERENCES "users"("id");
ALTER TABLE
    "regions" ADD CONSTRAINT "regions_country_id_foreign" FOREIGN KEY("country_id") REFERENCES "countries"("id");
ALTER TABLE
    "numbers" ADD CONSTRAINT "numbers_hotel_id_foreign" FOREIGN KEY("hotel_id") REFERENCES "hotels"("id");
ALTER TABLE
    "numbers" ADD CONSTRAINT "numbers_photo_id_foreign" FOREIGN KEY("photo_id") REFERENCES "photos"("id");
ALTER TABLE
    "places" ADD CONSTRAINT "places_region_id_foreign" FOREIGN KEY("region_id") REFERENCES "regions"("id");
ALTER TABLE
    "places" ADD CONSTRAINT "places_photo_id_foreign" FOREIGN KEY("photo_id") REFERENCES "photos"("id");
ALTER TABLE
    "hotels" ADD CONSTRAINT "hotels_place_id_foreign" FOREIGN KEY("place_id") REFERENCES "places"("id");
ALTER TABLE
    "hotels" ADD CONSTRAINT "hotels_photo_id_foreign" FOREIGN KEY("photo_id") REFERENCES "photos"("id");